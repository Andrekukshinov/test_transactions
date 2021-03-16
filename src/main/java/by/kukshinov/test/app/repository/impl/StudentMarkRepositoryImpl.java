package by.kukshinov.test.app.repository.impl;

import by.kukshinov.test.app.entity.StudentStatistics;
import by.kukshinov.test.app.repository.StudentMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentMarkRepositoryImpl implements StudentMarkRepository {

    private static final String STUDENT_MARKS =
            "SELECT mark FROM Mark as M  " +
            "INNER JOIN  Student_Marks as S_M ON M.id = S_M.mark_id " +
            "WHERE  S_M.student_id = ?";
    private static final String STUDENT_NAME =
            "SELECT DISTINCT name FROM Student as S " +
            "INNER JOIN  Student_Marks as S_M ON S.id = S_M.student_id " +
            "WHERE  S_M.student_id = ?";
    private static final String SAVE_STUDENT = "INSERT INTO Student(name) VALUES (?)";
    private static final String SAVE_MARK = "INSERT INTO Mark(mark) VALUES (?)";
    private static final String SAVE_STUDENT_STATISTICS = "INSERT INTO Student_Marks(student_id, mark_id) VALUES (?,?)";

    private final JdbcTemplate jdbc;

    @Autowired
    public StudentMarkRepositoryImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public StudentStatistics getStatisticsByStudentId(long studentId) {
        List<Byte> marks = jdbc.queryForList(STUDENT_MARKS, Byte.class, studentId);
        String name = jdbc.queryForObject(STUDENT_NAME, String.class, studentId);
        return new StudentStatistics(name, marks);
    }

    @Override
    @Transactional()
    public void saveStudentStatistics(StudentStatistics studentStatistics) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Long id = saveStudentAndGetId(studentStatistics, keyHolder);

        List<Number> keys = saveMarksAndGetIds(studentStatistics, keyHolder);

        saveStatistics(id, keys);
    }

    private void saveStatistics(Number key, List<Number> keys) {
        keys.stream().forEach(markId ->
                jdbc.update(SAVE_STUDENT_STATISTICS, key, markId)
        );
    }


    private List<Number> saveMarksAndGetIds(StudentStatistics studentStatistics, KeyHolder keyHolder) {
        List<Byte> marks = studentStatistics.getMarks();
        PreparedStatementCreatorFactory markPscFactory = new PreparedStatementCreatorFactory(
                SAVE_MARK, Types.SMALLINT
        );
        markPscFactory.setReturnGeneratedKeys(true);
        List<Number> keys = marks.stream().map(mark -> {
            PreparedStatementCreator psc = markPscFactory.newPreparedStatementCreator(List.of(mark));
            jdbc.update(psc, keyHolder);
            return keyHolder.getKey();
        }).collect(Collectors.toList());
        return keys;
    }

    private Long saveStudentAndGetId(StudentStatistics studentStatistics, KeyHolder keyHolder) {
        String name = studentStatistics.getName();
        PreparedStatementCreatorFactory creatorFactory = new PreparedStatementCreatorFactory(
                SAVE_STUDENT, Types.VARCHAR
        );
        creatorFactory.setReturnGeneratedKeys(true);
        PreparedStatementCreator studentPsc = creatorFactory.newPreparedStatementCreator(List.of(name));

        jdbc.update(studentPsc, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
