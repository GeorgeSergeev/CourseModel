package domain;

import com.sun.istack.internal.NotNull;
import domain.course.Course;
import domain.course.CourseProgress;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private final String name;
    private final String address;
    private final String mail;
    private final String phone;
    private final String num;

    private final Map<Integer,CourseProgress> courses=new HashMap<>();

    public Student()
    {
        this("","","","","");
    }

    public Student(@NotNull String name, @NotNull String address, @NotNull String mail, @NotNull String phone, @NotNull String num) {
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.num = num;
    }

    public Collection<CourseProgress> getCoursesList()
    {
       return courses.values();
    }
    public boolean canEnroll(@NotNull Course course)
    {
        return courses.containsKey(course.getNum());
    }
    public void startCourse(@NotNull Course course)
    {
        if(!canEnroll(course))
            return;
        courses.put(course.getNum(),new CourseProgress(course));
    }
    public void removeCourse(@NotNull Course course)
    {
        if(!courses.containsKey(course.getNum()))
        return;
        courses.remove(course.getNum());
    }
    public float averageMark()
    {
        float sum= (float) courses.values().stream().mapToDouble(CourseProgress::averageMark).sum();
        return sum/courses.values().size();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.name)
                .append(this.address)
                .append(this.mail)
                .append(this.phone)
                .append(this.num)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Student){
            final Student other = (Student) obj;
            return new EqualsBuilder()
                    .append(this.name,other.name)
                    .append(this.address,other.address)
                    .append(this.mail,other.mail)
                    .append(this.phone,other.phone)
                    .append(this.num,other.num)
                    .isEquals();
        } else{
            return false;
        }
    }
}
