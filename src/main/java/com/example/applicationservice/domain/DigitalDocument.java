package com.example.applicationservice.domain;


import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "digitalDocument")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DigitalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    private String type;
    // Use Boolean because boolean will not work with Lombok properly to generate is prefix field
    // https://stackoverflow.com/questions/42619986/lombok-annotation-getter-for-boolean-field
    private Boolean isRequired;
    private String path;
    private String description;
    private String title;
}
