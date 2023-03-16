package com.authentication.demo.employer;

import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.employer.request.CreateEmployerRequest;
import com.authentication.demo.employer.request.UpdateEmployerRequest;
import com.authentication.demo.user.Role;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;
import com.authentication.demo.util.MyUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class EmployerService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final EmployerRepository employerRepository;

    public EmployerService(UserRepository userRepository, EmployerRepository employerRepository) {
        this.userRepository = userRepository;
        this.employerRepository = employerRepository;
    }

    public Employer createEmployer(CreateEmployerRequest request) {

        Employer employer = new Employer(
                request.getEmail(),
                MyUtils.titleCase(request.getCompanyName()),
                MyUtils.titleCase(request.getFirstName()),
                MyUtils.titleCase(request.getLastName()),
                request.getNumOfEmployees(),
                request.getLocation());

        Employer newEmployer = this.employerRepository.save(employer);

        updateUserWithEmployer(newEmployer);

        return newEmployer;
    }

    public void updateUserWithEmployer(Employer employer) {
        String userName = "";
        Object auth = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (auth instanceof UserDetails) {
            userName = ((UserDetails) auth).getUsername();
        }

        User user = this.userRepository
                .findByEmail(userName)
                .orElseThrow(() -> new NotFoundException("User not found."));
        System.out.println(user);

        user.setEmployer(employer);
        user.setRole(Role.EMPLOYER);
        this.userRepository.save(user);
    }

    public Employer updateEmployer(UpdateEmployerRequest request, Long id) {
        Employer currentEmployer = this.employerRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found."));

        currentEmployer.setNumOfEmployees(request.getNumOfEmployees());
        currentEmployer.setEmail(request.getEmail());
        currentEmployer.setLastName(request.getLastName());
        currentEmployer.setFirstName(request.getFirstName());
        currentEmployer.setLocation(request.getLocation());
        currentEmployer.setCompanyName(request.getCompanyName());

        return this.employerRepository.save(currentEmployer);
    }
}
