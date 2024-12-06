package rft.beadando.grade;

import org.springframework.data.jpa.repository.JpaRepository;
import rft.beadando.course.Course;
import rft.beadando.student.Student;

public interface GradeRepository extends JpaRepository<Grade, GradeId> {
    Grade findGradeByStudentAndCourse(Student student, Course course);
}