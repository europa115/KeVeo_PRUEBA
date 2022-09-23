package com.example.KeVeo.dto;

import com.example.KeVeo.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PunctuationDTO implements Serializable {
    private Integer id;
    private Double score;
    private User user;
}
