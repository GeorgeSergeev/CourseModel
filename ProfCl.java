/*   */
import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.List; 
 
public class ProfCl implements Serializable {  // ProfCl

    int prof_id;
    String name; 
    String address; 
    String phone; 
    float salary; 
 
    public ProfCl(int prof_id,String name, String address, String phone, float salary) { 
        
        this.prof_id = prof_id;
        this.name = name; 
        this.address = address; 
        this.phone = phone; 
        this.salary = salary; 
    } 
  
 
  
    @Override 
    public String toString() { 
        return  "ProfCl {name = " + name + "; address = " + address +"; phone = " + 
                phone + "; salary = " + salary + "}"; 
    } 
}
