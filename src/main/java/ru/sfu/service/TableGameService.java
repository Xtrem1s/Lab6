package ru.sfu.service;

import ru.sfu.model.TableGame;

import java.util.List;

public interface TableGameService {
    void create(TableGame tg);
    List<TableGame> readAll();
    TableGame read(Integer id);
    boolean update(TableGame tg, Integer id);
    boolean delete(Integer id);
}
