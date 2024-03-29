package com.example.KeVeo.dto;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.utils.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDTO  implements Serializable {

    private Integer id;
    private String content;
    private LocalDateTime creation = DateUtil.stringToDate(DateUtil.dateToString(LocalDateTime.now()));
    private Film film;
    private User user;
}
