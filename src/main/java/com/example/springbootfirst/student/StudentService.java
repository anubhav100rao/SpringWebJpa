package com.example.springbootfirst.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// @Component annotation makes the class a bean which can be autowired in other classes

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        if(!studentRepository.existsById(studentId)) {
            throw new IllegalStateException("No such id exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(
                        () -> new IllegalStateException("no such student exists")
                );

        if(email != null && studentRepository.findStudentByEmail(email).isPresent()) {
            throw new IllegalStateException("email taken or email/name given is not valid");
        }
        if(name != null)
            student.setName(name);
        if(email != null)
            student.setEmail(email);
    }
}
