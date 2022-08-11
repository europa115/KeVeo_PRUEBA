package com.example.KeVeo.dto;

import com.example.KeVeo.data.entity.Genre;
import com.example.KeVeo.data.entity.Platform;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmDTO  implements Serializable {

    private Integer id;

    private String title;

    private Integer year;

    private Integer duration;

    private String cover;

    private String casting;

    private String synopsis;

    private String trailer;

    // private Integer puntuation;

    // private boolean favorites;

    private List<Genre> genres= new ArrayList<>();;

    private List<Platform> platforms= new ArrayList<>();;
}
