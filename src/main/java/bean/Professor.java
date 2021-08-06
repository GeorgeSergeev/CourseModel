package bean;

/**
 * Created by chuprovpa on 28.06.2017.
 */
public class Professor {
    private String name;
    private String adres;
    private String phone;
    private Float paySize;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getPaySize() {
        return paySize;
    }

    public void setPaySize(Float paySize) {
        this.paySize = paySize;
    }

    /***
     * Получение информации о профессоре.
     * @return String
     */
    public String getInfoProf()
    {
        StringBuilder bs=new StringBuilder();

        bs.append("Имя: "+name+" Адрес: "+adres+" Телефон: "+phone+" Сумма оплаты:"+paySize.toString());
        return bs.toString();
    }
}
