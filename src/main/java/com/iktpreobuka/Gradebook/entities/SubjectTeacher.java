package com.iktpreobuka.Gradebook.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SubjectTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    private Teacher teacher;

}
