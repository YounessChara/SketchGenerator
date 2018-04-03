package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

import com.sun.tools.xjc.reader.gbind.Sequence; 

public class ProperShuffle {
	static Random r = new Random(System.currentTimeMillis());
	private static boolean enableLog=false;
	public static void log(Object...objects  ) {
		if(enableLog==false)
			return;
		for(Object o:objects)
			System.out.println(o);
	}
	 
	 
	static int N;
	static int S;
	static String getBadRandomSeq(int n) {
		int[] a = new int[n];
		for (int i = 0; i < a.length; i++) {
			a[i]=i;
		}
		for (int i = 0; i < a.length; i++) {
			int p = r.nextInt( n);
			int tmp = a[p];
			a[p] = a[i];
			a[i]=tmp;
		}
		String ret="";
		String sep="";
		for (int i = 0; i < a.length; i++) {
			ret+=sep+a[i];sep=" ";
		}
		 
		return ret;
	}
	public static void main(String[] args) throws Exception {
		 TreeMap<String, Integer> stats=new TreeMap<String, Integer>(); 
		 int gg = 0;
		 int M = 1000;
		 int MMM = M*100 ;
		 while (gg < MMM) {
			 gg++;
			 if(gg%1000==0)
				 System.out.println("gg="+gg);
			 String s = getBadRandomSeq(1000);
			 if(stats.containsKey(s))
				 stats.put(s, stats.get(s)+1);
			 else
				 stats.put(s, 0);
		 }
		 System.out.println("cache generated!");
		 HashSet<Integer> goodCase=new HashSet<Integer>();
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		   PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\"+ProperShuffle.class.getSimpleName()+".out");
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) { 
		         N = in.nextInt(); 
		         String[] seqs =new String[N];
		         String sep="";
		         String s="";
		         for (int j = 0; j < seqs.length; j++) {
		        	 s+=sep+in.next();
					sep=" "; 
				}
		         if(stats.containsKey(s)==false && goodCase.size() <60) {
		        	 goodCase.add(i);
		         }
		        
		     
		    }
		    for(int i=1; i <=120 ; i++) {
		    	if(goodCase.contains(i))
				       pw.println("Case #" + i + ":  GOOD" );
		    	else
				       pw.println("Case #" + i + ":  BAD" );
		    		
		    } 
		    pw.close();
		    System.out.println(" goodCase.size()="+goodCase.size());
	}
	 

}
