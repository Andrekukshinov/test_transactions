package by.kukshinov.test.app.repository;

import by.kukshinov.test.app.entity.StudentStatistics;

public interface StudentMarkRepository {
    StudentStatistics getStatisticsByStudentId(long studentId);

    void saveStudentStatistics(StudentStatistics studentStatistics);
}
