package com.interview.arsen.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.interview.arsen.service.CourseService;
import com.interview.arsen.service.StudentService;
import com.interview.arsen.service.dto.StudentDTO;
import com.interview.arsen.web.rest.errors.BadRequestAlertException;
import com.interview.arsen.web.rest.util.HeaderUtil;
import com.interview.arsen.service.dto.CourseDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.*;

/**
 * REST controller for managing Course.
 */
@RestController
@RequestMapping("/api")
public class CourseResource {

    private final Logger log = LoggerFactory.getLogger(CourseResource.class);

    private static final String ENTITY_NAME = "course";

    private final CourseService courseService;
    private final StudentService studentService;
    private final StudentResource studentResource;

    public CourseResource(CourseService courseService, StudentService studentService, StudentResource studentResource) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.studentResource = studentResource;
    }

    /**
     * POST  /courses : Create a new course.
     *
     * @param courseDTO the courseDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new courseDTO, or with status 400 (Bad Request) if the course has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/courses")
    @Timed
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) throws URISyntaxException {
        log.debug("REST request to save Course : {}", courseDTO);
        if (courseDTO.getId() != null) {
            throw new BadRequestAlertException("A new course cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CourseDTO result = courseService.save(courseDTO);
        return ResponseEntity.created(new URI("/api/courses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /courses : Updates an existing course.
     *
     * @param courseDTO the courseDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated courseDTO,
     * or with status 400 (Bad Request) if the courseDTO is not valid,
     * or with status 500 (Internal Server Error) if the courseDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/courses")
    @Timed
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO) throws URISyntaxException {
        log.debug("REST request to update Course : {}", courseDTO);
        if (courseDTO.getId() == null) {
            return createCourse(courseDTO);
        }
        CourseDTO result = courseService.save(courseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, courseDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /courses : get all the courses.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of courses in body
     */
    @GetMapping("/courses")
    @Timed
    public List<CourseDTO> getAllCourses() {
        log.debug("REST request to get all Courses");
        return courseService.findAll();
        }

    /**
     * GET  /courses/:id : get the "id" course.
     *
     * @param id the id of the courseDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the courseDTO, or with status 404 (Not Found)
     */
    @GetMapping("/courses/{id}")
    @Timed
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        log.debug("REST request to get Course : {}", id);
        CourseDTO courseDTO = courseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(courseDTO));
    }

    /**
     * DELETE  /courses/:id : delete the "id" course.
     *
     * @param id the id of the courseDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/courses/{id}")
    @Timed
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        log.debug("REST request to delete Course : {}", id);
        courseService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/courses/{id}/students-for-delete")
    @Timed
    public List<StudentDTO> studentsForDelete(@PathVariable Long id){
        List<StudentDTO> allStudents = new LinkedList<>();
        List<StudentDTO> students = studentService.findAll();
        students.forEach(student -> {
            for (CourseDTO stud : student.getCourses()) {
                if (stud.getId().equals(id)) {
                    allStudents.add(student);
                    System.out.println(student);
                }
            }
        });
        return allStudents;
    }
    @GetMapping("/courses/{id}/get-students")
    @Timed
    public List<StudentDTO> getStudents(@PathVariable Long id) {
        List<StudentDTO> allStudents = new LinkedList<>();
        List<StudentDTO> students = studentService.findAll();

        students.forEach(studentDTO -> {
            for (CourseDTO stud : studentDTO.getCourses()) {
                if (stud.getId().equals(id)) {
                    return;
                }
            }
            allStudents.add(studentDTO);
        });
        return allStudents;
    }
    @PutMapping("/courses/{id}/udpate-students")
    @Timed
    public ResponseEntity<StudentDTO> updateStudents(@RequestBody Set<StudentDTO> students) throws URISyntaxException {
        log.debug("REST request to update Student : {}", students);
        for (StudentDTO studentDTOs : students){
            if(studentDTOs.getId() == null){
                return studentResource.createStudent (studentDTOs);
            }
        }
        for (StudentDTO studentDTOs : students){
            StudentDTO result = studentService.save(studentDTOs);
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, studentDTOs.getId().toString()))
                .body(result);
        }
        return null;
    }
}
