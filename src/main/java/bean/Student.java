package bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Created by chuprovpa on 28.06.2017.
 *
 */


public class Student {

    //используется для хранения информации по курсам
    private class InfoKyrs{
        private String nameKyrs;
        private Integer nKyrs;
        private String state;
    }

    private String name;
    private String adres;
    private String phone;
    private String eMeail;
    private Integer nZach;
    private Float avgBall;

// информация по завершенным курсам, а также текущим
    private ArrayList<InfoKyrs> listFinishedKyrs=new ArrayList<InfoKyrs>();

    public Student()
    {

    }

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

    public String geteMeail() {
        return eMeail;
    }

    public void seteMeail(String eMeail) {
        this.eMeail = eMeail;
    }

    public Integer getnZach() {
        return nZach;
    }

    public void setnZach(Integer nZach) {
        this.nZach = nZach;
    }

    public Float getAvgBall() {
        return avgBall;
    }

    public void setAvgBall(Float avgBall) {
        this.avgBall = avgBall;
    }


    @Override
    public int hashCode() {
        return nZach.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (!nZach.equals(other.nZach))
            return false;
        if (!adres.equals(other.adres))
            return false;
        if (!name.equals(other.name))
            return false;
        if (!phone.equals(other.phone))
            return false;
        if (!eMeail.equals(other.eMeail))
            return false;
        return true;
    }

    /***
     * Привязка студента к курсу
     * @param nameKyrs - имя курса
     * @param nKyrs - номер
     */
    public void attachToKurs(String nameKyrs, Integer nKyrs) {


       InfoKyrs ik=new InfoKyrs();
            ik.nameKyrs=nameKyrs;
            ik.nKyrs=nKyrs;
            ik.state="IN_PROCESS";

            listFinishedKyrs.add(ik);

    }

    /**
     * Проверяет возможность закрепления студента к курсу.
     * @param nameKyrs - имя курса
     * @param nKyrs - номер курса
     * @return true в случае возможности прикрепить студента к курсу
     */
    public Boolean canAttachKyrs(String nameKyrs, Integer nKyrs)
    {
        Iterator<InfoKyrs> itr = listFinishedKyrs.iterator();

        while (itr.hasNext()) {
            InfoKyrs ik = itr.next();
            if ((ik.nameKyrs.equals(nameKyrs))&&(ik.nKyrs.equals(nKyrs)))
            {
               return false;

            }
        }

        return true;

    }

    /****
     * Выставляет курсу статус FINISH-завершен
     * @param nameKyrs - имя курса
     * @param nKyrs - номер курса
     */
    public void finishKyrs(String nameKyrs, Integer nKyrs)
    {
        ListIterator<InfoKyrs> itr = listFinishedKyrs.listIterator();

        while (itr.hasNext()) {
            InfoKyrs ik = itr.next();
            if ((ik.nameKyrs.equals(nameKyrs))&&(ik.nKyrs.equals(nKyrs)))
            {
               ik.state="FINISH";
               itr.set(ik);
            }
        }

    }

    /****
     * Возвращает список законченых студентом курсов
     * @return ArrayList<String>
     */
    public ArrayList<String> getFinishedKyrs()
    {
        ArrayList<String> lst = new ArrayList<String>();

        ListIterator<InfoKyrs> itr = listFinishedKyrs.listIterator();
        while (itr.hasNext()) {
            InfoKyrs ik = itr.next();
            if (ik.state.equals("FINISH"))
            {
                String str=ik.nKyrs.toString()+" - "+ik.nameKyrs;
                lst.add(str);
            }
        }
        return lst;
    }

    /****
     * Получение информации по студенту.
     * @return String
     */
    public String getInfoStudent()
    {
        StringBuilder bs=new StringBuilder();

        bs.append("Имя: "+name+" Адрес: "+adres+" Телефон: "+phone+" e-mail: "+
                eMeail+" Номер зачетки: "+nZach.toString()+" Средний бал:"+avgBall.toString());
        return bs.toString();
    }

}
