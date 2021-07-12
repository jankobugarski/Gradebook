package com.iktpreobuka.Gradebook.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "aClass",fetch = FetchType.LAZY,cascade =CascadeType.REFRESH)
    private List<Student> students;
}
