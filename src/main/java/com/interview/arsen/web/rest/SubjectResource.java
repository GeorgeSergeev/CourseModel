package com.interview.arsen.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.interview.arsen.service.SubjectService;
import com.interview.arsen.web.rest.errors.BadRequestAlertException;
import com.interview.arsen.web.rest.util.HeaderUtil;
import com.interview.arsen.service.dto.SubjectDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Subject.
 */
@RestController
@RequestMapping("/api")
public class SubjectResource {

    private final Logger log = LoggerFactory.getLogger(SubjectResource.class);

    private static final String ENTITY_NAME = "subject";

    private final SubjectService subjectService;

    public SubjectResource(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * POST  /subjects : Create a new subject.
     *
     * @param subjectDTO the subjectDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subjectDTO, or with status 400 (Bad Request) if the subject has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/subjects")
    @Timed
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO) throws URISyntaxException {
        log.debug("REST request to save Subject : {}", subjectDTO);
        if (subjectDTO.getId() != null) {
            throw new BadRequestAlertException("A new subject cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubjectDTO result = subjectService.save(subjectDTO);
        return ResponseEntity.created(new URI("/api/subjects/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /subjects : Updates an existing subject.
     *
     * @param subjectDTO the subjectDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subjectDTO,
     * or with status 400 (Bad Request) if the subjectDTO is not valid,
     * or with status 500 (Internal Server Error) if the subjectDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/subjects")
    @Timed
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO) throws URISyntaxException {
        log.debug("REST request to update Subject : {}", subjectDTO);
        if (subjectDTO.getId() == null) {
            return createSubject(subjectDTO);
        }
        SubjectDTO result = subjectService.save(subjectDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subjectDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /subjects : get all the subjects.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of subjects in body
     */
    @GetMapping("/subjects")
    @Timed
    public List<SubjectDTO> getAllSubjects() {
        log.debug("REST request to get all Subjects");
        return subjectService.findAll();
        }

    /**
     * GET  /subjects/:id : get the "id" subject.
     *
     * @param id the id of the subjectDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subjectDTO, or with status 404 (Not Found)
     */
    @GetMapping("/subjects/{id}")
    @Timed
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long id) {
        log.debug("REST request to get Subject : {}", id);
        SubjectDTO subjectDTO = subjectService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(subjectDTO));
    }

    /**
     * DELETE  /subjects/:id : delete the "id" subject.
     *
     * @param id the id of the subjectDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/subjects/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        log.debug("REST request to delete Subject : {}", id);
        subjectService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
