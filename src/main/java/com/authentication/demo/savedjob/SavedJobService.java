package com.authentication.demo.savedjob;

import com.authentication.demo.advice.*;
import com.authentication.demo.employer.Employer;
import org.springframework.security.core.userdetails.UserDetails;
import com.authentication.demo.employer.EmployerRepository;
import com.authentication.demo.job.Job;
import com.authentication.demo.job.JobRepository;
import com.authentication.demo.job.dto.JobDto;
import com.authentication.demo.job.dto.JobPaginationDto;
import com.authentication.demo.savedjob.dto.SavedJobDto;
import org.springframework.security.core.context.SecurityContextHolder;
import com.authentication.demo.savedjob.dto.SavedJobPaginationDto;
import com.authentication.demo.savedjob.request.CreateSavedJobRequest;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import com.authentication.demo.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SavedJobService {

    @Autowired
    private final SavedJobRepository savedJobRepository;

    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final EmployerRepository employerRepository;

    public SavedJobService(SavedJobRepository savedJobRepository,
            JobRepository jobRepository,
            UserRepository userRepository,
            EmployerRepository employerRepository) {
        this.savedJobRepository = savedJobRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
        this.employerRepository = employerRepository;
    }

    public SavedJob syncSavedJob(Long jobId) {
        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = this.userRepository.findByEmail(username)
                    .orElseThrow(() -> new NotFoundException("User not found"));

            return this.savedJobRepository.checkIfSavedJobExists(jobId, user.getId());
        }
        return null;
    }

    public SavedJobPaginationDto getSavedJobs(Long userId, Integer page, Integer size, String direction) {

        Integer currentPage = MyUtils.paginate(page, direction);

        Pageable paging = PageRequest.of(currentPage, size, Sort.by("id"));
        Page<SavedJobDto> pagedResult = this.savedJobRepository.findSavedJobsByUserId(userId, paging);

        return new SavedJobPaginationDto(pagedResult.getContent(), pagedResult.getTotalPages(), currentPage);

    }

    public void createSavedJob(CreateSavedJobRequest request) {

        SavedJob exists = this.savedJobRepository

                .checkIfSavedJobExists(request.getJobId(), request.getUserId());
        if (exists != null) {
            throw new BadRequestException("User has already saved this job.");
        }

        Job job = this.jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new NotFoundException("Job not found."));
        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found."));
        Employer employer = this.employerRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new NotFoundException("User not found."));

        SavedJob savedJob = new SavedJob(user, job, employer);

        this.savedJobRepository.save(savedJob);
    }

    public void deleteSavedJob(Long id) {
        this.savedJobRepository.deleteById(id);
    }
}
