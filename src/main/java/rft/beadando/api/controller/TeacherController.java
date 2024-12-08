package rft.beadando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rft.beadando.api.model.Teacher;
import rft.beadando.api.service.TeacherService;

import java.util.List;
import java.util.Optional;

/**
 * REST API Controller a tanárok kezeléséhez.
 * A "/teachers" végpont alatt érhetők el a tanárokkal kapcsolatos műveletek.
 */
@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    /**
     * Konstruktor a Controller osztály példányosításához.
     * Az osztályhoz automatikusan befecskendezésre kerül a TeacherService.
     *
     * @param teacherService a tanárok kezelésére szolgáló szolgáltatás
     */
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    /**
     * Lekérdezi az összes tanárt.
     * HTTP GET kérés: "/teachers"
     *
     * @return a tanárok listája
     */
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.findAllTeachers();
    }

    /**
     * Lekérdezi a tanár adatait az ID alapján.
     * HTTP GET kérés: "/teachers/{id}"
     *
     * @param id a keresett tanár azonosítója
     * @return a tanár adatai, ha létezik
     */
    @GetMapping("/{id}")
    public Optional<Teacher> getTeacherById(@PathVariable int id) {
        return teacherService.findTeacherById(id);
    }

    /**
     * Új tanár létrehozása.
     * HTTP POST kérés: "/teachers"
     *
     * @param teacher a létrehozandó tanár adatai
     * @return a létrehozott tanár adatai
     */
    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    /**
     * Meglévő tanár frissítése az adott azonosító alapján.
     * HTTP PUT kérés: "/teachers/{id}"
     *
     * @param id            a frissítendő tanár azonosítója
     * @param updatedTeacher az új tanári adatok
     * @return a frissített tanár adatai
     */
    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable int id, @RequestBody Teacher updatedTeacher) {
        return teacherService.updateTeacher(id, updatedTeacher);
    }

    /**
     * Tanár törlése az azonosító alapján.
     * HTTP DELETE kérés: "/teachers/{id}"
     *
     * @param id a törlendő tanár azonosítója
     */
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherService.deleteTeacher(id);
    }
}
