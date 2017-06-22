package cf.nirvandil.coursemodel.implementation;

import cf.nirvandil.coursemodel.interfaces.Course;
import cf.nirvandil.coursemodel.interfaces.Professor;

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
public class ProfessorImpl implements Professor
{
    private String name;
    private String address;
    private String phone;
    private Float salary;
    private Set<Course> teachCourses;

    public ProfessorImpl()
    {
    }

    public ProfessorImpl(String name, String address, String phone, Float salary)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Float getSalary()
    {
        return salary;
    }

    public void setSalary(Float salary)
    {
        this.salary = salary;
    }

    public Set<Course> getTeachCourses()
    {
        return teachCourses;
    }

    public void setTeachCourses(Set<Course> teachCourses)
    {
        this.teachCourses = teachCourses;
    }
}
