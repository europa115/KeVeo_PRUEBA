package com.example.KeVeo.data.entity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Lob
    @NotNull
    private String content;
    @NotNull
    private LocalDateTime creation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
