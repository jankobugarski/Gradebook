package com.iktpreobuka.Gradebook.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class TeacherClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;
}
