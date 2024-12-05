package rft.beadando.student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findStudentById(int id);
    Student findStudentByName(String name);
}
