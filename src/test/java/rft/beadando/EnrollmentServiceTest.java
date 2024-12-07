package rft.beadando;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rft.beadando.api.model.Course;
import rft.beadando.api.model.Enrollment;
import rft.beadando.api.model.Student;
import rft.beadando.api.model.Teacher;
import rft.beadando.api.repository.EnrollmentRepository;
import rft.beadando.api.service.EnrollmentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllEnrollments() {
        Student student = new Student(1, "Student 1", "password");
        Course course = new Course(1, "Course 1", new Teacher(1, "Teacher 1", "password"));
        Enrollment enrollment1 = new Enrollment(student, course);
        Enrollment enrollment2 = new Enrollment(student, new Course(2, "Course 2", new Teacher(2, "Teacher 2", "password")));
        List<Enrollment> enrollments = Arrays.asList(enrollment1, enrollment2);

        when(enrollmentRepository.findAll()).thenReturn(enrollments);

        List<Enrollment> result = enrollmentService.findAllEnrollments();
        assertEquals(2, result.size());
        assertEquals(enrollment1, result.get(0));
        assertEquals(enrollment2, result.get(1));
    }

    @Test
    void testSaveEnrollment() {
        Student student = new Student(1, "Student 1", "password");
        Course course = new Course(1, "Course 1", new Teacher(1, "Teacher 1", "password"));
        Enrollment enrollment = new Enrollment(student, course);

        when(enrollmentRepository.save(enrollment)).thenReturn(enrollment);

        Enrollment result = enrollmentService.saveEnrollment(enrollment);
        assertEquals(enrollment, result);
    }

    @Test
    void testDeleteEnrollment() {
        Student student = new Student(1, "Student 1", "password");
        Course course = new Course(1, "Course 1", new Teacher(1, "Teacher 1", "password"));
        Enrollment.EnrollmentId enrollmentId = new Enrollment.EnrollmentId((long) student.getId(), (long) course.getId());
        Enrollment enrollment = new Enrollment(student, course);

        when(enrollmentRepository.findById(enrollmentId)).thenReturn(Optional.of(enrollment));
        doNothing().when(enrollmentRepository).delete(enrollment);

        enrollmentService.deleteEnrollment((long) student.getId(), (long) course.getId());

        verify(enrollmentRepository, times(1)).delete(enrollment);
    }
}