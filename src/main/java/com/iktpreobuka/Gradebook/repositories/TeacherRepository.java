package com.iktpreobuka.Gradebook.repositories;

import com.iktpreobuka.Gradebook.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {
}
