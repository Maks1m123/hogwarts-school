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
                .filter(name -> name != null && name.startsWith("А"))
                .map(String::toUpperCase)
                .sorted()
                .toList();
    }

    @GetMapping("/names")
    public List<String> getAllStudentNames(){
        return studentService.getAllStudentsNameStringA();
    }

    @GetMapping("/age/average")
    public double getAverageAgeFromRepository(){
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0.0);
    }
    @GetMapping("/print-parallel")
    public void printStudentsParallel(){
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()){
            return;
        }
        if(students.size() > 0){
            System.out.println(students.get(0).getName());
        }
        if (students.size() > 1){
            System.out.println(students.get(1).getName());
        }
        Thread thread1 = new Thread(()->{
            if(students.size() > 2){
                System.out.println(students.get(2).getName());
            }
            if(students.size() > 3){
                System.out.println(students.get(3).getName());
            }
        });
        thread1.start();
        Thread thread2 = new Thread(()->{
            if(students.size() > 4){
                System.out.println(students.get(4).getName());
            }
            if(students.size() > 5){
                System.out.println(students.get(5).getName());
            }
        });
        thread2.start();
    }
    private synchronized void printStudentName(Student student) {
        if (student != null) {
            System.out.println(student.getName());
        }
    }
    @GetMapping("/print-synchronized")
    public void printSynchronizedStudents(){
        List<Student> students = studentRepository.findAll();
        if(students.isEmpty()){
            return;
        }
        if(students.size() > 0){
            printStudentName(students.get(0));
        }
        if(students.size() > 1){
            printStudentName(students.get(1));
        }
        Thread thread1 = new Thread(()->{
            if(students.size() > 2){
                printStudentName(students.get(2));
            }
            if(students.size() > 3){
                printStudentName(students.get(3));
            }
        });
        thread1.start();
        Thread thread2 = new Thread(()->{
            if(students.size() > 4){
                printStudentName(students.get(4));
            }
            if(students.size() > 5){
                printStudentName(students.get(5));
            }
        });
        thread2.start();
    }


    @GetMapping("/sum")
    public long getSum() {
        return IntStream.rangeClosed(1, 1_000_000).sum();
    }

    @GetMapping("/count")
    public Long getCountAllStudents() {
        return studentService.getCountAllStudents();
    }

    @GetMapping("/average-age")
    public Double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/last-five")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }
}