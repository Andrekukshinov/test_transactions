package by.kukshinov.test.app.repository.impl;

import by.kukshinov.test.app.entity.Mark;
import by.kukshinov.test.app.repository.MarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcMarkRepository implements MarkRepository {

    private static final String GET_ALL = "SELECT * FROM Mark";

    private final JdbcTemplate jdbc;
    private final RowMapper<Mark> mapper;

    @Autowired
    public JdbcMarkRepository(JdbcTemplate jdbcTemplate, RowMapper<Mark> mapper) {
        this.jdbc = jdbcTemplate;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public List<Mark> getAll() {
        return jdbc.query(GET_ALL, mapper);
    }
}
