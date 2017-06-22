package cf.nirvandil.coursemodel.interfaces;

import cf.nirvandil.coursemodel.implementation.CourseImpl;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by Vladimir Sukharev aka Nirvandil on 22.06.17 at 11:44.
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
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CourseImpl.class, name = "CourseImpl") }
)
public interface Course
{
    void addStudent(Student student);
    void removeStudent(Student student);
}
