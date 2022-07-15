package com.example.KeVeo.data.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
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
    @Basic(optional = false)
    private boolean active;
    private String userSurname;
    private String userEmail;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Role> roles;

    //******************************************Constructors************************************************************
    public User(String userName, String password, Date date, boolean active, String userSurname,
                String userEmail, List roles) {
        this.userName = userName;
        this.password = password;
        this.date = date;
        this.active = active;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

}