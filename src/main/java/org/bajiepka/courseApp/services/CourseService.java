package org.bajiepka.courseApp.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.bajiepka.courseApp.converter.MyJsonConverter;
import org.bajiepka.courseApp.domain.Course;
import org.bajiepka.courseApp.repositories.CourseRepository;
import org.bajiepka.courseApp.wrappers.RootHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseService {

    private CourseRepository repository;

    @Autowired
    public CourseService (CourseRepository courseRepository){
        this.repository = courseRepository;
    }

    public List<Course> getCourses(){
        return (List<Course>) repository.findAll();
    }

    public Course getCourseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ошибка! Такой курс не существует!"));
    }

    public String viewCourseAsJSON(Long id){

        String result = "...";

        try {

            Course course = getCourseById(id);
            ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return mapper.writeValueAsString(course);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    public void dismissCourse(Long id){
        Course dismissive = repository.findById(id).orElse(null);

        repository.deleteById(dismissive.getId());
    }

    public boolean saveCourse(Course course) {
        repository.save(course);
        return true;
    }

    public boolean updateCourse(Course course) {

        Course currentCourse = getCourseById(course.getId());
        currentCourse.setId(course.getId());
        currentCourse.setVersion(course.getVersion() + 1);
        currentCourse.setName(course.getName());
        currentCourse.setNumber(course.getNumber());
        currentCourse.setCost(course.getCost());
        currentCourse.setModules(course.getModules());
        saveCourse(currentCourse);

        return true;
    }

    public String viewAllCoursesAsJSON(boolean toFile) {

        RootHolder response = new RootHolder(
                Collections.emptyList(),
                repository.findAll(),
                Collections.emptyList(),
                Collections.emptyList());

        MyJsonConverter converter = new MyJsonConverter();
        converter.addForConversion(response);
        return converter.write(toFile);

    }

}
