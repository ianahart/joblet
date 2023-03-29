package com.authentication.demo.application;

import com.authentication.demo.application.request.CreateApplicationRequest;
import com.authentication.demo.employer.Employer;
import com.authentication.demo.employer.EmployerRepository;
import com.authentication.demo.job.Job;
import com.authentication.demo.job.JobRepository;
import com.authentication.demo.profile.Profile;
import com.authentication.demo.profile.ProfileRepository;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.advice.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JobRepository jobRepository;

    @Autowired
    private final ProfileRepository profileRepository;

    @Autowired
    private final EmployerRepository employerRepository;

    @Autowired
    private final ApplicationRepository applicationRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            JobRepository jobRepository,
            ProfileRepository profileRepository,
            EmployerRepository employerRepository,
            UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
        this.profileRepository = profileRepository;
        this.employerRepository = employerRepository;
        this.userRepository = userRepository;
    }

    public Application createApplication(CreateApplicationRequest request) {
        Job job = this.jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new NotFoundException("Job not found."));
        Profile profile = this.profileRepository.findById(request.getProfileId())
                .orElseThrow(() -> new NotFoundException("Profile not found."));
        if (profile.getResume() == null) {
            throw new BadRequestException("Please upload a resume via your profile page");
        }
        Employer employer = this.employerRepository.findById(request.getEmployerId())
                .orElseThrow(() -> new NotFoundException("Employer not found."));
        User user = this.userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundException("Employer not found."));

        Application exists = this.applicationRepository.getApplicationByUserIdAndJobId(request.getJobId(),
                request.getUserId());

        if (exists != null) {
            throw new BadRequestException("You have already subbmitted an application for this job.");
        }
        Application application = new Application();
      application.setJob(job);
      application.setProfile(profile);
      application.setEmployer(employer);
      application.setUser(user);
      return this.applicationRepository.save(application);
    }
}
