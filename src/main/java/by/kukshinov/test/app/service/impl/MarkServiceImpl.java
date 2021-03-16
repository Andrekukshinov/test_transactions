package by.kukshinov.test.app.service.impl;

import by.kukshinov.test.app.entity.Mark;
import by.kukshinov.test.app.repository.MarkRepository;
import by.kukshinov.test.app.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MarkServiceImpl implements MarkService {

    private final MarkRepository repository;

    @Autowired
    public MarkServiceImpl(MarkRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Mark> getAll() {
        return repository.getAll();
    }
}
