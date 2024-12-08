package rft.beadando.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rft.beadando.api.model.Course;
import rft.beadando.api.model.Teacher;
import rft.beadando.api.repository.CourseRepository;
import rft.beadando.api.service.CourseService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCourses() {
        Teacher teacher = new Teacher(1, "Teacher 1");
        Course course1 = new Course(1, "Course 1", teacher);
        Course course2 = new Course(2, "Course 2", teacher);
        List<Course> courses = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courses);

        List<Course> result = courseService.findAllCourses();
        assertEquals(2, result.size());
        assertEquals("Course 1", result.get(0).getName());
        assertEquals("Course 2", result.get(1).getName());
    }

    @Test
    void testFindCourseById() {
        Teacher teacher = new Teacher(1, "Teacher 1");
        Course course = new Course(1, "Course 1", teacher);

        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        Optional<Course> result = courseService.findCourseById(1);
        assertTrue(result.isPresent());
        assertEquals("Course 1", result.get().getName());
    }

    @Test
    void testSaveCourse() {
        Teacher teacher = new Teacher(1, "Teacher 1");
        Course course = new Course(1, "Course 1", teacher);

        when(courseRepository.save(course)).thenReturn(course);

        Course result = courseService.saveCourse(course);
        assertEquals("Course 1", result.getName());
    }

    @Test
    void testUpdateCourse() {
        Teacher teacher = new Teacher(1, "Teacher 1");
        Course existingCourse = new Course(1, "Course 1", teacher);
        Course updatedCourse = new Course(1, "Updated Course", teacher);

        when(courseRepository.findById(1)).thenReturn(Optional.of(existingCourse));
        when(courseRepository.save(existingCourse)).thenReturn(existingCourse);

        Course result = courseService.updateCourse(1, updatedCourse);
        assertEquals("Updated Course", result.getName());
    }

    @Test
    void testDeleteCourse() {
        when(courseRepository.existsById(1)).thenReturn(true);
        doNothing().when(courseRepository).deleteById(1);

        courseService.deleteCourse(1);

        verify(courseRepository, times(1)).deleteById(1);
    }
}