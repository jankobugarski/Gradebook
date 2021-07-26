package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.entities.Class;
import com.iktpreobuka.Gradebook.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {
    @Autowired
    ClassRepository classRepository;
    @PostMapping(value = "/addClass")
    public ResponseEntity<Class> addClass(@RequestParam String className){
        Class clas=new Class();
        clas.setName(className);
        classRepository.save(clas);


        return new ResponseEntity<>(clas, HttpStatus.ACCEPTED);
    }
}
