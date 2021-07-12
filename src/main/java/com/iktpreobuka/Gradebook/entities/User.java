package com.iktpreobuka.Gradebook.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Teacher> teachers;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Admin> admins;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Student> students;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Parent> parents;
}
