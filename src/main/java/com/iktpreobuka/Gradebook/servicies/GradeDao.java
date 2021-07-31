package com.iktpreobuka.Gradebook.servicies;

import com.iktpreobuka.Gradebook.entities.Grade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface GradeDao {
    @PersistenceContext
    Grade insert(Integer marks, Long studentId, Long teacherId, Long subjectId);

    Double avg(Long studentId, Long parentId, Long subjectId);

    List<Integer> getGrade(Long studentId, Long parentId);

    List<Integer> getGradeSubject(Long studentId, Long parentId, Long subjectId);


}
