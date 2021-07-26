package com.iktpreobuka.Gradebook.controllers;

import com.iktpreobuka.Gradebook.entities.Class;
import com.iktpreobuka.Gradebook.entities.Teacher;
import com.iktpreobuka.Gradebook.entities.TeacherClass;
import com.iktpreobuka.Gradebook.entities.User;
import com.iktpreobuka.Gradebook.repositories.ClassRepository;
import com.iktpreobuka.Gradebook.repositories.TeacherClassRepository;
import com.iktpreobuka.Gradebook.repositories.TeacherRepository;
import com.iktpreobuka.Gradebook.repositories.UserRepository;
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
    @PostMapping(value = "/addTeacher")
    public ResponseEntity<?>addTeacher(@RequestParam String firstName,@RequestParam String lastName){

        Teacher teacher=new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacherRepository.save(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.ACCEPTED);

    }
    @PostMapping(value="/addUserToTeacher")
    public ResponseEntity<?> addUsertoTeacher(@RequestParam Long userId,@RequestParam Long teacherId){
        User user=userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Teacher teacher=teacherRepository.findById(teacherId).orElseThrow( ()->new UsernameNotFoundException("Teacher not found"));
        teacher.setUser(user);
        teacherRepository.save(teacher);

        return new ResponseEntity<>(teacher,HttpStatus.ACCEPTED);

    }
    @PostMapping(value = "/addTeacherToClass")
    public ResponseEntity<?> addTeacherToClass(@RequestParam Long teacherId,@RequestParam Long classId){
        TeacherClass teacherClass =new TeacherClass();

        Teacher teacher =teacherRepository.findById(teacherId).orElseThrow(()->new UsernameNotFoundException("Teacher not found"));
        Class clas=classRepository.findById(classId).orElseThrow(()-> new UsernameNotFoundException("Class not found"));
        teacherClass.setTeacher(teacher);
        teacherClass.setAClass(clas);
        teacherClassRepository.save(teacherClass);
        return new ResponseEntity<>(teacherClass,HttpStatus.ACCEPTED);
    }
}
