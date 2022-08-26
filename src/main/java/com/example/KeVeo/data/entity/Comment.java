package com.example.KeVeo.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    private String content;

    private Date creation;

    //La entidad comentarios se terminara mas tarde. Relacion desde film oneToMany/Relacion desde de User ManyToOne

    @ManyToOne(fetch = FetchType.LAZY)
    private Film film;

}
