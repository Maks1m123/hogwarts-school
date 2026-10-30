package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;
import ru.hogwarts.school.service.StudentService;

import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public StudentController(StudentService studentService, StudentRepository studentRepository) {
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable long id) {
        return studentService.findStudent(id);
    }

    @PutMapping
    public Student putStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable long id) {
        studentService.removeStudent(id);
    }

    @GetMapping("/age/{age}")
    public List<Student> findByAge(@PathVariable int age) {
        return studentService.findByAge(age);
    }
    @GetMapping("/min/max")
    public List<Student> findMinMaxAge(@RequestParam int min, @RequestParam int max) {
        return studentService.findByAgeBetween(min, max);
    }
    @GetMapping("/{id}/faculty")
    public Faculty getStudentFaculty(@PathVariable long id) {
        return studentService.findStudent(id).getFaculty();
    }

    @GetMapping("/A-names")
    public List<String> getAllStudentNamesStartingA(){
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .filter(name -> name != null && (name.startsWith("А") ))
                .map(String::toUpperCase)
                .sorted()
                .toList();

    }
    @GetMapping("/names")
    public List<String> getAllStudentNames(){
        return studentService.getAllStudentsNameStringA();
    }
    @GetMapping("/age/average")
    public double getAverageAge(){
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }
    @GetMapping("sum")
    public long getSum(){
        return IntStream.rangeClosed(1,1_000_000).sum();
    }
}
