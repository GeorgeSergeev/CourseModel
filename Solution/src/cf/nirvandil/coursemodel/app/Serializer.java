package cf.nirvandil.coursemodel.app;

import cf.nirvandil.coursemodel.interfaces.Course;
import cf.nirvandil.coursemodel.interfaces.CourseLearning;
import cf.nirvandil.coursemodel.interfaces.Professor;
import cf.nirvandil.coursemodel.interfaces.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Vladimir Sukharev aka Nirvandil on 22.06.17 at 20:54.
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
public class Serializer
{
    private static ObjectMapper mapper = new ObjectMapper();

    public static void writeStudent(String fileName, Student student) throws IOException
    {
        mapper.writeValue(new File(fileName), student);
    }
    public static void writeCourse(String fileName, Course course) throws IOException
    {
        mapper.writeValue(new File(fileName), course);
    }
    public static void writeCourseLearning(String fileName, CourseLearning learning) throws IOException
    {
        mapper.writeValue(new File(fileName), learning);
    }
    public static void writeProfessor(String fileName, Professor professor) throws IOException
    {
        mapper.writeValue(new File(fileName), professor);
    }
}
