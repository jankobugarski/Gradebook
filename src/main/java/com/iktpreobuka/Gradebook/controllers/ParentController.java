package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.entities.Parent;
import com.iktpreobuka.Gradebook.entities.Student;
import com.iktpreobuka.Gradebook.repositories.ParentRepository;
import com.iktpreobuka.Gradebook.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParentController {
    @Autowired
    ParentRepository parentRepository;
    @Autowired
    StudentRepository studentRepository;

    @PostMapping(value = "/addParent")
    public ResponseEntity<?> addParent(@RequestBody Parent parent) {

        Parent parent1 = new Parent();
        parent1.setFirstName(parent.getFirstName());
        parent1.setLastName(parent.getLastName());
        parent1.setEmail(parent.getEmail());
        parentRepository.save(parent1);

        return new ResponseEntity<>(parent1, HttpStatus.OK);


    }

    @PostMapping(value = "/addParentToStudent")
    public ResponseEntity<?> addParentToStudent(@RequestParam Long parentId, @RequestParam Long studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new UsernameNotFoundException("Student not found"));
        Parent parent = parentRepository.findById(parentId).orElseThrow(() -> new UsernameNotFoundException("Parent not found"));

        student.setParent(parent);

        studentRepository.save(student);

        return new ResponseEntity<>(student, HttpStatus.OK);

    }
}
