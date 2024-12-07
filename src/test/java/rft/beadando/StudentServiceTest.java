package rft.beadando;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rft.beadando.api.model.Student;
import rft.beadando.api.repository.StudentRepository;
import rft.beadando.api.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllStudents() {
        Student student1 = new Student(1, "Student 1", "password1");
        Student student2 = new Student(2, "Student 2", "password2");
        List<Student> students = Arrays.asList(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);

        List<Student> result = studentService.findAllStudents();
        assertEquals(2, result.size());
        assertEquals("Student 1", result.get(0).getName());
        assertEquals("Student 2", result.get(1).getName());
    }

    @Test
    void testFindStudentById() {
        Student student = new Student(1, "Student 1", "password1");

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        Optional<Student> result = studentService.findStudentById(1);
        assertTrue(result.isPresent());
        assertEquals("Student 1", result.get().getName());
    }

    @Test
    void testSaveStudent() {
        Student student = new Student(1, "Student 1", "password1");

        when(studentRepository.save(student)).thenReturn(student);

        Student result = studentService.saveStudent(student);
        assertEquals("Student 1", result.getName());
    }

    @Test
    void testUpdateStudent() {
        Student existingStudent = new Student(1, "Student 1", "password1");
        Student updatedStudent = new Student(1, "Updated Student", "password1");

        when(studentRepository.findById(1)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(existingStudent);

        Student result = studentService.updateStudent(1, updatedStudent);
        assertEquals("Updated Student", result.getName());
    }

    @Test
    void testDeleteStudent() {
        when(studentRepository.existsById(1)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1);

        studentService.deleteStudent(1);

        verify(studentRepository, times(1)).deleteById(1);
    }
}