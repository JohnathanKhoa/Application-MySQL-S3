package com.example.applicationservice.remote.request;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmailDetails implements Serializable {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
