package com.example.KeVeo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MenuDTO {

    private Integer id;
    private String description;
    private MenuDTO parent;
    private Integer order;
    private Integer active;
    private String url;
    private String icon;
    private Set<RoleDTO> roles;
}
