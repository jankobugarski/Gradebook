package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.entities.Student;
import com.iktpreobuka.Gradebook.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping(value="/addStudent")
    public ResponseEntity<Student> addStudent(@RequestParam String firstName,@RequestParam String lastName){

        Student student=new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        studentRepository.save(student);
        return new ResponseEntity<>(student, HttpStatus.ACCEPTED);
    }
    @RequestMapping(value="/addClassToStudent")
    public void addClassToStudent(@RequestParam Long studentId,@RequestParam Long classId){

        Student student=studentRepository.findById(studentId).orElseThrow( ()-> new UsernameNotFoundException("Student not found"));
        student.setId(classId);
        studentRepository.save(student);
    }
}
