package rft.beadando.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import rft.beadando.course.Course;
import rft.beadando.student.Student;

public interface EnrollmentInterface extends JpaRepository<Enrollment, Enrollment.EnrollmentId> {
    Enrollment findEnrollmentByStudentAndCourse(Student student, Course course);
}