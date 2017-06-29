package bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by chuprovpa on 28.06.2017.
 */
public class Kyrs {

    private class Ball
    {
        private Integer nZachetkiStud;
        private Integer ballVal;

        //вспомогательный класс
        //для работы с списком оценок студентов
        public Ball(Integer innZachetkiStud,Integer inballVal)
        {
            nZachetkiStud=innZachetkiStud;
            ballVal=inballVal;
        }
    };

    private String caption;
    private Integer number;
    private Float price;
    //служебные списки для хранения списка оценок студента, студентов, списка преподавателей для курса
    private ArrayList<Student> studList=new ArrayList<Student>();
    private ArrayList<Ball> ballList=new ArrayList<Ball>();
    private ArrayList<Professor> profList=new ArrayList<Professor>();

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

   public void addStudentBall(Integer nZachetki, Integer ball)
   {
       Ball sb = new Ball(nZachetki,ball);
       ballList.add(sb);
   }

   public Float getAvgBall()
   {
       Float sum=Float.valueOf("0");
       Iterator<Ball> itr = ballList.iterator();
       while (itr.hasNext()) {
           Ball sb=itr.next();
           sum=sum+sb.ballVal;
       }

       return sum/ballList.size();

   }

    /*******
     * Получение среднего бала по курсу для студента.
     * @param nZach - номер зачетной книжки
     * @return среднее значение бала студента
     */
    public Float getFinishBallStudent(Integer nZach)
    {
        Float sum=Float.valueOf("0");
        Integer count=0;

        Iterator<Ball> itr = ballList.iterator();
        while (itr.hasNext()) {
            Ball sb=itr.next();
            if(sb.nZachetkiStud.equals(nZach))
            {sum=sum+sb.ballVal;
            count++;}
        }

        return sum/count;

    }

    /*****
     * Добавление нового студента в курс.
     * @param st - объект Student
     */
    public void addStudent(Student st)
   {if(st.canAttachKyrs(this.caption,this.number)==true)
   { st.attachToKurs(this.caption,this.number);
       studList.add(st);}
   }


    /******
     * Прикрепление профессора к курсу.
     * @param pr
     */
   public void attachProfessor(Professor pr)
   {
       profList.add(pr);
   }

    public ArrayList<String> getListProfessor()
    {
        ArrayList<String> lst = new ArrayList<String>();

        ListIterator<Professor> itr = profList.listIterator();
        while (itr.hasNext()) {
           Professor pr = itr.next();

                String str=pr.getName()+" "+pr.getPhone();
                lst.add(str);

        }
        return lst;
    }

    /*****
     * Возвращает список студентов для данного курса.
     * @return ArrayList<String>
     */
    public ArrayList<String> getListStudent()
    {
        ArrayList<String> lst = new ArrayList<String>();

        ListIterator<Student> itr = studList.listIterator();
        while (itr.hasNext()) {
            Student st = itr.next();

            String str=st.getInfoStudent();
            lst.add(str);

        }
        return lst;
    }


    /******
     * Получение информации по курсу.
     * @return String
     */
    public String getInfoKyrs()
    {
        StringBuilder bs=new StringBuilder();

        bs.append("Наименование: "+caption+" Номер: "+number+" Стоимость: "+price);
        bs.append("Студенты: ");
        ArrayList <String> lst=getListStudent();
        bs.append(Arrays.toString(lst.toArray()));
        return bs.toString();
    }

    /****
     * Удаление студента из курса.
     * @param st
     */
   public void deleteStudent(Student st)
   {
       Iterator<Student> itr = studList.iterator();
       while (itr.hasNext()) {
           Student stud = itr.next();
           if (stud.equals(st))
           {
               boolean remove = studList.remove(st);

           }
       }
   }
}
