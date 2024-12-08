package rft.beadando.apitest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rft.beadando.api.controller.GradeController;
import rft.beadando.api.model.Grade;
import rft.beadando.api.service.GradeService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GradeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private GradeController gradeController;

    public GradeControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();
    }

    @Test
    public void testGetAllGrades() throws Exception {
        when(gradeService.findAllGrades()).thenReturn(Arrays.asList(new Grade(), new Grade()));
        mockMvc.perform(get("/grades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }


    @Test
    public void testCreateGrade() throws Exception {
        Grade grade = new Grade();
        when(gradeService.saveGrade(any(Grade.class))).thenReturn(grade);
        mockMvc.perform(post("/grades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateGrade() throws Exception {
        Grade grade = new Grade();
        when(gradeService.updateGrade(eq(1L), eq(1L), anyInt())).thenReturn(grade);
        mockMvc.perform(put("/grades")
                        .param("studentId", "1")
                        .param("courseId", "1")
                        .param("newGrade", "90")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteGrade() throws Exception {
        doNothing().when(gradeService).deleteGrade(1L, 1L);
        mockMvc.perform(delete("/grades")
                        .param("studentId", "1")
                        .param("courseId", "1"))
                .andExpect(status().isOk());
    }
}
