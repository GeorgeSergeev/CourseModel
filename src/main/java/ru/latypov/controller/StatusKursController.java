package ru.latypov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.latypov.exception.StatusKursNotFound;
import ru.latypov.model.StatusKurs;
import ru.latypov.service.StatusKursService;

import java.util.List;


/**
 * Контролер для  api/statuskurs.
 */

@RestController
@RequestMapping("api/statuskurs")
public class StatusKursController {
    @Autowired
    private StatusKursService statuskursService;

    /**
     * Слушаем /list.
     */
    @PostMapping(value = "/list")
    public List<StatusKurs> getStatusKurs() {
        List<StatusKurs> statusKurs = statuskursService.retrieveStatusKurs();
        return statusKurs;
    }

    /**
     * Слушаем /{id}.
     */
    @GetMapping(value = "/{id}")
    public StatusKurs getStatusKurs(@PathVariable(name = "id") Integer id) throws StatusKursNotFound {
        return statuskursService.getStatusKurs(id);
    }

    /**
     * Слушаем /update.
     */
    @PostMapping(value = "/update")
    public ResponseEntity updateStatusKurs(@RequestBody StatusKurs statusKurs) {
        StatusKurs emp = statuskursService.getStatusKurs(statusKurs);
        if (emp != null) {
            statuskursService.updateStatusKurs(statusKurs);

        }

        return new ResponseEntity("success", HttpStatus.OK);
    }

    /**
     * Слушаем /save.
     */
    @PostMapping(value = "/save")
    public void saveStatusKurs(StatusKurs statusKurs) {
        statuskursService.savesStatusKurs(statusKurs);

    }

    /**
     * Слушаем /delete.
     */
    @PostMapping(value = "/delete")
    public void deleteStatusKurs(StatusKurs statusKurs) {
        statuskursService.deleteStatusKurs(statusKurs);

    }

}



