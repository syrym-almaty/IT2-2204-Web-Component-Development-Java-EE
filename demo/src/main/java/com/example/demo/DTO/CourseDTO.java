package com.example.demo.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.util.UUID;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String code;

    @NotNull
    private Integer credits;


}