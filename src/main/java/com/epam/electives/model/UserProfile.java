package com.epam.electives.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by rusamaha on 7/29/17.
 */

@Data
@Entity
//@Table(name = "UserProfile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    private Date birthday;
    private String login;
    private String password;
    private Role role;
}
