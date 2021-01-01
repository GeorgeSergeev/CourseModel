/*
Сущность "Профессор" имеет свойста: имя, адресс, телефон, заработную палту
Профессор может
вычеслить совю заработную плату calculatePayment

Профессор приписан у некоторому курсу
 */
package javacm;

/**
 *
 * @author serega
 */
public class Professor {
    
    //поля
    private String professorName;
    private String address;
    private String telephone;
    private float payment;
    
    //конструкторы
    public Professor() {
        
    }

    public Professor(String professorName, String address, String telephone) {
        this.professorName = professorName;
        this.address = address;
        this.telephone = telephone;
    }
    
    //методы
    public String getName() {
        return professorName;
    }

    public void setName(String professorName) {
        this.professorName = professorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    //Вычисление заработной платы профессора,
    //кол-во студетов * стоимость проводимого курса
    public void calculatePayment(int studentsCount, float coursePrice) {
        
        setPayment(studentsCount * coursePrice);
    }
    
}
