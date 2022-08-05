package com.example.KeVeo.dto;

import com.example.KeVeo.data.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {

    private Integer id;
    private String userName;
    private String password;
    private String date;
    private ZonedDateTime registerDate=ZonedDateTime.now();
    private boolean active;
    private String userSurname;
    private String userEmail;
    private String photo;
    private List<Role> roles = new ArrayList<>();


}
