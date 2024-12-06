package rft.beadando.api.model;

import java.io.Serializable;
import java.util.Objects;

public class GradeId implements Serializable {
    private Long student;
    private Long course;

    public GradeId() {}

    public GradeId(Long student, Long course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeId gradeId = (GradeId) o;
        return Objects.equals(student, gradeId.student) && Objects.equals(course, gradeId.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course);
    }
}