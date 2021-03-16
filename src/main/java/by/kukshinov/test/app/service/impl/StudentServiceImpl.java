package by.kukshinov.test.app.service.impl;

import by.kukshinov.test.app.entity.Student;
import by.kukshinov.test.app.repository.StudentRepository;
import by.kukshinov.test.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Autowired
    public StudentServiceImpl(StudentRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Student> getAll() {
        return repository.getAll();
    }
}
