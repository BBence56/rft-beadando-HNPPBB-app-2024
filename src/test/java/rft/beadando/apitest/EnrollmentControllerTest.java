package rft.beadando.apitest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import rft.beadando.api.controller.EnrollmentController;
import rft.beadando.api.model.Enrollment;
import rft.beadando.api.service.EnrollmentService;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EnrollmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EnrollmentService enrollmentService;

    @InjectMocks
    private EnrollmentController enrollmentController;

    public EnrollmentControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(enrollmentController).build();
    }

    @Test
    public void testGetAllEnrollments() throws Exception {
        when(enrollmentService.findAllEnrollments()).thenReturn(Arrays.asList(new Enrollment(), new Enrollment()));
        mockMvc.perform(get("/enrollments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testEnrollStudent() throws Exception {
        Enrollment enrollment = new Enrollment();
        when(enrollmentService.saveEnrollment(any(Enrollment.class))).thenReturn(enrollment);
        mockMvc.perform(post("/enrollments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEnrollment() throws Exception {
        doNothing().when(enrollmentService).deleteEnrollment(1L, 1L);
        mockMvc.perform(delete("/enrollments")
                        .param("studentId", "1")
                        .param("courseId", "1"))
                .andExpect(status().isOk());
    }
}
