//User.java		|	 Alejandro Chacon
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.Scanner;

public class User implements Serializable {
private String nombre;
private String ID;
private String fechaIngreso;
public String password;
    public User(){}
    public User(String n){
        this.nombre = n;
        this.ID = makeID();
        this.fechaIngreso = getDate();
    }
    //SET/GET
    public void setNombre(String n) { this.nombre = n; }
    public String getNombre () {return this.nombre;}
    public void setID(String nE){ this.ID = nE;}
    public String getID () {return this.ID;}
    public void setFechaIngreso(String fI) { this.fechaIngreso = fI; }
    public String getFechaIngreso () {return this.fechaIngreso;}
    public void setPass(String p){this.password = p;}
    public String getPass(){return this.password;}
    @Override
    public String toString(){ return this.ID + " | " + this.nombre; }
    //METODOS
    String getDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    String makeID(){
    	Scanner scan = new Scanner(this.nombre);
    	scan.tokens();    	String ID = "";
    	while(scan.hasNext()) {
    		char[] a = scan.next().toCharArray();
    		ID += a[0];
    	}
    	scan.close();
    	ID += (int)Math.floor(Math.random()*(999-100+1)+100);
        return ID;
    }
}