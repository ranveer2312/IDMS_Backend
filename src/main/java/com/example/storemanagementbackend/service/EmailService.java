package com.example.storemanagementbackend.service;
 
public interface EmailService {
        //void sendResetEmail(String toEmail, String resetLink);
        void sendOtpEmail(String toEmail, String otp);
 
}
 
 