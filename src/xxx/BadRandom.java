package xxx;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.TreeMap;

public class BadRandom {

	static Random r = new Random(System.currentTimeMillis());
	
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
	
	static long fact(int n) {
		if(n<=1)
			return 1;
		return n*fact(n-1);
	}
	public static void mainx(String[] args) { 
		TreeMap<String, Integer> stat=new TreeMap<>();
		int n=10;
		long nf=fact(n);
		long T = n;
		 
		System.out.println("stat="+stat);
		for(long i = 0; i <T; i++) {
			String seq = getBadRandomSeq(n);
		//	System.out.println("seq="+seq);
			if(stat.containsKey(seq))
			 stat.put(seq, stat.get(seq)+1); 
			else
			  stat.put(seq,  1); 
				
		}
		System.out.println(" IDEAL="+(1.0/nf));
		for (String k:stat.keySet()) {
			if(stat.get(k)>0) {
		//		System.out.println("% of "+k+" ="+ (""+(1.0*stat.get(k)/T)+"00000").substring(0,  5) +" ratio="+( (1.0*nf*stat.get(k)/T)));
				
				System.out.println(""+k+"\t"+ (""+(1.0*stat.get(k)/T)+"00000").substring(0,  7) +" \t"+( (1.0*nf*stat.get(k)/T)));
			} 
		}
		 
	}
	
	public static void main(String[] args) {
		
		for(int m=10; m < 10000; m=m*2) { 
			int M=m*100; 
		int[] dp = new int[m+1];
		for (int i = 0; i <= m; i++) {
			dp[i]=0;
		}

		Random r =new Random(new Date().getTime());
		for (int i = 0; i < M; i++) {
			int cf=0;
			for (int j = 0; j < m; j++) { 
				cf+= r.nextInt(200000)%2;
			}
			dp[cf]=dp[cf]+1;
			
		}
		  double mean=0;
		for (int i = 0; i < dp.length; i++) {
		//	System.out.println("p("+i+")="+(1.0*dp[i]/M)+" soit "+dp[i]);
			mean+=i*(1.0*dp[i]/M);
		}
		double sigma2=0;
		for (int i = 0; i < dp.length; i++) {
			 
			sigma2+=(1.0*i-mean)*(1.0*i-mean)*(1.0*dp[i]/M);
		}
		System.out.println("m="+m+" M="+M);
		System.out.println("AVG="+1.0*mean/m);
		System.out.println("3*STD="+3*Math.sqrt(sigma2)+"   S/m="+   Math.sqrt(sigma2)/m +"  S*S/m="+   sigma2/m );
	 	}
	}
}
