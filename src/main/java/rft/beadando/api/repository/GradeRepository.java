package rft.beadando.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rft.beadando.api.model.Course;
import rft.beadando.api.model.Grade;
import rft.beadando.api.model.GradeId;
import rft.beadando.api.model.Student;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    Grade findGradeByStudentAndCourse(Student student, Course course);
}