package ru.sfu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sfu.model.TableGame;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableGameRepository extends CrudRepository<TableGame, Integer> {
    List<TableGame> findAllByPriceIsLessThanEqual(int value);

}

