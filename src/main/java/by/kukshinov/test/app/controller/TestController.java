package by.kukshinov.test.app.controller;


import by.kukshinov.test.app.entity.Mark;
import by.kukshinov.test.app.entity.Student;
import by.kukshinov.test.app.entity.StudentStatistics;
import by.kukshinov.test.app.service.MarkService;
import by.kukshinov.test.app.service.StudentMarkDtoService;
import by.kukshinov.test.app.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final StudentService studentService;
    private final MarkService markService;
    private final StudentMarkDtoService dtoService;

    @Autowired
    public TestController(StudentService studentService, MarkService markService, StudentMarkDtoService dtoService) {
        this.studentService = studentService;
        this.markService = markService;
        this.dtoService = dtoService;
    }

    @GetMapping
    public String getMsg() {
        return "test controller";
    }

    @GetMapping("/students/all")
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

    @GetMapping("/marks/all")
    public List<Mark> getAllMarks() {

        return markService.getAll();
    }

    @GetMapping("/dto/{id}")
    public StudentStatistics getDto(@PathVariable Long id) {
        return dtoService.getStudentStatisticsById(id);
    }

    @PostMapping
    public void save(@RequestBody StudentStatistics studentStatistics) {
        dtoService.save(studentStatistics);
    }

}
// TODO: 15.03.2021 connection pool (done)

// TODO: 15.03.2021 tests 4 repo(integr) + unit
// TODO: 15.03.2021  error handling
// TODO: 15.03.2021 modules wtf????????????
// TODO: 15.03.2021 get db data from resources
