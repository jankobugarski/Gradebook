package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.entities.Grade;
import com.iktpreobuka.Gradebook.servicies.GradeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradeController {

    @Autowired
    GradeDaoImpl gradeDao;

    @PostMapping(value = "/addGrade")
    public ResponseEntity<?> addGrade(@RequestParam Long teacherId, @RequestParam Long studentId, @RequestParam Long subjectId) {

        Grade grade = gradeDao.insert(teacherId, studentId, subjectId);
        return new ResponseEntity<>(grade, HttpStatus.ACCEPTED);
    }


}
