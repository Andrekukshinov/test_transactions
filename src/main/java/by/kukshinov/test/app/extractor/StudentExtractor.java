package by.kukshinov.test.app.extractor;

import by.kukshinov.test.app.entity.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentExtractor implements ResultSetExtractor<List<Student>> {

    private static final String ID = "student_id";
    private static final String MARK_ID = "mark_id";
    private static final String MARK = "mark";

    @Override
    public List<Student> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, Student> students = new HashMap<>();
        Student student;
        while(resultSet.next()){
            long id = resultSet.getLong(ID);
            if(!students.containsKey(id)) {
                //mapping student here
                student = new Student(id, "none");
                students.put(id, student);
            }
            long markId = resultSet.getLong(MARK_ID);
            if (markId > 0) {
                byte mark = resultSet.getByte(MARK);
                //add mark to student
            }

        }
        return null;
    }
}
