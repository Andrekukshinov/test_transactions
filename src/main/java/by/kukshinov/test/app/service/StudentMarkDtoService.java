package by.kukshinov.test.app.service;

import by.kukshinov.test.app.entity.StudentStatistics;

public interface StudentMarkDtoService {

    StudentStatistics getStudentStatisticsById(long id);

    void save(StudentStatistics studentStatistics);
}
