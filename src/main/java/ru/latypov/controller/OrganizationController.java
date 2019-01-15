package ru.latypov.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.latypov.model.Organization;
import ru.latypov.service.OrganizationService;

import java.util.List;


/**
 * Контролер для  api/organization.
 */


@RestController
@RequestMapping("api/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    /**
     * Слушаем /list.
     */
    @PostMapping(value = "/list")
    public List<Organization> getOrganization() {
        List<Organization> organizations = organizationService.retrieveOrganization();
        return organizations;
    }

    /**
     * Слушаем /{id}.
     */
    @GetMapping(value = "/{id}")
    public Organization getOrganization(@PathVariable(name = "id") Integer id) {
        return organizationService.getOrganization(id);
    }

    /**
     * Слушаем /update.
     */
    @PostMapping(value = "/update")
    public ResponseEntity updateOrganization(@RequestBody Organization organization) {
        Organization emp = organizationService.getOrganization(organization);
        if (emp != null) {
            organizationService.updateOrganization(organization);

        }

        return new ResponseEntity("success", HttpStatus.OK);
    }

    /**
     * Слушаем /save.
     */
    @PostMapping(value = "/save")
    public void saveOrganization(Organization organization) {
        organizationService.savesOrganization(organization);

    }

}
