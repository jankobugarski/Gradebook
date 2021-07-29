package com.iktpreobuka.Gradebook.repositories;

import com.iktpreobuka.Gradebook.entities.SubjectTeacher;
import org.hibernate.SQLQuery;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectTeacherRepository extends CrudRepository<SubjectTeacher, Long> {

    @Query(nativeQuery = true,
        value = "select case when count(tc.id) > 0 then 'true' else 'false' end as exist " +
                "from teacher_class tc " +
                "join subject_teacher st on st.teacher_id = tc.teacher_id " +
                "join student s on s.a_class_id = tc.class_id " +
                "where tc.teacher_id = :teacherId " +
                "and st.subject_id = :subjectId " +
                "and s.id = :studentId")
    boolean canTeacherGrade(@Param("teacherId") Long teacherId, @Param("subjectId") Long subjectId,
                            @Param("studentId") Long studentId);

    
}
