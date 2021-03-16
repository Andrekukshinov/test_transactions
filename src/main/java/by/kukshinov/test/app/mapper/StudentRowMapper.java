package by.kukshinov.test.app.mapper;

import by.kukshinov.test.app.entity.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class StudentRowMapper implements RowMapper<Student> {

    private static final String ID = "id";
    private static final String NAME = "name";

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        return new Student(id, name);
    }
}
