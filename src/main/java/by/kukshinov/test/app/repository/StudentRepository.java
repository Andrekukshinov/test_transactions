package by.kukshinov.test.app.repository;

import by.kukshinov.test.app.entity.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> getAll();
}
