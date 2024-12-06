package rft.beadando.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rft.beadando.course.Course;
import rft.beadando.student.Student;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    // Minden jegy lekérdezése
    public List<Grade> findAllGrades() {
        return gradeRepository.findAll();
    }

    // Egy adott jegy keresése hallgató és kurzus alapján
    public Optional<Grade> findGradeById(Student student, Course course) {
        GradeId id = new GradeId((long) student.getId(), (long) course.getId());
        return gradeRepository.findById(id);
    }

    // Új jegy hozzáadása
    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    // Jegy frissítése
    public Grade updateGrade(Student student, Course course, int newGrade) {
        GradeId id = new GradeId((long) student.getId(), (long) course.getId());
        return gradeRepository.findById(id)
                .map(existingGrade -> {
                    existingGrade.setGrade(newGrade);
                    return gradeRepository.save(existingGrade);
                })
                .orElseThrow(() -> new RuntimeException("Grade not found for student ID: " + student.getId() +
                        " and course ID: " + course.getId()));
    }

    // Jegy törlése
    public void deleteGrade(Student student, Course course) {
        GradeId id = new GradeId((long) student.getId(), (long) course.getId());
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Grade not found for student ID: " + student.getId() +
                    " and course ID: " + course.getId());
        }
    }
}
