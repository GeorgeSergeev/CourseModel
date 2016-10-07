/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    //текуцщий средний балл
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
