package com.iktpreobuka.Gradebook.entities;

import com.iktpreobuka.Gradebook.enums.Marks;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Marks marks;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name="teacher_id")
    private Teacher teacher;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name="student_id")
    private Student student;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "subject_id")
    private Subject subject;

}
