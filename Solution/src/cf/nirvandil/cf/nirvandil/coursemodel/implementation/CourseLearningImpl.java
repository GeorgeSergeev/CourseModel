package cf.nirvandil.cf.nirvandil.coursemodel.implementation;

import cf.nirvandil.cf.nirvandil.coursemodel.interfaces.Course;
import cf.nirvandil.cf.nirvandil.coursemodel.interfaces.CourseLearning;
import cf.nirvandil.cf.nirvandil.coursemodel.interfaces.Professor;

import java.util.List;

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
public class CourseLearningImpl implements CourseLearning
{
    private List<Integer> scores;
    private Course course;
    private Professor teacher;

    @Override
    public void getCurrentAverageScore()
    {

    }

    @Override
    public void getFinalScore()
    {

    }
}
