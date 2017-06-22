package cf.nirvandil.coursemodel.implementation;

import cf.nirvandil.coursemodel.interfaces.Course;
import cf.nirvandil.coursemodel.interfaces.Professor;
import cf.nirvandil.coursemodel.interfaces.Student;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

/**
 * Created by Vladimir Sukharev aka Nirvandil on 22.06.17 at 12:33.
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
public class CourseImpl implements Course
{
    private String name;
    private int number;
    private float price;
    private Set<Student> students;
    @JsonDeserialize(as=ProfessorImpl.class)
    private Professor teacher;

    public CourseImpl()
    {
    }

    public CourseImpl(String name, int number, float price, Set <Student> students)
    {
        this.name = name;
        this.number = number;
        this.price = price;
        this.students = students;
    }
    public CourseImpl(String name, int number, float price,
                      Set<Student> students, Professor teacher)
    {
        this(name, number, price, students);
        this.teacher = teacher;
    }

    public String getName()
    {
        return name;
    }

    public int getNumber()
    {
        return number;
    }

    public float getPrice()
    {
        return price;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public Set<Student> getStudents()
    {
        return students;
    }

    public void setStudents(Set<Student> students)
    {
        this.students = students;
    }

    public void setTeacher(Professor teacher)
    {
        this.teacher = teacher;
    }

    public Professor getTeacher()
    {
        return teacher;
    }

    @Override
    public void addStudent(Student student)
    {
        students.add(student);
    }

    @Override
    public void removeStudent(Student student)
    {
        students.remove(student);
    }

    @Override
    public String toString()
    {
        return "Course: " + this.name + " with number " + this.number;
    }
}
