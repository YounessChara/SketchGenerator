
import java.io.*;
import java.util.*;

public class D {
	int R;
	int C;
	int M;
	int[][] g;

	public D( int R, int C, int M, int[][] g, int caseNumber) {
		this.R = R;
		this.C = C;
		this.M = M;
		this.g = g;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		PrintWriter pw = new PrintWriter("C:/Users/Youness/Downloads/" + "D" + ".out");
		int T = in.nextInt();
		for (int i = 1; i <= T; ++i) {
			int R = in.nextInt();

			int C = in.nextInt();

			int M = in.nextInt();
			int[][] g = new int[R][C];
			for (int i1 = 0; i1 < R; i1++) {
				for (int i2 = 0; i2 < C; i2++) {
				}
			}

			D testCase = new D(R, C, M, g, i);
			String res = testCase.solve();
			pw.println("Case #" + i + ": " + res.trim());
			pw.flush();
			System.out.println("Case #" + i + ": " + res.trim());
		}
		in.close();
		pw.close();
	}

	private String solve() { 
		 
		return "";
	}

	 

}
