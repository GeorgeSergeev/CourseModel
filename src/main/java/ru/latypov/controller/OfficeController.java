package ru.latypov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.latypov.model.Office;
import ru.latypov.service.OfficeService;

import java.util.List;


/**
 * Контролер для  api/office.
 */

@RestController
@RequestMapping("api/office")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    /**
     * Слушаем /list.
     */
    @PostMapping(value = "/list")
    public List<Office> getOffice() {

        return officeService.retrieveOffice();
    }

    /**
     * Слушаем /{id}.
     */
    @GetMapping(value = "/{id}")
    public Office getOffice(@PathVariable(name = "id") Integer id) {
        return officeService.getOffice(id);
    }

    /**
     * Слушаем /update.
     */
    @PostMapping(value = "/update")
    public ResponseEntity updateOffice(@RequestBody Office office) {
        Office emp = officeService.getOffice(office);
        if (emp != null) {
            officeService.updateOffice(office);

        }

        return new ResponseEntity("success", HttpStatus.OK);
    }

    /**
     * Слушаем /save.
     */
    @PostMapping(value = "api/office/save")
    public void saveOffice(Office office) {
        officeService.savesOffice(office);

    }

}



