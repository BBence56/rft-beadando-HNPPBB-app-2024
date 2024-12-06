package rft.beadando.enrollment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rft.beadando.course.Course;
import rft.beadando.student.Student;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentInterface enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentInterface enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> findEnrollmentById(Student student, Course course) {
        Enrollment.EnrollmentId id = new Enrollment.EnrollmentId((long) student.getId(), (long) course.getId());
        return enrollmentRepository.findById(id);
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Student student, Course course) {
        Enrollment.EnrollmentId id = new Enrollment.EnrollmentId((long) student.getId(), (long) course.getId());
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Enrollment not found for student ID: " + student.getId() +
                    " and course ID: " + course.getId());
        }
    }
}
