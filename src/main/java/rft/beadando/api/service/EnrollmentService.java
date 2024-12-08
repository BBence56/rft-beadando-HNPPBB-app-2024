package rft.beadando.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rft.beadando.api.repository.EnrollmentRepository;
import rft.beadando.api.repository.StudentRepository;
import rft.beadando.api.repository.CourseRepository;
import rft.beadando.api.model.Enrollment;
import rft.beadando.api.model.Student;
import rft.beadando.api.model.Course;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        if (enrollment.getStudent() == null || enrollment.getCourse() == null) {
            throw new IllegalArgumentException("Student and Course must not be null");
        }

        Student student = studentRepository.findById(enrollment.getStudent().getId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Course course = courseRepository.findById(enrollment.getCourse().getId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Long studentId, Long courseId) {
        Enrollment.EnrollmentId enrollmentId = new Enrollment.EnrollmentId(studentId, courseId);
        enrollmentRepository.findById(enrollmentId).ifPresent(enrollmentRepository::delete);
    }
}