package worker;

import BeanSerializer.KyrsSerializer;
import BeanSerializer.ProfessorSerializer;
import BeanSerializer.StudentSerializer;
import bean.Kyrs;
import bean.Professor;
import bean.Student;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by chuprovpa on 28.06.2017.
 * Синглтон.
 */
public class MainWorker {
    private static MainWorker ourInstance = new MainWorker();
    //списки для хранения внутренней информации о курсах,профессорах,студентах
    private static ArrayList<Kyrs> lstKyrs ;
    private static ArrayList<Student> lstStudent ;
    private static ArrayList<Professor> lstProfessor ;
    public static MainWorker getInstance() {
        return ourInstance;
    }

    private MainWorker() {

            File f = new File("work_files");

        lstKyrs =new ArrayList<Kyrs>();
        lstStudent =new ArrayList<Student>();
        lstProfessor =new ArrayList<Professor>();
         // инициализация тестовыми данными служебных списков данных.
        // рабочая директория: work_files
        // данные  в формате JSON

            File[] files = f.listFiles();
            for (File file : files) {
                try {
                    System.out.println(file.getName());
                    String st= new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), StandardCharsets.UTF_8);

                    if(file.getName().toUpperCase().equals("KYRS.TXT"))
                    {Kyrs kr = new Kyrs();
                     kr= KyrsSerializer.getKyrsObject(st);
                    System.out.println("Load json KYRS.");
                        lstKyrs.add(kr);}
                    if(file.getName().toUpperCase().equals("PROFESSOR.TXT"))
                    {
                        Professor pr = new Professor();
                        pr = ProfessorSerializer.getProfessorObject(st);
                        System.out.println("Load json PROFESSOR.");
                        lstProfessor.add(pr);
                        System.out.println(lstProfessor.size());}
                    if(file.getName().toUpperCase().equals("STUDENT.TXT"))
                    {
                        Student styd = new Student();
                        styd= StudentSerializer.getStudentObject(st);
                        System.out.println("Load json STUDENT.");
                        lstStudent.add(styd);
                        System.out.println(lstStudent.size());
                    }

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }

    public static ArrayList<Kyrs> getLstKyrs() {
        return lstKyrs;
    }

    public static void setLstKyrs(ArrayList<Kyrs> lstKyrs) {
        MainWorker.lstKyrs = lstKyrs;
    }

    public static ArrayList<Student> getLstStudent() {
        return lstStudent;
    }

    public static void setLstStudent(ArrayList<Student> lstStudent) {
        MainWorker.lstStudent = lstStudent;
    }

    public static ArrayList<Professor> getLstProfessor() {
        return lstProfessor;
    }

    public static void setLstProfessor(ArrayList<Professor> lstProfessor) {
        MainWorker.lstProfessor = lstProfessor;
    }

    /*****
     * Запись строковых данных в папку: out_json
     * @param inputstr - входные данные
     * @param fileName - имя файла
     */
    public void JSONToFile(String inputstr,String fileName)
    {

        try {

            BufferedWriter out = new BufferedWriter(new FileWriter("out_json/"+fileName));
            out.write(inputstr);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        finally {

        }
    }
}
