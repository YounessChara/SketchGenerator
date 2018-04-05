package xxx;
import java.util.*;
import java.io.*;
public class TidyNumber_S {

	public static void main(String[] args) {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) {
		      String number = in.next();
		      
		      String res= handle(number );
		      System.out.println("Case #" + i + ": " + trimLeft0( res));
		    }
	}

	private static String trimLeft0(String res) {
		while(res.startsWith("0"))
			res=res.substring(1);
		return res;
	}

	private static String handle(String w ) {
		if(w.length()==0 || w.length()==1)
			return w;
		 for(int i = 0; i < w.length() -1 ; i++ )  {
			 char c = w.charAt(i);
			 char cn = w.charAt(i+1);
			 if(c <=cn)
				 continue;
			 return handle(padd9(w.substring(0, i), (char) (c-1),   w.length()));
		 }
		 return  w;
	}

	private static String padd9(String w, char i, int length) {
		String s =w;
		s=s+((char)i);
		while(s.length() <length)
			s=s+9;
		
		return s;
	}

	private static void log(int[] row, int sw) {
		if(false)
			return;
		System.out.println(">> "+sw+": ");
		for (int i = 0; i < row.length; i++) {
			System.out.print(""+ (row[i]==0?"+":"-"));
		}
		System.out.println();
	/*	for (int i = 0; i < row.length; i++) {
			System.out.print(""+ row[i]);
		}
		System.out.println(); */
	}

	private static void swap(int[] row, int ii, int k) {
		for (int i = ii; i < ii+k; i++) {
			if(row[i]==1)
				row[i]=0;
			else
				row[i]=1;
		}
		
	}

	private static boolean check(int[] row) {
		for (int i = 0; i < row.length; i++) {
			if(row[i]==1)
				return false;
		}
		return true;
	}
}
