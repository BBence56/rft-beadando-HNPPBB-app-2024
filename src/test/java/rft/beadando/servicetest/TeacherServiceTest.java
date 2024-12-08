package rft.beadando.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rft.beadando.api.model.Teacher;
import rft.beadando.api.repository.TeacherRepository;
import rft.beadando.api.service.TeacherService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllTeachers() {
        Teacher teacher1 = new Teacher(1, "Teacher 1", "password1");
        Teacher teacher2 = new Teacher(2, "Teacher 2", "password2");
        List<Teacher> teachers = Arrays.asList(teacher1, teacher2);

        when(teacherRepository.findAll()).thenReturn(teachers);

        List<Teacher> result = teacherService.findAllTeachers();
        assertEquals(2, result.size());
        assertEquals("Teacher 1", result.get(0).getName());
        assertEquals("Teacher 2", result.get(1).getName());
    }

    @Test
    void testFindTeacherById() {
        Teacher teacher = new Teacher(1, "Teacher 1", "password1");

        when(teacherRepository.findById(1)).thenReturn(Optional.of(teacher));

        Optional<Teacher> result = teacherService.findTeacherById(1);
        assertTrue(result.isPresent());
        assertEquals("Teacher 1", result.get().getName());
    }

    @Test
    void testSaveTeacher() {
        Teacher teacher = new Teacher(1, "Teacher 1", "password1");

        when(teacherRepository.save(teacher)).thenReturn(teacher);

        Teacher result = teacherService.saveTeacher(teacher);
        assertEquals("Teacher 1", result.getName());
    }

    @Test
    void testUpdateTeacher() {
        Teacher existingTeacher = new Teacher(1, "Teacher 1", "password1");
        Teacher updatedTeacher = new Teacher(1, "Updated Teacher", "password1");

        when(teacherRepository.findById(1)).thenReturn(Optional.of(existingTeacher));
        when(teacherRepository.save(existingTeacher)).thenReturn(existingTeacher);

        Teacher result = teacherService.updateTeacher(1, updatedTeacher);
        assertEquals("Updated Teacher", result.getName());
    }

    @Test
    void testDeleteTeacher() {
        when(teacherRepository.existsById(1)).thenReturn(true);
        doNothing().when(teacherRepository).deleteById(1);

        teacherService.deleteTeacher(1);

        verify(teacherRepository, times(1)).deleteById(1);
    }
}