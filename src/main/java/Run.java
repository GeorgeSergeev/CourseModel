/**
 * Created by chuprovpa on 28.06.2017.
 */

import BeanSerializer.KyrsSerializer;
import BeanSerializer.ProfessorSerializer;
import BeanSerializer.StudentSerializer;
import bean.Kyrs;
import bean.Professor;
import bean.Student;
import worker.MainWorker;

import java.util.ArrayList;
import java.util.ListIterator;

public  class Run {
public static void main(String[] args) {
    // запуск работы с тестовым функционалом.
    System.out.println("----------Run main programm.-----------");
    System.out.println("----Load JSON data----");
    //загрузка тестовых данных из JSON файлов
    MainWorker worker;
    worker = MainWorker.getInstance();

    System.out.println("----------------------");
    System.out.println("--- Info Students");
    ArrayList<Student> studArray = worker.getLstStudent();
    Student tstStud;
    ListIterator<Student> itr = studArray.listIterator();
    while (itr.hasNext()) {
        tstStud = itr.next();
        System.out.println("---- " + tstStud.getInfoStudent());
    }


    System.out.println("----------------------");
    System.out.println("--- Info Professor");
    Professor tstPr;
    ArrayList<Professor> profArray = worker.getLstProfessor();
    ListIterator<Professor> itr2 = profArray.listIterator();
    while (itr2.hasNext()) {
        tstPr = itr2.next();
        System.out.println("---- " + tstPr.getInfoProf());
    }


    System.out.println("----------------------");
    System.out.println("--- Info Kyrs");
    Kyrs tstKyrs;
    ArrayList<Kyrs> kyrsArray = worker.getLstKyrs();
    ListIterator<Kyrs> itr3 = kyrsArray.listIterator();
    while (itr3.hasNext()) {
        tstKyrs = itr3.next();
        System.out.println("---- " + tstKyrs.getInfoKyrs());
    }
    //вызов различных обработчиков (методов) классов
    System.out.println("======================================");
    System.out.println("PULL REQUEST");
    System.out.println("======================================");
    System.out.println("GET LIST FINISHED STUDENT KYRS ");
    //список всех законченых курсов студента
    ArrayList<String> tmpSt = studArray.get(0).getFinishedKyrs();
    for (int i = 0; i < tmpSt.size(); i++)
    {System.out.println(tmpSt.get(i));}

    System.out.println("======================================");
    System.out.println("WORK THIS KYRS ");

    tstKyrs=kyrsArray.get(0);
    tstKyrs.addStudentBall(1,12);
    tstKyrs.addStudentBall(1,6);
    tstKyrs.addStudentBall(11,11);
    tstKyrs.addStudentBall(16,8);
    System.out.println("Текущий средний бал по студенту с зачеткой 1 :"+tstKyrs.getFinishBallStudent(1));
    System.out.println("Текущий средний бал по курсу :"+tstKyrs.getAvgBall());

    Student st2=new Student();
    st2.setName("Михаил");
    st2.setAdres("Testovaya, 23");
    st2.setnZach(123);
    st2.setPhone("11-11-11");
    st2.setAvgBall(Float.valueOf("123"));
    st2.seteMeail("mail@mail.test");
    tstKyrs.addStudent(st2);

    System.out.println("======================================");
    System.out.println("RUN STORE DATA ");
     //запись выходных тестовых данных в формате JSON.
         System.out.println("kyrs file: out_json/out_kyrs.txt");
         String strData=KyrsSerializer.serializKyrs(tstKyrs);
         worker.JSONToFile(strData,"out_kyrs.txt");

    System.out.println("student file: out_json/out_student.txt");
    strData= StudentSerializer.serializStudent(st2);
    worker.JSONToFile(strData,"out_student.txt");

    System.out.println("professor file: out_json/out_professor.txt");

    tstPr=profArray.get(0);
    tstPr.setAdres("Адресс изменен.");
    strData= ProfessorSerializer.serializProfessor(tstPr);
    worker.JSONToFile(strData,"out_professor.txt");
}

}
