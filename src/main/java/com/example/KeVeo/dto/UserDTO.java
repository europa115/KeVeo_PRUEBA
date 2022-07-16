package com.example.KeVeo.dto;

import com.example.KeVeo.data.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {

    private Integer id;
    private String userName;
    private String password;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private boolean active;
    private String userSurname;
    private String userEmail;
    private List <Role>roles=new ArrayList<>();


    //****************************************CONSTRUCTORS**************************************************************

    public UserDTO(String userName, String password, Date date, boolean active, String userSurname, String userEmail, List<Role> roles) {
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
