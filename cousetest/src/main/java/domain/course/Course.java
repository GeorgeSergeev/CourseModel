package domain.course;

import com.sun.istack.internal.NotNull;
import domain.Student;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

public class Course implements ICourse{
    private final String title;
    private final int price;
    private final int num;
    private final List<Student> students=new ArrayList<Student>();

    public Course()
    {
        this("",0,0);
    }
    public Course(@NotNull String title, int price, int num) {
        this.title = title;
        this.price = price;
        this.num = num;
    }

    public void addStudent(@NotNull Student student)
    {
        if(students.contains(student))
            return;

        students.add(student);
        student.startCourse(this);
    }
    public void removeStudent(@NotNull Student student)
    {
        if(students.contains(student))
            return;

        students.remove(student);
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getNum() {
        return num;
    }

    public List<Student> getStudents() {
        return new ArrayList<>(this.students);
    }
    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.title)
                .append(this.price)
                .append(this.num)
                .append(this.students)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Course){
            final Course other = (Course) obj;
            return new EqualsBuilder()
                    .append(this.title,other.title)
                    .append(this.price,other.price)
                    .append(this.num,other.num)
                    .append(this.students,other.students)
                    .isEquals();
        } else{
            return false;
        }
    }
}
