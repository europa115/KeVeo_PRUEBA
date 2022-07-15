package com.example.KeVeo.dto;

import com.example.KeVeo.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDTO implements Serializable {

    private Integer id;
    private String roleName;

    public RoleDTO(String roleName) {

        this.roleName = roleName;

    }

}