package rft.beadando.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }

    public Optional<Grade> findGradeById(Long studentId, Long courseId) {
        GradeId gradeId = new GradeId(studentId, courseId);
        return gradeRepository.findById(gradeId);
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Long studentId, Long courseId, int newGrade) {
        GradeId gradeId = new GradeId(studentId, courseId);
        return gradeRepository.findById(gradeId)
                .map(grade -> {
                    grade.setGrade(newGrade);
                    return gradeRepository.save(grade);
                })
                .orElseThrow(() -> new RuntimeException("Grade not found for studentId: " + studentId + " and courseId: " + courseId));
    }

    public void deleteGrade(Long studentId, Long courseId) {
        GradeId gradeId = new GradeId(studentId, courseId);
        gradeRepository.findById(gradeId).ifPresent(gradeRepository::delete);
    }
}
