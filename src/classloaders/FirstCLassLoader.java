package classloaders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import sun.misc.IOUtils;
 

public class FirstCLassLoader  extends ClassLoader {

	File dir =null;
	
	
	public FirstCLassLoader(String dirPath) {
		super();
		dir =new File( dirPath );
		
		
	}


	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File classFile = new File (dir, name.replace(".", "/")+".class");
		
		try {
		 if(classFile.exists()) {
			 FileInputStream fis =new FileInputStream(classFile  );
				byte[] data = IOUtils.readFully(fis, -1, true);
				System.out.println("loded data = "+data+" "+(data!=null? data.length: 0));
				Class<?> clazz = defineClass(name, data, 0, data.length, null);
				System.out.println("loded class = "+clazz);
				return clazz;
			}
			
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return  super.findClass(name); 
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, Exception {
		String dirPath="C:/Users/Youness/workspace/karaz-docsigner/target/classes" ;
		FirstCLassLoader fcl =new FirstCLassLoader(dirPath);
		Class<?> clazz = fcl.loadClass("ma.ribatis.karaz.desktop.Util", true);
		System.out.println("xx   " );
		Comparable  inst = (Comparable) clazz.newInstance();
		
		System.out.println("inst = "+inst.compareTo(inst));
		
	}
}
