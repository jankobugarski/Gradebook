package com.iktpreobuka.Gradebook.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Parent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;

    private String lastName;

    private String email;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "parent",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Student>students;
}
