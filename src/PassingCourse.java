/**
 * Created by Chuprov on 22.11.2016.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PassingCourse implements Serializable {
    private List<Integer> marks = new ArrayList<>();

    private Course course;

    public PassingCourse(Course course) throws RuntimeException {
        if (course == null) throw new RuntimeException("Укажите объект 'Курс'! ");
        this.course = course;
    }

    public void addMark(int mark) {
        if (TaskUtils.isValidMark(mark)) {marks.add(mark);}
    }

    public float getAverageMark() {
        OptionalDouble average = marks
                .stream()
                .mapToDouble(a -> a)
                .average();
        return (average.isPresent() ? (float) average.getAsDouble() : 0);
    }
    public Integer getFinalMark() {

        return Math.round(getAverageMark());
    }

    public Course getCourse() {
        return this.course;
    }

    @Override
    public String toString() {
        return  "Оценки по текущему курсу" +"\n"+
                marks;
    }
}
