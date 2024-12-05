package rft.beadando.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherById(int id);
    Teacher findTeacherByName(String name);
}