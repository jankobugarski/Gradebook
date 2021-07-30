package com.iktpreobuka.Gradebook.servicies;

import com.iktpreobuka.Gradebook.entities.*;
import com.iktpreobuka.Gradebook.repositories.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Grade insert(Integer marks, Long teacherId, Long subjectId, Long studentId) {
        if (subjectTeacherRepository.canTeacherGrade(teacherId, subjectId, studentId)) {


            Teacher teacher = teacherRepository.findById(teacherId).get();
            Student student = studentRepository.findById(studentId).get();
            Subject subject = subjectRepository.findById(subjectId).get();

            Grade grade = new Grade();
            grade.setMarks(marks);
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


    public Double avg(Long studentId, Long subjectId) {
        Double avg = gradeRepository.countAvg(studentId, subjectId);
        return avg;
    }

    @Override
    public List<Integer> getGrade(Long studentId) {
        List<Integer> grd = gradeRepository.getGrades(studentId);
        return grd;
    }

}