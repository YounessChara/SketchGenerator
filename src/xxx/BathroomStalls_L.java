package xxx;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
public class BathroomStalls_L {

	public static void main(String[] args) {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) {
		      long N = in.nextLong();
		      long K = in.nextLong();
		      
		      String res= handle(N, K );
		      System.out.println("Case #"+ i + ": " +  res);
		    }
		    in.close();
	}

 
	public static class Doublon {
		long min;
		long max;
		long step;
	}
	private static String handle(long  N, long K) {
		TreeMap<Long, Long> windows =new TreeMap<Long, Long>();
		windows.put(N, 1L);
		 
		 Doublon res = new Doublon();
		 Long step=1L;
		 for(long i=1; i <= K; i+=step) {
			 res = enter(windows, step);
			 step=Math.min(res.step, K-i+1);
			 
		//	 if(i%1000==0 || i/1000==0)
		//		 System.out.println(">> "+i+" step="+step+ " >> ws="+windows);
		//	 log(stalls, i);
		 }
		 return ""+ res.max+" "+res.min;
		 
	}

	 
	private static Doublon enter(TreeMap<Long, Long> windows, long pack) { 
		Entry<Long, Long> greatest = windows.lastEntry() ;
		Long wSize = greatest.getKey();
		Long wOccurence = greatest.getValue();
		Long min = (wSize-1)/2;
		Long max =  wSize-1 -min;
		
		 
		 Doublon d = new Doublon();		 
		 d.min = min;
		 d.max = max;
		 if(wOccurence >pack ) {
			 windows.put(wSize, wOccurence-pack);
			 d.step=wOccurence-pack;
			 
		 } else {
			 windows.remove(wSize);
			 d.step=1;
		 }
		 windows.put(min,  trim(windows.get(min))+pack);
		 windows.put(max,  trim(windows.get(max))+pack);
		return d;
	}

   
	private static Long trim(Long l) { 
		return l==null? 0L:l;
	}


	 
	 
 
}
