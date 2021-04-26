package ru.khrebtov.university;

import ru.khrebtov.university.entity.Course;

import java.util.List;

public class CourseTestData {
    public static final Long COURSE_ID = 0L;
    public static final Course MATH = new Course(COURSE_ID + 1, "Математика", 555, 1500);
    public static final Course PHYSICS = new Course(COURSE_ID + 5, "Физика", 666, 2500);
    public static final Course ENGLISH = new Course(COURSE_ID + 7, "Английский", 777, 700);

    public static final List<Course> COURSES = List.of(MATH, PHYSICS, ENGLISH);

}
