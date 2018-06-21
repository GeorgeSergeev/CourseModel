package com.interview.arsen.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.interview.arsen.service.StudentService;
import com.interview.arsen.web.rest.errors.BadRequestAlertException;
import com.interview.arsen.web.rest.util.HeaderUtil;
import com.interview.arsen.service.dto.StudentDTO;
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
 * REST controller for managing Student.
 */
@RestController
@RequestMapping("/api")
public class StudentResource {

    private final Logger log = LoggerFactory.getLogger(StudentResource.class);

    private static final String ENTITY_NAME = "student";

    private final StudentService studentService;

    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * POST  /students : Create a new student.
     *
     * @param studentDTO the studentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new studentDTO, or with status 400 (Bad Request) if the student has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/students")
    @Timed
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws URISyntaxException {
        log.debug("REST request to save Student : {}", studentDTO);
        if (studentDTO.getId() != null) {
            throw new BadRequestAlertException("A new student cannot already have an ID", ENTITY_NAME, "idexists");
        }
        StudentDTO result = studentService.save(studentDTO);
        return ResponseEntity.created(new URI("/api/students/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /students : Updates an existing student.
     *
     * @param studentDTO the studentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated studentDTO,
     * or with status 400 (Bad Request) if the studentDTO is not valid,
     * or with status 500 (Internal Server Error) if the studentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/students")
    @Timed
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) throws URISyntaxException {
        log.debug("REST request to update Student : {}", studentDTO);
        if (studentDTO.getId() == null) {
            return createStudent(studentDTO);
        }
        StudentDTO result = studentService.save(studentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /students : get all the students.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of students in body
     */
    @GetMapping("/students")
    @Timed
    public List<StudentDTO> getAllStudents() {
        log.debug("REST request to get all Students");
        return studentService.findAll();
        }

    /**
     * GET  /students/:id : get the "id" student.
     *
     * @param id the id of the studentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the studentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/students/{id}")
    @Timed
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
        log.debug("REST request to get Student : {}", id);
        StudentDTO studentDTO = studentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(studentDTO));
    }

    /**
     * DELETE  /students/:id : delete the "id" student.
     *
     * @param id the id of the studentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/students/{id}")
    @Timed
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        log.debug("REST request to delete Student : {}", id);
        studentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
