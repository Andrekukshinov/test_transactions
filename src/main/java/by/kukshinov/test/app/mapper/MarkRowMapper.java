package by.kukshinov.test.app.mapper;

import by.kukshinov.test.app.entity.Mark;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MarkRowMapper implements RowMapper<Mark> {

    private static final String ID = "id";
    private static final String MARK = "mark";

    @Override
    public Mark mapRow(ResultSet resultSet, int i) throws SQLException {
        long id = resultSet.getLong(ID);
        byte mark = resultSet.getByte(MARK);
        return new Mark(id, mark);
    }
}
