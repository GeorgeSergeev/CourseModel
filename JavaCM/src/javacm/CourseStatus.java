/*
Сущность "Прохождение курса" имеет свойста: имя курса, оценки по данному курсу
Сущность может
просчитать средний балл getAverageScore
выдать финальную оценку getFinalScore

Сущность принадлежит некоторому студенту
 */
package javacm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serega
 */
public class CourseStatus {
    
    //поля
    private String listenCurse;
    //оценки по конкретному курсу храним в списке
    private List score = new ArrayList();

    //конструкторы
    public CourseStatus() {
        
    }

    public CourseStatus(String listenCurse) {
        this.listenCurse = listenCurse;
    }
    
    
    //методы
    public String getListenCurse() {
        
        return listenCurse;
    }

    public void setListenCurse(String listenCurse) {
        
        this.listenCurse = listenCurse;
    }

    public void setScore(float score) {
        
        this.score.add(score);
    }
    
    //расчет текущего среднего балла по конкретному курсу
    public float getAverageScore() {
        
        float sum = 0;
        int i, total;
        
        total = score.size();
        if(total == 0){
            return 0;
        }
        for(i=0;i<total;i++) {
            sum += (float)score.get(i);
        }
        return sum/total;
    }
    
    //финальная оценка
    public int getFinalScore() {
        
        return (int)(0.5f+getAverageScore());
    }
    
}
