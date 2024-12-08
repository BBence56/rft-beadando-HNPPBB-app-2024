package rft.beadando.apitest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rft.beadando.api.controller.StudentController;
import rft.beadando.api.model.Student;
import rft.beadando.api.service.StudentService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    public StudentControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testGetAllStudents() throws Exception {
        when(studentService.findAllStudents()).thenReturn(Arrays.asList(new Student(), new Student()));
        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetStudentById() throws Exception {
        Student student = new Student();
        student.setId(1);
        when(studentService.findStudentById(1)).thenReturn(Optional.of(student));
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student();
        when(studentService.saveStudent(any(Student.class))).thenReturn(student);
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student();
        when(studentService.updateStudent(eq(1), any(Student.class))).thenReturn(student);
        mockMvc.perform(put("/students/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteStudent() throws Exception {
        doNothing().when(studentService).deleteStudent(1);
        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isOk());
    }
}
