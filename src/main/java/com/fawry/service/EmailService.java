package com.fawry.service;

import com.fawry.dto.EmailDto;
import com.fawry.dto.OrderDTO;
import jakarta.mail.MessagingException;

import java.io.IOException;


public interface EmailService  {
    EmailDto createEmail(OrderDTO orderDTO);
    void sendEmail(EmailDto emailDto) throws MessagingException, IOException;
}
