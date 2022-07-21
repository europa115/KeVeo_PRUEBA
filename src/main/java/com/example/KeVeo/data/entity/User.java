package com.example.KeVeo.data.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
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
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private ZonedDateTime registerDate;
    @Basic(optional = false)
    private boolean active;
    private String userSurname;
    private String userEmail;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;

    //******************************************Constructors************************************************************
    public User(String userName, String password, Date date, ZonedDateTime registerDate, boolean active, String userSurname,
                String userEmail, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.date = date;
        this.registerDate=registerDate;
        this.active = active;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }


}