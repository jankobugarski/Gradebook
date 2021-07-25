package com.iktpreobuka.Gradebook.repositories;

import com.iktpreobuka.Gradebook.entities.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {
}
