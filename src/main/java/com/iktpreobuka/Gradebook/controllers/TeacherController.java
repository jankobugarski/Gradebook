package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.entities.*;
import com.iktpreobuka.Gradebook.entities.Class;
import com.iktpreobuka.Gradebook.repositories.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ClassRepository classRepository;
    @Autowired
    TeacherClassRepository teacherClassRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectTeacherRepository subjectTeacherRepository;

    @PostMapping(value = "/addTeacher")
    public ResponseEntity<?> addTeacher(@RequestParam String firstName, @RequestParam String lastName) {

        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacherRepository.save(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.ACCEPTED);

    }

    @PostMapping(value = "/addUserToTeacher")
    public ResponseEntity<?> addUsertoTeacher(@RequestParam Long userId, @RequestParam Long teacherId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new UsernameNotFoundException("Teacher not found"));
        teacher.setUser(user);
        teacherRepository.save(teacher);

        return new ResponseEntity<>(teacher, HttpStatus.ACCEPTED);

    }

    //    @Secured("Teacher")
    @PostMapping(value = "/addTeacherToClass")
    public ResponseEntity<?> addTeacherToClass(@RequestParam Long teacherId, @RequestParam Long classId) {
        TeacherClass teacherClass = new TeacherClass();

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new UsernameNotFoundException("Teacher not found"));
        Class clas = classRepository.findById(classId).orElseThrow(() -> new UsernameNotFoundException("Class not found"));
        teacherClass.setTeacher(teacher);
        teacherClass.setAClass(clas);
        teacherClassRepository.save(teacherClass);
        return new ResponseEntity<>(teacherClass, HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/addTeacherToSubject")
    public ResponseEntity<?> addTeacherToSubject(@RequestParam Long teacherId, @RequestParam Long subjectId) throws NotFoundException {

        SubjectTeacher subjectTeacher = new SubjectTeacher();
        subjectTeacher.setSubject(subjectRepository.findById(subjectId).orElseThrow(()->new NotFoundException("Subject not found")));
        subjectTeacher.setTeacher(teacherRepository.findById(teacherId).orElseThrow(()->new NotFoundException("Teacher not found")));
        subjectTeacherRepository.save(subjectTeacher);
        return new ResponseEntity<>(subjectTeacher,HttpStatus.ACCEPTED);
    }
}
