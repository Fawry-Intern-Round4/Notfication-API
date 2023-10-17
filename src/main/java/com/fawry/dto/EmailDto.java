package com.fawry.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailDto {
    MultipartFile[] files;
    String to;
    String[] cc;
    String subject;
    String body;
}
