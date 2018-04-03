package xxx;
import java.util.*;
import java.io.*;
public class BathroomStalls_S {

	public static void main(String[] args) {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) {
		      long N = in.nextLong();
		      long K = in.nextLong();
		      
		      String res= handle(N, K );
		      System.out.println("Case #" + i + ": " +  res);
		    }
		    in.close();
	}

 
	public static class Doublon {
		long min;
		long max;
	}
	private static String handle(long  N, long K) {
		 int[] stalls =new int[(int) N] ; 
		 for (int i = 0; i < stalls.length; i++) {
			stalls[i]=0;
		}
		 Doublon res = new Doublon();
		 for(int i=1; i <= K; i++) {
			 res = enter(stalls);
		//	 log(stalls, i);
		 }
		 return ""+ res.max+" "+res.min;
		 
	}

	 
	private static Doublon enter(int[] stalls) { 
		long cwSize = 0;
		long cwStart = 0;
		long lwSize = 0;
		long lwStart = 0;
		 for (int i = 0; i < stalls.length; i++) {
			 if(stalls[i]==0    ) {
				 cwSize++;
				 if(cwStart<0)
					 cwStart=i;
			 } else {
				 if(cwSize>lwSize) {
					 lwSize=cwSize;
					 lwStart=cwStart;
				 } 
				 cwStart=-1;
				 cwSize=0;
			 }
			}
		 if(cwSize>lwSize) {
			 lwSize=cwSize;
			 lwStart=cwStart;
		 } 
		 Doublon d = new Doublon();
		 //System.out.println("lwStart="+lwStart+" lwSize="+lwSize);
		 stalls[(int) (lwStart+(lwSize-1)/2)]=1;
		 d.min = (lwSize -1)/2;
		 d.max = lwSize -1 -d.min;
		return d;
	}


	private static void log(int[] row, int sw) {
		 
		System.out.println(">> "+sw+": ");
		for (int i = 0; i < row.length; i++) {
			System.out.print(""+ (row[i]==0?"-":"x"));
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
