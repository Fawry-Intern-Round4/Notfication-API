package com.Fawry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessagingException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class SpringKafkaDockerIntApplication {
    @Autowired
    KafkaProducer producer;


    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaDockerIntApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void Test() throws MessagingException {
        while (true) {
            producer.writeMessage("Hello World " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));


            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


