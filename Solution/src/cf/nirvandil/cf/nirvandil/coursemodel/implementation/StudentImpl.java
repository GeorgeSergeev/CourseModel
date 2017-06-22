package cf.nirvandil.cf.nirvandil.coursemodel.implementation;

import cf.nirvandil.cf.nirvandil.coursemodel.interfaces.CourseLearning;
import cf.nirvandil.cf.nirvandil.coursemodel.interfaces.Student;

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

    @Override
    public void canListen()
    {

    }

    @Override
    public void getFinishedCourses()
    {

    }
}
