package domain.course;

import com.sun.istack.internal.NotNull;
import domain.Professor;
import domain.Student;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Курс который ведёт профессор
 */
public class ProfessorDependentCourse implements ICourse{

    private final ICourse course;
    private final Professor professor;

    public ProfessorDependentCourse()
    {
        this(new Course(),new Professor());
    }
    public ProfessorDependentCourse(@NotNull ICourse course,@NotNull Professor professor)
    {
        this.course=course;
        this.professor=professor;
    }

    public void addStudent(Student student) {
        course.addStudent(student);
    }

    public void removeStudent(Student student) {
        course.removeStudent(student);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.professor)
                .append(this.course)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ProfessorDependentCourse){
            final ProfessorDependentCourse other = (ProfessorDependentCourse) obj;
            return new EqualsBuilder()
                    .append(this.professor,other.professor)
                    .append(this.course,other.course)
                    .isEquals();
        } else{
            return false;
        }
    }
}
