package com.iktpreobuka.Gradebook.repositories;

import com.iktpreobuka.Gradebook.entities.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject,Long> {
}
