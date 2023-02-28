package com.authentication.demo.email;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.authentication.demo.advice.NotFoundException;
import com.authentication.demo.auth.request.EmailRequest;
import com.authentication.demo.auth.response.EmailResponse;
import com.authentication.demo.config.JwtService;
import com.authentication.demo.passwordreset.PasswordResetService;
import com.authentication.demo.user.User;
import com.authentication.demo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final PasswordResetService passwordResetService;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final Configuration configuration;

    @Autowired
    private final JavaMailSender javaMailSender;

    @Autowired
    final UserRepository userRepository;

    public EmailService(
            PasswordResetService passwordResetService,
            JwtService jwtService,
            Configuration configuration,
            JavaMailSender javaMailSender,
            UserRepository userRepository) {

        this.jwtService = jwtService;
        this.passwordResetService = passwordResetService;
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

        User user = this.userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException("A user with this email does not exist."));

        helper.setTo(request.getEmail());
        String emailContent = getEmailContent(user);
        helper.setText(emailContent, true);
        javaMailSender.send(mimeMessage);

        return new EmailResponse("Email sent successfully...");
    }

    String getEmailContent(User user) throws IOException, TemplateException {
        String token = this.jwtService.generateToken(user);
        this.passwordResetService.savePasswordReset(user, token);

        StringWriter stringWriter = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("token", token);
        this.configuration.getTemplate("email.ftlh").process(model, stringWriter);
        return stringWriter.getBuffer().toString();
    }
}
