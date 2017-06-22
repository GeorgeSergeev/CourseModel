package cf.nirvandil.coursemodel.app;

import cf.nirvandil.coursemodel.implementation.CourseImpl;
import cf.nirvandil.coursemodel.implementation.CourseLearningImpl;
import cf.nirvandil.coursemodel.implementation.ProfessorImpl;
import cf.nirvandil.coursemodel.implementation.StudentImpl;
import cf.nirvandil.coursemodel.interfaces.Course;
import cf.nirvandil.coursemodel.interfaces.CourseLearning;
import cf.nirvandil.coursemodel.interfaces.Professor;
import cf.nirvandil.coursemodel.interfaces.Student;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by Vladimir Sukharev aka Nirvandil on 22.06.17 at 18:39.
 * This program is part of CourseModel.
 * CourseModel is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */
public class MainApp
{
    public static void main(String[] args) throws IOException
    {
        // So, create some model objects, write them to JSON files, than read some of it back
        // and print to stdout some data
        Course course = new CourseImpl("JavaSE", 42, 4200, new HashSet<>());
        CourseLearning courseLearning = new CourseLearningImpl(course);
        Professor professor = new ProfessorImpl("Ross Geller", "New-York", "+42", 42000.0f);
        Student student = new StudentImpl("Joe", "New-York", "+24", "boss@gmail.com", 42);
        course.addStudent(student);
        Serializer.writeCourseLearning("learning.json", courseLearning);
        Serializer.writeCourse("course.json", course);
        Serializer.writeProfessor("professor.json", professor);
        Serializer.writeStudent("student.json", student);
        System.out.println(((StudentImpl)Serializer.readStudent("student.json")).getName());
        System.out.println(((CourseLearningImpl) Serializer.readCourseLearning("learning.json")).getCourse());
        course = Serializer.readCourse("course.json");
        // Add one more student to this course
        course.addStudent(new StudentImpl("Rachel", "Paris", "+422", "NONE", 24));
        System.out.println(((CourseImpl) course).getStudents());
    }
}
