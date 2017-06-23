package cf.nirvandil.coursemodel.implementation;

import cf.nirvandil.coursemodel.interfaces.Course;
import cf.nirvandil.coursemodel.interfaces.CourseLearning;
import cf.nirvandil.coursemodel.interfaces.Student;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Vladimir Sukharev aka Nirvandil on 22.06.17 at 12:32.
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
public class StudentImpl implements Student
{
    private String name;
    private String address;
    private String phone;
    private String email;
    private Integer recordBookNumber;
    private float averageAcademPerformance;
    private Set<CourseLearning> learnings;
    private Set<Course> finishedCourses;

    public StudentImpl()
    {
    }

    public StudentImpl(String name, String address, String phone, String email, Integer recordBookNumber)
    {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.recordBookNumber = recordBookNumber;
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Integer getRecordBookNumber()
    {
        return recordBookNumber;
    }

    public void setRecordBookNumber(Integer recordBookNumber)
    {
        this.recordBookNumber = recordBookNumber;
    }

    public float getAverageAcademPerformance()
    {
        return averageAcademPerformance;
    }

    public void setAverageAcademPerformance(float averageAcademPerformance)
    {
        this.averageAcademPerformance = averageAcademPerformance;
    }

    public Set<CourseLearning> getLearnings()
    {
        return learnings;
    }

    public void setLearnings(Set<CourseLearning> learnings)
    {
        this.learnings = learnings;
    }

    public void setFinishedCourses(Set<Course> finishedCourses)
    {
        this.finishedCourses = finishedCourses;
    }

    @Override
    // What exactly do this method?
    // Assume it takes set of all available courses
    // then excludes already listened or finished
    public void canListen(Set<Course> allCourses)
    {
        Set<Course> available = new HashSet<>(allCourses);
        available.removeAll(finishedCourses);
        for (CourseLearning learning : learnings)
            available.remove(((CourseLearningImpl)learning).getCourse());
        available.forEach(System.out::println);
    }

    @Override
    public void getFinishedCourses()
    {
        System.out.println("This student finished: ");
        finishedCourses.forEach(System.out::println);
    }
}
