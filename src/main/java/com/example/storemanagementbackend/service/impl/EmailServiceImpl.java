package com.example.storemanagementbackend.service.impl;
 
import com.example.storemanagementbackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
 
@Service
public class EmailServiceImpl implements EmailService {
 
    private final JavaMailSender mailSender;
 
    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
//    @Override
//    public void sendResetEmail(String toEmail, String resetLink) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setSubject("Reset Your Password");
//        message.setText("Click the link below to reset your password:\n" + resetLink);
//        message.setFrom("your-email@example.com");  // Replace with your sending email
//
//        mailSender.send(message);
//    }
@Override
public void sendOtpEmail(String toEmail, String otp) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject("Your Password Reset OTP");
    message.setText("Your OTP is: " + otp + "\nIt is valid for 5 minutes.");
    message.setFrom("your-email@example.com");
 
    mailSender.send(message);
}
 
}
//package com.example.register_page.service.impl;
//
//import com.example.register_page.service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//
//    private final JavaMailSender mailSender;
//
//    @Autowired
//    public EmailServiceImpl(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    @Override
//    public void sendResetEmail(String toEmail, String resetLink) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(toEmail);
//        message.setSubject("Reset Your Password");
//        message.setText("Click the link below to reset your password:\n" + resetLink);
//        message.setFrom("your-email@example.com"); // Replace with a valid sender address
//
//        mailSender.send(message);
//    }
//}
 
 