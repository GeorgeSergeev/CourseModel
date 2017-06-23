package cf.nirvandil.coursemodel.implementation;

import cf.nirvandil.coursemodel.interfaces.Course;
import cf.nirvandil.coursemodel.interfaces.CourseLearning;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
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
    private List<Integer> scores = new ArrayList<>();
    @JsonDeserialize(as=CourseImpl.class)
    private Course course;

    public CourseLearningImpl()
    {
    }

    public CourseLearningImpl(Course course)
    {
        this.course = course;
    }

    public List<Integer> getScores()
    {
        return scores;
    }

    public void setScores(List<Integer> scores)
    {
        this.scores = scores;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }

    public Course getCourse()
    {
        return course;
    }


    @Override
    public void getCurrentAverageScore()
    {
        System.out.println("Current average score is " +
                scores.stream().mapToInt(Integer::intValue).sum() / (double) scores.size()
        );
    }

    @Override
    // What is final score?.. Just floored average?.. TODO: rethink it.
    public void getFinalScore()
    {
        System.out.println("Final score is " + Math.floor(
                scores.stream().mapToInt(Integer::intValue).sum() / (double) scores.size()
        ));
    }
}
