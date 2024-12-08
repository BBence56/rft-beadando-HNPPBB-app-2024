package rft.beadando.apitest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rft.beadando.api.controller.CourseController;
import rft.beadando.api.model.Course;
import rft.beadando.api.service.CourseService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CourseControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    public CourseControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    public void testGetAllCourses() throws Exception {
        when(courseService.findAllCourses()).thenReturn(Arrays.asList(new Course(), new Course()));
        mockMvc.perform(get("/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetCourseById() throws Exception {
        Course course = new Course();
        course.setId(1);
        when(courseService.findCourseById(1)).thenReturn(Optional.of(course));
        mockMvc.perform(get("/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }


    @Test
    public void testCreateCourse() throws Exception {
        Course course = new Course();
        when(courseService.saveCourse(any(Course.class))).thenReturn(course);
        mockMvc.perform(post("/courses")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCourse() throws Exception {
        Course course = new Course();
        when(courseService.updateCourse(eq(1), any(Course.class))).thenReturn(course);
        mockMvc.perform(put("/courses/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCourse() throws Exception {
        doNothing().when(courseService).deleteCourse(1);
        mockMvc.perform(delete("/courses/1"))
                .andExpect(status().isOk());
    }
}
