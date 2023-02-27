package com.authentication.demo.email;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.authentication.demo.advice.BadRequestException;
import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.auth.request.EmailRequest;
import com.authentication.demo.auth.response.EmailResponse;
import com.authentication.demo.config.JwtService;
import com.authentication.demo.token.Token;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final Configuration configuration;

    @Autowired
    private final JavaMailSender javaMailSender;

    @Autowired
    final UserRepository userRepository;

    public EmailService(
            JwtService jwtService,
            Configuration configuration,
            JavaMailSender javaMailSender,
            UserRepository userRepository) {

        this.jwtService = jwtService;
        this.configuration = configuration;
        this.javaMailSender = javaMailSender;
        this.userRepository = userRepository;
    }

    @Value("${emailsender}")
    private String sender;

    public EmailResponse sendSimpleMail(EmailRequest request)
            throws MessagingException, IOException, TemplateException {

        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(sender);
        helper.setSubject("Reset Your Password");
        helper.setTo(request.getEmail());
        String emailContent = getEmailContent(request.getEmail());
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);

        return new EmailResponse("Email sent successfully...");
    }

    String getEmailContent(String email) throws IOException, TemplateException {
        User user = this.userRepository.findByEmail(email).orElseThrow();
        String token = this.jwtService.generateToken(user);

        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("token", token);
        this.configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
