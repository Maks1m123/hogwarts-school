package ru.hogwarts.school.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Faculty {
    @Id
    @GeneratedValue

    private Long id;
    private String name;
    private String color;

    public Faculty(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Faculty() {
    }

    public Faculty(String color, Long id, String name) {
        this.color = color;
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @OneToMany(mappedBy = "faculty")
     private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}
