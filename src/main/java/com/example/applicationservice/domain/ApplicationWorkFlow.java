package com.example.applicationservice.domain;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "applicationWorkFlow")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationWorkFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    private String employeeId;
    private String createDate;
    private String lastModificationDate;
    private String status;
    private String comment;
}
