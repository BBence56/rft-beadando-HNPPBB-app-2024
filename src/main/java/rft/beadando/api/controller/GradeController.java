package rft.beadando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rft.beadando.api.model.Grade;
import rft.beadando.api.service.GradeService;

import java.util.List;
import java.util.Optional;

/**
 * REST API Controller az osztályzatok kezeléséhez.
 * A "/grades" végpont alatt érhetők el az osztályzatokkal kapcsolatos műveletek.
 */
@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    /**
     * Konstruktor az osztály példányosításához, automatikusan befecskendezve a GradeService-t.
     *
     * @param gradeService a GradeService, amely az osztályzatok kezeléséért felelős.
     */
    @Autowired
    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    /**
     * Lekérdezi az összes osztályzatot.
     * HTTP GET kérés: "/grades"
     *
     * @return a rendszer összes osztályzata
     */
    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.findAllGrades();
    }

    /**
     * Lekérdezi a hallgató osztályzatát a kurzus azonosítójával és hallgató azonosítójával.
     * HTTP GET kérés: "/grades/{studentId}/{courseId}"
     *
     * @param studentId a hallgató azonosítója
     * @param courseId a kurzus azonosítója
     * @return a hallgató osztályzata adott kurzushoz
     */
    @GetMapping("/{studentId}/{courseId}")
    public Optional<Grade> getGradeById(@RequestParam Long studentId, @RequestParam Long courseId) {
        return gradeService.findGradeById(studentId, courseId);
    }

    /**
     * Új osztályzat létrehozása.
     * HTTP POST kérés: "/grades"
     *
     * @param grade az új osztályzat adatainak JSON formátumban
     * @return a létrehozott osztályzat
     */
    @PostMapping
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeService.saveGrade(grade);
    }

    /**
     * Egy adott osztályzat frissítése.
     * HTTP PUT kérés: "/grades"
     *
     * @param studentId a hallgató azonosítója
     * @param courseId a kurzus azonosítója
     * @param newGrade az új osztályzat
     * @return a frissített osztályzat
     */
    @PutMapping
    public Grade updateGrade(@RequestParam Long studentId, @RequestParam Long courseId, @RequestParam int newGrade) {
        return gradeService.updateGrade(studentId, courseId, newGrade);
    }

    /**
     * Egy adott osztályzat törlése.
     * HTTP DELETE kérés: "/grades"
     *
     * @param studentId a hallgató azonosítója
     * @param courseId a kurzus azonosítója
     */
    @DeleteMapping
    public void deleteGrade(@RequestParam Long studentId, @RequestParam Long courseId) {
        gradeService.deleteGrade(studentId, courseId);
    }
}
