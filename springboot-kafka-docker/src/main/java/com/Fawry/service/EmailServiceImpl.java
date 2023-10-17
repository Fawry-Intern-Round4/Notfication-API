package com.fawry.service;

import com.fawry.dto.EmailDto;
import com.fawry.dto.OrderDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public EmailDto createEmail(OrderDTO orderDTO) {
        return EmailDto.builder()
                .to(orderDTO.getGuestEmail())
                .body(orderDTO.toString())
                .subject("Order Confirmation")
                .build();
    }

    @Override
    public void sendEmail(EmailDto emailDto) throws MessagingException, IOException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(fromEmail);
        mimeMessageHelper.setTo(Objects.requireNonNull(emailDto.getTo()));
        mimeMessageHelper.setSubject(Objects.requireNonNull(emailDto.getSubject()));
        mimeMessageHelper.setText(Objects.requireNonNull(emailDto.getBody()));
        String[] cc = emailDto.getCc();
        MultipartFile[] files = emailDto.getFiles();
        if (cc != null) {
            mimeMessageHelper.setCc(cc);
        }
        if (files != null) {
            for (MultipartFile file : files) {
                mimeMessageHelper
                        .addAttachment(
                                Objects.requireNonNull(file.getOriginalFilename()),
                                new ByteArrayResource(file.getBytes())
                        );
            }
        }
        javaMailSender.send(mimeMessage);
    }
}
