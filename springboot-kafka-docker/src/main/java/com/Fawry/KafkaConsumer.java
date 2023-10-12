package com.Fawry;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "my_topic", groupId = "my_group_id")
    public void getMessage(String message) {

        System.out.println(message);
        sendNotification(message);
    }
    public void sendNotification(String msg) {

        APIClient.sendEmail("mohamed.mohamed32@msa.edu.eg", msg);
    }


}
