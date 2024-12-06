package rft.beadando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rft.beadando.grade.Grade;
import rft.beadando.grade.GradeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.findAllGrades();
    }

    @GetMapping
    public Optional<Grade> getGradeById(@RequestParam Long studentId, @RequestParam Long courseId) {
        return gradeService.findGradeById(studentId, courseId);
    }

    @PostMapping
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeService.saveGrade(grade);
    }

    @PutMapping
    public Grade updateGrade(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam int newGrade) {
        return gradeService.updateGrade(studentId, courseId, newGrade);
    }

    @DeleteMapping
    public void deleteGrade(@RequestParam Long studentId, @RequestParam Long courseId) {
        gradeService.deleteGrade(studentId, courseId);
    }
}
