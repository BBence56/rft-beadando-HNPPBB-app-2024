package rft.beadando.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rft.beadando.api.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherById(int id);
    Teacher findTeacherByName(String name);
}