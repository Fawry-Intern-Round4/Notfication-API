package com.Fawry;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIClient {


    public static void sendEmail(String to, String body2) {
        // URL of the API endpoint
        String apiUrl = "http://localhost:8080/notification/sendEmail";

        // Create a RestTemplate instance to make HTTP requests
        RestTemplate restTemplate = new RestTemplate();

        // Set up the request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Create a MultiValueMap to hold the request body
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        // Add the file(s) if needed
        // body.add("file", file); // Add MultipartFile instances

        // Add the other parameters
        body.add("to", to);
//        body.add("cc", new String[]{"cc1@gmail.com", "cc2@gmail.com"});
//        body.add("subject", "Email Subject");
        body.add("body", body2);

        // Create the request entity with headers and body
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Send the POST request
        String response = restTemplate.postForObject(apiUrl, requestEntity, String.class);

        System.out.println("Response: " + response);


    }
}

