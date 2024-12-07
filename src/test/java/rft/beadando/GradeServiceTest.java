package rft.beadando;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rft.beadando.api.model.Course;
import rft.beadando.api.model.Grade;
import rft.beadando.api.model.GradeId;
import rft.beadando.api.model.Student;
import rft.beadando.api.repository.GradeRepository;
import rft.beadando.api.service.GradeService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllGrades() {
        Grade grade1 = new Grade(new Student(), new Course(), 90);
        Grade grade2 = new Grade(new Student(), new Course(), 85);
        when(gradeRepository.findAll()).thenReturn(Arrays.asList(grade1, grade2));

        List<Grade> grades = gradeService.findAllGrades();

        assertEquals(2, grades.size());
        verify(gradeRepository, times(1)).findAll();
    }

    @Test
    void testFindGradeById() {
        Grade grade = new Grade(new Student(), new Course(), 90);
        GradeId gradeId = new GradeId(1L, 1L);
        when(gradeRepository.findById(gradeId)).thenReturn(Optional.of(grade));

        Optional<Grade> foundGrade = gradeService.findGradeById(1L, 1L);

        assertTrue(foundGrade.isPresent());
        assertEquals(90, foundGrade.get().getGrade());
        verify(gradeRepository, times(1)).findById(gradeId);
    }

    @Test
    void testSaveGrade() {
        Grade grade = new Grade(new Student(), new Course(), 90);
        when(gradeRepository.save(grade)).thenReturn(grade);

        Grade savedGrade = gradeService.saveGrade(grade);

        assertNotNull(savedGrade);
        assertEquals(90, savedGrade.getGrade());
        verify(gradeRepository, times(1)).save(grade);
    }

    @Test
    void testUpdateGrade() {
        Grade grade = new Grade(new Student(), new Course(), 90);
        GradeId gradeId = new GradeId(1L, 1L);
        when(gradeRepository.findById(gradeId)).thenReturn(Optional.of(grade));
        when(gradeRepository.save(grade)).thenReturn(grade);

        Grade updatedGrade = gradeService.updateGrade(1L, 1L, 95);

        assertNotNull(updatedGrade);
        assertEquals(95, updatedGrade.getGrade());
        verify(gradeRepository, times(1)).findById(gradeId);
        verify(gradeRepository, times(1)).save(grade);
    }

    @Test
    void testDeleteGrade() {
        Grade grade = new Grade(new Student(), new Course(), 90);
        GradeId gradeId = new GradeId(1L, 1L);
        when(gradeRepository.findById(gradeId)).thenReturn(Optional.of(grade));

        gradeService.deleteGrade(1L, 1L);

        verify(gradeRepository, times(1)).findById(gradeId);
        verify(gradeRepository, times(1)).delete(grade);
    }
}