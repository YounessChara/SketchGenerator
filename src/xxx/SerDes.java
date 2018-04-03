package xxx;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class SerDes {

	public static void ser(Object o ) throws Exception { 
		FileOutputStream fos = new FileOutputStream("../serdes.txt");
		ObjectOutputStream os = new ObjectOutputStream( fos );
		os.writeObject(o);
		os.flush(); 
	}
	public static Object des( ) throws Exception { 
		FileInputStream fos = new FileInputStream("../serdes.txt");
		ObjectInputStream os = new ObjectInputStream( fos );
		Object o = os.readObject();
		return o;
	}
	
	public static void main(String[] args) throws Exception {
		TreeMap<String, Object> m =new TreeMap<String, Object>();
		m.put("name", "Chara");
		m.put("firstname", "Youness");
		ArrayList<String> phones = new ArrayList<String>()  ;
		phones.add("0689009");
		m.put("phones", phones);
		//ser(m);
		Object o = des( );
		System.out.println("o="+o);
		
	}
}
