package com.fawry;

import com.fawry.dto.OrderDTO;
import com.fawry.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
    private final EmailService emailService;

    @KafkaListener(topics = "notification_topic", groupId = "notification_id")
    public void getMessage(OrderDTO orderDTO) throws MessagingException, IOException {
        log.info("order:\n" + orderDTO);
        emailService.sendEmail(emailService.createEmail(orderDTO));
    }
}