import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

import sun.misc.IOUtils;

public class ParentingPartenering {
	static final int TJ=1;
	static final int TC=2;
	
	static class ACT implements Comparable<ACT>{
		int s;
		int e;
		int id;
		int d;
		int type;
		boolean j;
		boolean c;
		ACT(int id, int s, int e , boolean jnc) {
			this.s=s;
			this.e=e;
			this.id=id;
			d=e-s;
			j=jnc;
			c=!jnc;
			type=j?TJ :TC ;
		}
		@Override
		public int compareTo(ACT o) {
			if(s < o.s)
				return -1;
			if(s > o.s)
				return  1;
			return id-o.id;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			if(j)
				return "("+s+", "+e+"  /" +d +")";
			else 
				return "["+s+", "+e+"  /"+d+"]";
		}
	}
	 
	private static boolean enableLog = true;

	public static void log(Object... objects) {
		if (enableLog == false)
			return;
		for (Object o : objects)
			System.out.println(o);
	}
 
	static int NAc;
	static int NAj; 
	static int[] ACs;
	static int[] ACe;
	static int[] AJs; 
	static int[] AJe; 
	static TreeSet<ACT> setAC ;
	static TreeSet<ACT> setAJ ;
	static TreeSet<ACT> allA ;

 

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
 


		PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + ParentingPartenering.class.getSimpleName() + ".out");
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {

			 
			NAc= in.nextInt();
			NAj = in.nextInt();
			ACs =new int[NAc];
			AJs =new int[NAj];
			ACe =new int[NAc];
			AJe =new int[NAj];  
			setAC=new TreeSet<ACT>();
			setAJ=new TreeSet<ACT>();
			allA=new TreeSet<ACT>(); 
			for (int as = 0; as < NAc+NAj; as++) {
				if(as <NAc) { 
				 ACs[as]=in.nextInt();
				 ACe[as]=in.nextInt();
				 setAC.add(new ACT(as, ACs[as], ACe[as] , true));
				 allA.add(new ACT(as, ACs[as], ACe[as] , true));
				}
				else { 
				 AJs[as-NAc]=in.nextInt();
				 AJe[as-NAc]=in.nextInt();
				 setAC.add(new ACT(as-NAc, AJs[as-NAc], AJe[as-NAc] , false ));
				 allA.add(new ACT(as-NAc, AJs[as-NAc], AJe[as-NAc] , false ));
				}

			} 
			String res = resolve();
			System.out.println("Case #" + i + ": " + res);
			pw.println("Case #" + i + ": " + res);

		}

		pw.close();

	}

	private static String resolve() { 
		ACT fa = allA.first(); 
		System.out.println("allA="+allA);
		int firstLT =fa.j?TC:TJ;
		int lastLT =0;
		int lt=0;
		int ext = 0; 
		int cc=0; 
		
		for(ACT act:allA) {
			lastLT=act.type;
			 
				if(lt!=act.type) {
					ext++;
					lt=act.type;
				}
			if(act.c) 
				cc+=act.d;
			
		} 
		System.out.println("firstLT="+firstLT+"  lastLT="+lastLT);
		 return ""+(ext+(firstLT!=lastLT?1:0)) ;
	}



}
