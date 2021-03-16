package by.kukshinov.test.app.repository.impl;

import by.kukshinov.test.app.entity.Student;
import by.kukshinov.test.app.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdcbStudentRepository implements StudentRepository {

    private static final String GET_ALL = "SELECT * FROM Student";

    private final JdbcTemplate jdbc;
    private final RowMapper<Student> studentRowMapper;

    @Autowired
    public JdcbStudentRepository(JdbcTemplate jdbcTemplate, RowMapper<Student> studentRowMapper) {
        this.jdbc = jdbcTemplate;
        this.studentRowMapper = studentRowMapper;
    }

    @Override
    public List<Student> getAll() {
        return jdbc.query(GET_ALL, studentRowMapper);
    }
}
