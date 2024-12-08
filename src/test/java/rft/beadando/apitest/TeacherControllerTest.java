package rft.beadando.apitest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rft.beadando.api.controller.TeacherController;
import rft.beadando.api.model.Teacher;
import rft.beadando.api.service.TeacherService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TeacherControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    public TeacherControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
    }

    @Test
    public void testGetAllTeachers() throws Exception {
        when(teacherService.findAllTeachers()).thenReturn(Arrays.asList(new Teacher(), new Teacher()));
        mockMvc.perform(get("/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetTeacherById() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(1);
        when(teacherService.findTeacherById(1)).thenReturn(Optional.of(teacher));
        mockMvc.perform(get("/teachers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateTeacher() throws Exception {
        Teacher teacher = new Teacher();
        when(teacherService.saveTeacher(any(Teacher.class))).thenReturn(teacher);
        mockMvc.perform(post("/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTeacher() throws Exception {
        Teacher teacher = new Teacher();
        when(teacherService.updateTeacher(eq(1), any(Teacher.class))).thenReturn(teacher);
        mockMvc.perform(put("/teachers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteTeacher() throws Exception {
        doNothing().when(teacherService).deleteTeacher(1);
        mockMvc.perform(delete("/teachers/1"))
                .andExpect(status().isOk());
    }
}
