/*
Сущность "Образовательный курс" имеет свойста: название, идентификатор курса,
стоимость, может преподаваться профессором, знает всех записанных студентов.
Курс может
иметь проподавателя addProfessor
принять нового студента для обучения addStudent
удалить студента из списка removeStudent
посмотреть на студента getStudent
оценить знания студента некоторым баллом addScoreToStudent

К образовательному курсу может быть приписан профессор, и могут быть
приписаны студенты
 */
package javacm;

import java.util.ArrayList;
/**
 *
 * @author serega
 */
public class EducCourse {
    
    //поля
    private String courseName;
    private int Id;
    private float price;
    private Professor professor;
    //Курс могут проходить несколько студентов, 
    //будем сохранять их в списке
    private ArrayList<Student> studentsList = new ArrayList<Student>();
    
    //конструкторы
    public EducCourse() {    
        
    }

    public EducCourse(String courseName, int Id, float price) {
        this.courseName = courseName;
        this.Id = Id;
        this.price = price;
    }
    
    //методы
    public String getName() {
        return courseName;
    }

    public void setName(String courseName) {
        this.courseName = courseName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    //курс может вести профессор
    public void addProfessor(Professor professor) {
        
        this.professor = professor;
    }
    
    //добавить студента в список
    public void addStudent(Student newStudent) {
        
        newStudent.addCourse(this.getName());
        studentsList.add(newStudent);
        
        //Если данный курс ведет профессор, то его оплата будет
        //расти, пропорционально кол-ву обучаемых студентов
       try {
            this.professor.calculatePayment(studentsList.size(), price);
        } catch (Exception e) {
            
        }
    }
    
    //удалить студента из списка по номеру зачетки
    public Student removeStudent(int recBookId) {
        
        Student currStudent = null;
        
        for(Student student : studentsList ) {
            
            if(student.getRecbookId() == recBookId) {
                currStudent = student;
                studentsList.remove(student);
                
                //Оплата профессора уменьшится (если он ведет курс)
                try {
                    this.professor.calculatePayment(studentsList.size(), price);
                } catch (Exception e) {
                    
                }
            }
        }
        
        return currStudent;
    }
    
    
    //получить студента из списка по номеру зачетки
    public Student getStudent(int recBookId) {
        
        Student currStudent = null;
        
        for(Student student : studentsList ) {
            
            if(student.getRecbookId() == recBookId) {
                currStudent = student;
            }
        }
        
        return currStudent;
    }    
    
    //поставить определенному студенту оценку по данному курсу
    public void addScoreToStudent(int recBookId, float currScore) {
        
        Student student;
        
        student = getStudent(recBookId);
        student.getCourse(courseName).setScore(currScore);
        //каждая оценка влияет на общую успеваемость студента
        student.calculateAvrProgress();
        
    }
}
