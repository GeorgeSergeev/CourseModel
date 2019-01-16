package ru.latypov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.latypov.exception.KursNotFound;
import ru.latypov.model.Kurs;
import ru.latypov.service.KursService;

import java.util.List;


/**
 * Контролер для  api/kurs.
 */


@RestController
@RequestMapping("api/kurs")
public class KursController {
    @Autowired
    private KursService kursService;

    /**
     * Слушаем /list.
     */
    @PostMapping(value = "/list")
    public List<Kurs> getKurs() {
        List<Kurs> kurs = kursService.retrieveKurs();
        return kurs;
    }

    /**
     * Слушаем /{id}.
     */
    @GetMapping(value = "/{id}")
    public Kurs getKurs(@PathVariable(name = "id") Integer id) throws KursNotFound {
        return kursService.getKurs(id);
    }

    /**
     * Слушаем /update.
     */
    @PostMapping(value = "/update")
    public ResponseEntity updateKurs(@RequestBody Kurs kurs) {
        Kurs emp = kursService.getKurs(kurs);
        if (emp != null) {
            kursService.updateKurs(kurs);

        }

        return new ResponseEntity("success", HttpStatus.OK);
    }

    /**
     * Слушаем /save.
     */
    @PostMapping(value = "/save")
    public void saveKurs(Kurs kurs) {
        kursService.saveKurs(kurs);

    }

    /**
     * Слушаем /delete.
     */
    @PostMapping(value = "/delete")
    public void deleteKurs(Kurs kurs) {
        kursService.deleteKurs(kurs);

    }

}
