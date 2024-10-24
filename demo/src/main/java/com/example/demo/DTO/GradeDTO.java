package com.example.demo.DTO;

import com.example.demo.entity.GradeId;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@AllArgsConstructor
@Setter
@Getter
public class GradeDTO {
    @EmbeddedId
    private GradeId id = new GradeId();
    private Double score;

}
