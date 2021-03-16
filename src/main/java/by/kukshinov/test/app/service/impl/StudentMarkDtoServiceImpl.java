package by.kukshinov.test.app.service.impl;

import by.kukshinov.test.app.entity.Mark;
import by.kukshinov.test.app.entity.Student;
import by.kukshinov.test.app.entity.StudentStatistics;
import by.kukshinov.test.app.repository.MarkRepository;
import by.kukshinov.test.app.repository.StudentMarkRepository;
import by.kukshinov.test.app.repository.StudentRepository;
import by.kukshinov.test.app.service.StudentMarkDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentMarkDtoServiceImpl implements StudentMarkDtoService {

    private final StudentMarkRepository studentStatisticsRepository;

    @Autowired
    public StudentMarkDtoServiceImpl(StudentMarkRepository studentStatisticsRepository) {
         this.studentStatisticsRepository = studentStatisticsRepository;
    }

    @Override
    public StudentStatistics getStudentStatisticsById(long id) {
        return studentStatisticsRepository.getStatisticsByStudentId(id);
    }

    @Override
    public void save(StudentStatistics studentStatistics) {
        studentStatisticsRepository.saveStudentStatistics(studentStatistics);
    }
}
