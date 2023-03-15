package com.authentication.demo.employer;

import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.employer.request.CreateEmployerRequest;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;

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
                request.getCompanyName(),
                request.getFirstName(),
                request.getLastName(),
                request.getNumOfEmployees());

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
        this.userRepository.save(user);
    }
}
