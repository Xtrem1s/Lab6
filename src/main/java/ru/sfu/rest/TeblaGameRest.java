package ru.sfu.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sfu.model.TableGame;
import ru.sfu.service.TableGameService;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class TeblaGameRest {

    @Autowired
    TableGameService service;

    @PostMapping()
    public ResponseEntity<?> addGame(@RequestBody TableGame tg){
        service.create(tg);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TableGame>> showGames(){
        List<TableGame> tableGames = service.readAll();

        return tableGames != null &&  !tableGames.isEmpty()
                ? new ResponseEntity<>(tableGames, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TableGame> read(@PathVariable(name = "id") int id) {
        final TableGame tg = service.read(id);

        return tg != null
                ? new ResponseEntity<>(tg, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable("id") int id,
                             @RequestBody TableGame tg){
        tg.setId(id);
        final boolean updated = service.update(tg, id);
        return  updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteGame(@PathVariable("id") int id){
        final boolean deleted = service.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
