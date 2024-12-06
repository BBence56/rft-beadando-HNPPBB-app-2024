package rft.beadando.api.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rft.beadando.api.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseById(int id);
    Course findCourseByName(String name);
}
