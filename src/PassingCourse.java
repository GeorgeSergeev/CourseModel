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

    public void addMark(int n) {
        if (n > 1 && n < 6) {marks.add(n);}
    }

    public float getAverageMark() {
        // Переменная sum - сумма оценок по курсу
        float sum = 0;
        int index, total;
        // total - хранит в себе количество поставленных оценок
        total = marks.size();
        if (total == 0) {
            return 0;
        }
        for (index = 0; index < total; index++) {
            sum += (float) marks.get(index);
        }
        return sum / total;
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