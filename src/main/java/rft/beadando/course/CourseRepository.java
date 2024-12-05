package rft.beadando.course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseById(int id);
    Course findCourseByName(String name);
}
