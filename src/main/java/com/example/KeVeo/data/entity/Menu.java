package com.example.KeVeo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private Menu parent;
    @Column(name = "APP_ORDER")
    private Integer order;
    private Integer active;
    private String url;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

}
