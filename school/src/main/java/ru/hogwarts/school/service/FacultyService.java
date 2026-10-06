    package ru.hogwarts.school.service;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import ru.hogwarts.school.model.Faculty;
    import ru.hogwarts.school.repository.FacultyRepository;

    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    @Service
    public class FacultyService {

        private final FacultyRepository facultyRepository;
        @Autowired

        public FacultyService(FacultyRepository facultyRepository) {
            this.facultyRepository = facultyRepository;
        }

        public Faculty addFaculty(Faculty faculty) {
            return facultyRepository.save(faculty);
        }
        public Faculty findFaculty(long id) {
            return facultyRepository.findById(id).orElse(null);
        }
        public Faculty editFaculty(Faculty faculty) {
            return facultyRepository.save(faculty);
        }
        public void removeFaculty(long id) {
            facultyRepository.deleteById(id);
        }
        public List<Faculty> findByColor(String color) {
            return facultyRepository.findByColor(color);
        }
        public List<Faculty> findByNameOrColor (String nameOrColor) {
            return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(nameOrColor,nameOrColor);
        }
    }
