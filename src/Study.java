

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by TheEndl on 01.05.2017.
 */



public class Study {
    public ArrayList<Integer> evaluations = new ArrayList<>();

    public float getMiddleGrade(Student student)
    {
        float sum = 0;

        for (int i = 0; i < this.evaluations.size(); i++)
        {
            sum = sum + this.evaluations.get(i);
        }
        student.studyLevel = sum / this.evaluations.size();
        if (student.studyLevel > 0)
            student.setStudyLevel(student.studyLevel);
        else System.out.println("Студент" + student + " еще не получал оценок");

        return student.studyLevel;
    }

   // Set new evaluate for student
    public void setMarkForStudent(Student student, int evaluation)
    {
        this.evaluations.add(evaluation);
    }


}
