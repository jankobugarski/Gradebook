package com.iktpreobuka.Gradebook.servicies;

import com.iktpreobuka.Gradebook.entities.*;
import com.iktpreobuka.Gradebook.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class GradeDaoImpl implements GradeDao {

    private final SubjectTeacherRepository subjectTeacherRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final GradeRepository gradeRepository;

    @Override
    public Grade insert(Long teacherId, Long subjectId, Long studentId) {
        if (subjectTeacherRepository.canTeacherGrade(teacherId, subjectId, studentId)) {

            //TODO implement grade insert and return newly inserted grade
            Teacher teacher = teacherRepository.findById(teacherId).get();
            Student student = studentRepository.findById(studentId).get();
            Subject subject = subjectRepository.findById(subjectId).get();

            Grade grade = new Grade();
            grade.setStudent(student);
            grade.setSubject(subject);
            grade.setTeacher(teacher);
            gradeRepository.save(grade);

            return grade;
        } else {
            log.error("Teacher: {} can`t grade student: {} + for subject: {}", teacherId, studentId, subjectId);
            return null;

        }
    }

}