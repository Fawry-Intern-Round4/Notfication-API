package com.example.mailsend.service.impl;

import com.example.mailsend.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendMail(MultipartFile[] file, String to, String[] cc, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(fromEmail);
            if (to != null) {
                mimeMessageHelper.setTo(to);
            } else {
                throw new RuntimeException("No To Email");
            }
            mimeMessageHelper.setTo(to);
            if (cc != null) {
                mimeMessageHelper.setCc(cc);
            }
            if (subject != null) {
                mimeMessageHelper.setSubject(subject);
            } else {
                mimeMessageHelper.setSubject("No Subject");
            }
            if (body != null) {
                mimeMessageHelper.setText(body);
            } else {
                mimeMessageHelper.setText("No Body");
            }

            if (file != null) {
                for (int i = 0; i < file.length; i++) {
                    mimeMessageHelper.addAttachment(
                            file[i].getOriginalFilename(),
                            new ByteArrayResource(file[i].getBytes()));
                }
            }

            javaMailSender.send(mimeMessage);

            return "mail send";

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
