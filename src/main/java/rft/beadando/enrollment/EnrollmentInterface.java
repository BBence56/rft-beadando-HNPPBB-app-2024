package rft.beadando.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import rft.beadando.course.Course;
import rft.beadando.student.Student;
import java.util.List;

public interface EnrollmentInterface extends JpaRepository<Enrollment, Integer> {
    Enrollment findEnrollmentById(int id);
    Enrollment findEnrollmentByStudentAndCourse(Student student, Course course);
    List<Enrollment> findEnrollmentByStudent(Student student);
    List<Enrollment> findEnrollmentByCourse(Course course);
}
