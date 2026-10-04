package com.muhammadakram.campus_placement_portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Password Reset OTP - Campus Placement Portal");
        message.setText("Hello,\n\nYour OTP for password reset is: " + otp +
                "\n\nPlease do not share this OTP with anyone. If you didn't request this, please ignore this email.\n\n" +
                "Regards,\nCampus Placement Portal Team");

        mailSender.send(message);
        System.out.println("OTP Email sent successfully to: " + toEmail);
    }
}