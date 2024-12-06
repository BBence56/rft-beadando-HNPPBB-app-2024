package rft.beadando.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rft.beadando.api.model.Course;
import rft.beadando.api.model.Enrollment;
import rft.beadando.api.model.Student;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Enrollment.EnrollmentId> {
    Enrollment findEnrollmentByStudentAndCourse(Student student, Course course);
}