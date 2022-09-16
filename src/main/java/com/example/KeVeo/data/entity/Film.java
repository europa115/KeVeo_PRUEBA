package com.example.KeVeo.data.entity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @NotNull
    private String title;
    @NotNull
    private Integer year;
    @NotNull
    private Integer duration;
    @NotNull
    private String cover;
    private String casting;
    @Lob
    private String synopsis;
    private String trailer;
    private boolean active;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "film_genres", joinColumns = @JoinColumn(name = "Film_ID"),
            inverseJoinColumns = @JoinColumn(name = "Genre_ID"))
    private List<Genre> genres;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "film_platforms", joinColumns = @JoinColumn(name = "Film_ID"),
            inverseJoinColumns = @JoinColumn(name = "Platform_ID"))
    private List<Platform> platforms;
    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "films_punctuations", joinColumns = @JoinColumn(name = "Film_ID"),
            inverseJoinColumns = @JoinColumn(name = "Punctuation_ID"))
    private List<Punctuation> punctuations;

}
