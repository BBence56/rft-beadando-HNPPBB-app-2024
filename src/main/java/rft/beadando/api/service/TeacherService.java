package rft.beadando.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rft.beadando.api.repository.TeacherRepository;
import rft.beadando.api.model.Teacher;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Összes tanár lekérdezése
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    // Egy tanár keresése azonosító alapján
    public Optional<Teacher> findTeacherById(int id) {
        return teacherRepository.findById(id);
    }

    // Új tanár mentése
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Tanár frissítése
    public Teacher updateTeacher(int id, Teacher updatedTeacher) {
        return teacherRepository.findById(id)
                .map(teacher -> {
                    teacher.setName(updatedTeacher.getName());
                    teacher.setPassword(updatedTeacher.getPassword());
                    return teacherRepository.save(teacher);
                })
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }

    // Tanár törlése
    public void deleteTeacher(int id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        } else {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
    }
}
