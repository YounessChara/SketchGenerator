import java.util.Scanner;

public class FullColoredGrid {


	 
 
 
	private static final long mod = 1000000007L;
	static long N;
	static long C; 
	 

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);

		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {

			 
			N = in.nextLong();
			C = in.nextLong();
			 
			String res = resolve();
			System.out.println( res); 

		}
 
	}

	private static String resolve() { 
		long asym=asym();
		long sym2=sym2();
		long sym4=sym4();
		long sf =  asym + sym2 + 2*sym4; 
		return ""+( (sf % mod) * 250000002) % mod ;
		 
	}

	
	 
	private static long pow(long  base, long exp) {
		if(exp==0)
			return 1; 
		 base=base%mod; 
		 long newbase=(base*base)%mod;
		 if(exp%2==1)
			 return (base*pow(newbase, exp/2))%mod;
		 else
			 return (pow(newbase, exp/2));
	}
	private static long sym4() {
		long N4 = (N%2==0) ? (5*N*N/4  )
				:
					((5*N*N-1)/4+1);
		return (long) pow(C, N4);
	}

	private static long sym2() {
		long N2 = (N%2==0) ? (5*N*N/2  )
				:
					((5*N*N-1)/2 + 1);
		return (long) pow(C, N2);
	}

	private static long asym() {
		 
		return (long) pow(C, 5*N*N);
	}



}
