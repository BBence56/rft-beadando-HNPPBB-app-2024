package rft.beadando.api.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "enrollment")
@IdClass(Enrollment.EnrollmentId.class)
public class Enrollment {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = true)
    private Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Enrollment() {
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static class EnrollmentId implements Serializable {
        private Long student;
        private Long course;

        public EnrollmentId() {}

        public EnrollmentId(Long student, Long course) {
            this.student = student;
            this.course = course;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            EnrollmentId that = (EnrollmentId) o;
            return Objects.equals(student, that.student) && Objects.equals(course, that.course);
        }

        @Override
        public int hashCode() {
            return Objects.hash(student, course);
        }
    }
}