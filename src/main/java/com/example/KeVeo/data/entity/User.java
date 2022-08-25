package com.example.KeVeo.data.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;


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
    private boolean active;
    private String userSurname;
    private String userEmail;
    private String photo;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;

    @ManyToMany(cascade = CascadeType.ALL)     //Un poco de dudas con lazy y cascade
    @JoinTable(name = "user_films", joinColumns = @JoinColumn(name = "User_ID"),
    inverseJoinColumns = @JoinColumn(name = "Film_ID"))
    private List<Film> films;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_comments", joinColumns = @JoinColumn(name = "User_ID"),
            inverseJoinColumns = @JoinColumn(name = "Comment_ID"))
    private List<Comment> comments;

    //******************************************Constructors************************************************************
    /*public User(String userName, String password, Date date, ZonedDateTime registerDate, boolean active, String userSurname,
                String userEmail,String photo, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.date = date;
        this.registerDate = registerDate;
        this.active = active;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.photo=photo;
        this.roles = roles;
    }*/

    public void addRole(Role role) {
        this.roles.add(role);
    }


}