package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;


import java.util.List;
import org.slf4j.LoggerFactory;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    public Student createStudent(Student student){
        logger.info("Вызван метод для создания студента");
        return studentRepository.save(student);
    }
    public Student findStudent(long id) {
        logger.debug("Метод для поиска студента с id = {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            logger.warn("Студент с id = {} не был найден", id);
        }
        return student;
    }
    public Student addStudent(Student student){
        logger.info("Метод для добавления Студента");
        return studentRepository.save(student);
    }
    public Student editStudent(Student student){
        logger.info("Метод для редактирования Студента = {}", student);
        return studentRepository.save(student);
    }
    public void removeStudent(long id){
        logger.info("Метод для удаления студента с id = {}", id);
        studentRepository.deleteById(id);
    }
    public List<Student> findByAge(int age){
        logger.debug("Метод для поиска студентов по возрасту = {}", age);
        return studentRepository.findByAge(age);
    }
    public List<Student> findByAgeBetween(int min, int max){
        logger.debug("Метод для поиска студентов в возрасте от {} до {}", min, max);
        return studentRepository.findByAgeBetween(min,max);
    }
    public Long getCountAllStudents() {
        logger.debug("getCountAllStudents()");
        return studentRepository.count();
    }
    public Double getAverageAge() {
        logger.debug("Метод для получения среднего возраста студентов");
        return studentRepository.getAvgOfAllStudents();
    }
    public List<Student> getLastFiveStudents(){
        logger.debug("getLastFiveStudents()");
        return studentRepository.getLastFiveStudents();
    }


}
