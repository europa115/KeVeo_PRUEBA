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
    private List<Role> roles = new ArrayList<>();


    //****************************************CONSTRUCTORS**************************************************************

    public UserDTO(String userName, String password, String date, ZonedDateTime registerDate, boolean active, String userSurname, String userEmail, List<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.date = date;
        this.registerDate=registerDate;
        this.active = active;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.roles = roles;
    }


}
