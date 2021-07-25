package com.iktpreobuka.Gradebook.repositories;

import com.iktpreobuka.Gradebook.entities.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student,Long> {


}
