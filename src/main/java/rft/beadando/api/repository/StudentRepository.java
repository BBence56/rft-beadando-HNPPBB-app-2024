package rft.beadando.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rft.beadando.api.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findStudentById(int id);
    Student findStudentByName(String name);
}
