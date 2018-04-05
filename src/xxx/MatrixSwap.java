package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

public class MatrixSwap {
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
	static int N2; 
	
	static int[] M; 

	 

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + MatrixSwap.class.getSimpleName() + ".out");
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {

		 
			N = in.nextInt();
			N2=2*N;
			M=new int[4*N*N];
			System.out.println("N="+N+" M="+M.length);
			for (int as = 0; as < N2; as++) {
				String row = in.next();
				int j=0;
				for(char c:row.toCharArray()) {
					M[as*N2+j]=(c=='0')?0:1;
					j++;
				}

			}
			System.out.println("MZ "+M[M.length-1]);
			boolean impossible=false;
			for (int j = 0; j < N2; j++) {
				int rowTot=0;
				for (int j2 = 0; j2 < N2; j2++) {
				//	System.out.println("M["+j+","+j2+"]="+M[j*N2+j2]);
					rowTot += M[j*N2+j2];
				}
				if(rowTot!=N) {
					System.out.println("rowTot="+rowTot+" for r="+ j );
					impossible = true;
				}
			}
			for (int j = 0; j < N2; j++) {
				int rowTot=0;
				for (int j2 = 0; j2 < N2; j2++) {
					rowTot += M[j2*N2+j];
				}
				if(rowTot!=N) {System.out.println("rowTot="+rowTot+" for  c="+ j );
				
					impossible = true;
				}
			}
			if(impossible)
			{
				System.out.println("Case #" + i + ": IMPOSSIBLE" );
				pw.println("Case #" + i + ": IMPOSSIBLE" );
			} else { 
				int swap0R = 0;
				int swap1R = 0;
				for (int j = 0; j < N2; j++) {
					int v = M[j*N2];
					if(v==0 && j%2!=0) {
						swap0R++;
					}
					if(v==1 && j%2!=0) {
						swap1R++;
					}
				}
				
				int swap0C = 0;
				int swap1C = 0;
				for (int j = 0; j < N2; j++) {
					int v = M[j];
					if(v==0 && j%2!=0) {
						swap0C++;
					}
					if(v==1 && j%2!=0) {
						swap1C++;
					}
				}
				int totSw = Math.min(swap0C+swap0R, swap1C+swap1R);
			System.out.println("Case #" + i + ": " + totSw);
			pw.println("Case #" + i + ": " + totSw);
			}

		}

		pw.close();

	}

	 



}
