package rft.beadando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rft.beadando.api.model.Student;
import rft.beadando.api.service.StudentService;

import java.util.List;
import java.util.Optional;

/**
 * REST API Controller a hallgatók kezeléséhez.
 * A "/students" végpont alatt érhetők el a hallgatókkal kapcsolatos műveletek.
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    /**
     * Konstruktor az osztály példányosításához, automatikusan befecskendezve a StudentService-t.
     *
     * @param studentService a hallgatók kezelésére szolgáló szolgáltatás
     */
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Lekérdezi az összes hallgatót.
     * HTTP GET kérés: "/students"
     *
     * @return a hallgatók listája
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    /**
     * Lekérdezi a hallgató adatait az azonosítója alapján.
     * HTTP GET kérés: "/students/{id}"
     *
     * @param id a hallgató azonosítója
     * @return a hallgató adatai, ha létezik
     */
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable int id) {
        return studentService.findStudentById(id);
    }

    /**
     * Új hallgató létrehozása.
     * HTTP POST kérés: "/students"
     *
     * @param student a létrehozandó hallgató adatai
     * @return a létrehozott hallgató adatai
     */
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    /**
     * Meglévő hallgató frissítése az adott azonosító alapján.
     * HTTP PUT kérés: "/students/{id}"
     *
     * @param id            a frissítendő hallgató azonosítója
     * @param updatedStudent az új hallgatói adatok
     * @return a frissített hallgató adatai
     */
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student updatedStudent) {
        return studentService.updateStudent(id, updatedStudent);
    }

    /**
     * Hallgató törlése az azonosító alapján.
     * HTTP DELETE kérés: "/students/{id}"
     *
     * @param id a törlendő hallgató azonosítója
     */
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }
}
