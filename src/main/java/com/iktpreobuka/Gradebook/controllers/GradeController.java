package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.entities.Grade;
import com.iktpreobuka.Gradebook.servicies.GradeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GradeController {

    @Autowired
    GradeDaoImpl gradeDao;

    @PostMapping(value = "/addGrade")
    public ResponseEntity<?> addGrade(@RequestParam Integer marks, @RequestParam Long teacherId, @RequestParam Long subjectId,@RequestParam Long studentId) {

        Grade grade = gradeDao.insert(marks, teacherId, subjectId,studentId);
        return new ResponseEntity<>(grade, HttpStatus.ACCEPTED);
    }

    @GetMapping(value="/avg")
    public  ResponseEntity<?> getAvg(@RequestParam Long studentId,@RequestParam Long subjectId){


       Double avg= gradeDao.avg(studentId,subjectId);
       return new ResponseEntity<>(avg,HttpStatus.OK);
    }

    @GetMapping(value="/getGrades")
    public ResponseEntity<?> getGrades(@RequestParam Long studentId){

        List<Integer> allGrades=gradeDao.getGrade(studentId);
        return new ResponseEntity<>(allGrades,HttpStatus.OK);
    }

}
