package cf.nirvandil.cf.nirvandil.coursemodel.implementation;

import cf.nirvandil.cf.nirvandil.coursemodel.interfaces.Course;
import cf.nirvandil.cf.nirvandil.coursemodel.interfaces.Student;

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

    public CourseImpl(String name, int number, float price)
    {
        this.name = name;
        this.number = number;
        this.price = price;
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

    public Set<Student> getStudents()
    {
        return students;
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
}
