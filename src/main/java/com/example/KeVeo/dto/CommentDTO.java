package com.example.KeVeo.dto;

import com.example.KeVeo.data.entity.Film;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO  implements Serializable {

    private Integer id;
    private String content;
    private Date creation;
    private Film film;
}
