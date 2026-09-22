package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @GetMapping("{id}")
    public Faculty getFaculty(@PathVariable long id) {
        return facultyService.findFaculty(id);
    }

    @PutMapping
    public Faculty putFaculty(@RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @PutMapping("{id}")
    public Faculty putFacultyById(@PathVariable long id, @RequestBody Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable long id) {
        facultyService.removeFaculty(id);
    }
    @GetMapping("/color/{color}")
    public List<Faculty> findByColor(@PathVariable String color){
        return facultyService.findByColor(color);
    }
    @GetMapping
    public List<Faculty> findByNameOrColor(@RequestParam String nameOrColor){
        return facultyService.findByNameOrColor(nameOrColor);
    }
    @GetMapping("/{id}/students")
    public List<Student> getStudents(@PathVariable long id){
        return facultyService.findFaculty(id).getStudents();
    }
}

