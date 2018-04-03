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

public class CruiseControl {


	private static int toolCap(int x, int i, int s) {
		if (i < s) {
			if (x < i)
				x = i;
			if (x > s)
				x = s;
		}
		if (s < i) {
			if (x < s)
				x = s;
			if (x > i)
				x = i;
		}
		return x;
	}
	private static boolean enableLog = true;

	public static void log(Object... objects) {
		if (enableLog == false)
			return;
		for (Object o : objects)
			System.out.println(o);
	}
 
	static int N;
	static long D; 
	static int[] positions;
	static int[] vitesses; 

 

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		PrintWriter pwc = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + CruiseControl.class.getSimpleName() + ".java");


		PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + CruiseControl.class.getSimpleName() + ".out");
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {

			 
			D = in.nextInt();
			N = in.nextInt();
			positions =new int[N];
			vitesses =new int[N];
			System.out.println("processinf case "+i+" N="+N+ " D="+D);
			for (int as = 0; as < N; as++) {
				 positions[as]=in.nextInt();
				 vitesses[as]=in.nextInt();

			} 
			String res = resolve();
			System.out.println("Case #" + i + ": " + res);
			pw.println("Case #" + i + ": " + res);

		}

		pw.close();

	}

	private static String resolve() {
		double sf = Double.MAX_VALUE;
		System.out.println();
		for (int i = positions.length-1; i >=0 ; i--) {
			int si=vitesses[i];
			int ki=positions[i] ;
		 	System.out.println ("H"+i+ "  ki="+ki+" si="+si);
			double s = (si*D)*1.0/(D-ki);
		 	System.out.println("s["+i+"]="+s+" arrival time="+(D/s)+ " others position then = "+ (ki+(D/s)*vitesses[i]));
			
			if(s <sf)
				sf = s;
		}
		return ""+sf ;
		 
	}



}
