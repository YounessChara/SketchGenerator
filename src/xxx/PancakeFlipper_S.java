package xxx;
import java.util.*;
import java.io.*;
public class PancakeFlipper_S {

	public static void main(String[] args) {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) {
		      String row = in.next();
		      int k = in.nextInt();
		      String res= handle(row, k);
		      System.out.println("Case #" + i + ": " + res);
		    }
	}

	private static String handle(String word, int k) {
		int n = word.length();
		//System.out.println("n="+n+" k="+k);
		if( n<=k) {
			if(word.matches("\\+*"))
				return "0";

			if(word.matches("\\-*") && n==k)
				return "1";

			return "IMPOSSIBLE";
		}
		int[] row = new int[n] ;
		for(int i=0; i <n; i++) {
			row[i] = (word.charAt(i)=='+')?0:1;
		}
		int sw=0;

		//log(row, sw);
		for(int i=0; i <n-k+1; i++) {
			if(row[i] ==0 )
				continue;
			swap(row, i, k);
			sw++;
			//log(row, sw);
		}
		if(check(row))
			return ""+sw;
		return "IMPOSSIBLE";
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
