package com.iktpreobuka.Gradebook.servicies;

import com.iktpreobuka.Gradebook.entities.Grade;

public interface GradeDao {

    Grade insert(Long studentId, Long teacherId, Long subjectId);
}
