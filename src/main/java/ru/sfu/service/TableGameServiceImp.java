package ru.sfu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sfu.model.TableGame;
import ru.sfu.repository.TableGameRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TableGameServiceImp implements TableGameService {

    @Autowired
    TableGameRepository repo;

    @Override
    public void create(TableGame tg) {
        repo.save(tg);
    }

    @Override
    public List<TableGame> readAll() {
        return (List<TableGame>) repo.findAll();
    }

    @Override
    public TableGame read(Integer id) {
        Optional<TableGame> tg = repo.findById(id);
        if (tg.isPresent())
            return tg.get();
        return  null;
    }

    @Override
    public boolean update(TableGame tg, Integer id) {
        if (repo.existsById(id)){
            repo.save(tg);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
