package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.entities.Subject;
import com.iktpreobuka.Gradebook.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {
  @Autowired
    SubjectRepository subjectRepository;
    @PostMapping(value="/addSubject")
    public ResponseEntity<Subject> addSubject(@RequestParam String name,@RequestParam Integer numberOfClasses){

        Subject subject=new Subject();
        subject.setName(name);
        subject.setNumberOfClasses(numberOfClasses);
        subjectRepository.save(subject);
        return new ResponseEntity<>(subject, HttpStatus.ACCEPTED);

    }
}
