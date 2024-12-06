package rft.beadando.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rft.beadando.api.Repository.EnrollmentRepository;
import rft.beadando.api.model.Enrollment;



import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public void deleteEnrollment(Long studentId, Long courseId) {
        Enrollment.EnrollmentId enrollmentId = new Enrollment.EnrollmentId(studentId, courseId);
        enrollmentRepository.findById(enrollmentId).ifPresent(enrollmentRepository::delete);
    }
}

