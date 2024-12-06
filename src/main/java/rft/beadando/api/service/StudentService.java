package rft.beadando.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rft.beadando.api.Repository.StudentRepository;
import rft.beadando.api.model.Student;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Összes hallgató lekérdezése
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    // Egy hallgató lekérdezése azonosító alapján
    public Optional<Student> findStudentById(int id) {
        return studentRepository.findById(id);
    }

    // Új hallgató mentése
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // Hallgató frissítése
    public Student updateStudent(int id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(updatedStudent.getName());
                    student.setPassword(updatedStudent.getPassword());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    // Hallgató törlése
    public void deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found with id: " + id);
        }
    }
}
