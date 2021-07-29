package com.iktpreobuka.Gradebook.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer numberOfClasses;
    @JsonIgnore
    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<SubjectTeacher> subjectTeachers;
    @JsonIgnore
    @OneToMany(mappedBy = "subject",fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private List<Grade> grades;

}
