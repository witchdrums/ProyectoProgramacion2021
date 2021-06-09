import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.Vector;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Serialization {

	public static void main(String[] args) {

		try {
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("Usuarios.obj"));
			//ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("PersonList.obj"));
			Vector<User> users = new Vector<User>();
			fillVector(users);
			objectOut.writeObject(users);
			objectOut.close();
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("Usuarios.obj"));
			Vector<User> users2 = (Vector<User>)objectIn.readObject();
			for(User e : users2) System.out.println(e.toString());
		}catch(IOException e) {
			System.out.println(e);
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}
		
	}
	public static void fillVector(Vector<User> users) {
		users.add(new User("Alejandro Chacon Fernandez"));
		users.add(new User("Hector Manuel Toral Huerta"));
		users.add(new User("Katherine Bautista Marquez"));
		users.add(new User("Pablo Hernan Moreno"));
	}
}
