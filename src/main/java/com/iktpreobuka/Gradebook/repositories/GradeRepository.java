package com.iktpreobuka.Gradebook.repositories;

import com.iktpreobuka.Gradebook.entities.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

    @Query(nativeQuery = true,
            value = "SELECT avg(marks) FROM grade g WHERE g.student_id = :student_id and g.subject_id = :subject_id group by student_id")
    Double countAvg(
            @Param("student_id") Long studentId,
            @Param("subject_id") Long subjectId);


    @Query(nativeQuery = true,
            value = "select marks from grade where grade.student_id= :student_id")

      List<Integer> getGrades(@Param("student_id") Long student_id);
}