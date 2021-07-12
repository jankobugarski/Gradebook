package com.iktpreobuka.Gradebook.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer numberOfClasses;
    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<SubjectTeacher> subjectTeachers;

}
