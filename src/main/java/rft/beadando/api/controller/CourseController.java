package rft.beadando.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rft.beadando.api.model.Course;
import rft.beadando.api.service.CourseService;

import java.util.List;
import java.util.Optional;

/**
 * REST API Controller a kurzusok kezeléséhez.
 * A "/courses" végpont alatt érhető el.
 */
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    /**
     * Konstruktor az osztály példányosításához, automatikusan befecskendezve a CourseService-t.
     *
     * @param courseService a kurzusok kezeléséért felelős szolgáltatás
     */
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Lekérdezi az összes kurzust.
     * HTTP GET kérés: "/courses"
     *
     * @return a kurzusok listája
     */
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();
    }

    /**
     * Lekérdezi az adott azonosítójú kurzust.
     * HTTP GET kérés: "/courses/{id}"
     *
     * @param id a keresett kurzus azonosítója
     * @return az adott kurzus adatai, ha létezik
     */
    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable int id) {
        return courseService.findCourseById(id);
    }

    /**
     * Új kurzus létrehozása.
     * HTTP POST kérés: "/courses"
     *
     * @param course a létrehozandó kurzus adatai
     * @return a mentett kurzus adatai
     */
    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    /**
     * Meglévő kurzus frissítése az adott azonosító alapján.
     * HTTP PUT kérés: "/courses/{id}"
     *
     * @param id            a frissítendő kurzus azonosítója
     * @param updatedCourse az új kurzus adatai
     * @return a frissített kurzus adatai
     */
    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        return courseService.updateCourse(id, updatedCourse);
    }

    /**
     * Egy adott kurzus törlése az azonosító alapján.
     * HTTP DELETE kérés: "/courses/{id}"
     *
     * @param id a törlendő kurzus azonosítója
     */
    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }
}
