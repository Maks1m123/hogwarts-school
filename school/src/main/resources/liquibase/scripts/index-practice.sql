-- liquibase formatted sql

-- changeset Maksym:1
CREATE INDEX idx_student_name ON student(name);

-- changeset Maksym:2
CREATE INDEX idx_faculty_name_color ON faculty (name,color);