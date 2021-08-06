package domain.course;

import com.sun.istack.internal.NotNull;
import domain.Student;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class CourseProgress {

    private final ICourse course;
    private final List<Integer> marks=new ArrayList<>();

    public CourseProgress()
    {
        this(new Course());
    }
    public CourseProgress(@NotNull ICourse course) {
        this.course = course;
    }

    public float averageMark()
    {
        int sum=marks.stream().mapToInt(Integer::intValue).sum();
        return (float)sum/marks.size();
    }
    public int finalMark()
    {
        return Math.round(averageMark());
    }
    public void addMark(int mark)
    {
        marks.add(mark);
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.course)
                .append(this.marks)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof CourseProgress){
            final CourseProgress other = (CourseProgress) obj;
            return new EqualsBuilder()
                    .append(this.course,other.course)
                    .append(this.marks,other.marks)
                    .isEquals();
        } else{
            return false;
        }
    }
}
