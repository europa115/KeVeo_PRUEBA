package com.example.KeVeo.data.entity;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private ZonedDateTime registerDate;
    @Basic(optional = false)
    @NotNull
    private boolean active;
    @NotNull
    private String userSurname;
    @NotNull
    private String userEmail;
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_films", joinColumns = @JoinColumn(name = "User_ID"),
    inverseJoinColumns = @JoinColumn(name = "Film_ID"))
    private List<Film> films;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Punctuation> punctuations;

    public void addRole(Role role) {
        this.roles.add(role);
    }
    public void addFavourite(Film film) {
        this.films.add(film);
    }

    //metodo creado para comprobar los favoritos con thymeleaf
    public boolean favourite(Integer id){
        boolean favourite=false;
        for(Film film:this.films){
            if(Objects.equals(id, film.getId())) favourite=true;
        }
        return favourite;
    }
}