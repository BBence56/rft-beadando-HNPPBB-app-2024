package rft.beadando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rft.beadando.api.model.Enrollment;
import rft.beadando.api.service.EnrollmentService;

import java.util.List;

/**
 * REST API Controller a beiratkozások kezeléséhez.
 * A "/enrollments" végpont alatt érhetők el az beiratkozással kapcsolatos műveletek.
 */
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    /**
     * Konstruktor a Controller osztály példányosításához.
     * Az EnrollmentService automatikusan befecskendezésre kerül a Spring keretrendszer által.
     *
     * @param enrollmentService a beiratkozások kezelésére szolgáló szolgáltatás
     */
    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * Lekérdezi az összes beiratkozást.
     * HTTP GET kérés: "/enrollments"
     *
     * @return a beiratkozások listája
     */
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.findAllEnrollments();
    }

    /**
     * Új beiratkozás hozzáadása.
     * HTTP POST kérés: "/enrollments"
     *
     * @param enrollment a beiratkozási adatok
     * @return az új beiratkozás
     */
    @PostMapping
    public Enrollment enrollStudent(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    /**
     * Egy adott beiratkozás törlése a hallgató és a kurzus azonosítói alapján.
     * HTTP DELETE kérés: "/enrollments?studentId={studentId}&courseId={courseId}"
     *
     * @param studentId a hallgató azonosítója
     * @param courseId a kurzus azonosítója
     */
    @DeleteMapping
    public void deleteEnrollment(@RequestParam Long studentId, @RequestParam Long courseId) {
        enrollmentService.deleteEnrollment(studentId, courseId);
    }
}
