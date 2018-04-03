
import java.io.*;
import java.util.*;

public class Permutation {
	int R;
	int[] g;
	int[] a;

	public Permutation(	int R, int[] g, int caseNumber) {
		this.R = R;
		this.g = g;
		this.a=new int[g.length];
		for (int i = 0; i < g.length; i++) {
			a[i]=i;
		}
	}
public static void mainx(String[] args) {
	int[] x = new int[] {3, 4, 2, 5, 1};
	printArray(x, "x1");
	sortBuble(x, 0, x.length-1);
	printArray(x, "x2");
	 
	
}
	private static void printArray(int[] x, String m) {
	System.out.print(m +" : ");
	for (int i = x.length-1; i >=0 ; i--) {
		System.out.print(" "+x[i]+"," );
	}
	System.out.println();
	
}
	
	private void printPermute(int[] g2, int[] x, String m) {
		System.out.print(m +" : ");
		for (int i = x.length-1; i >=0 ; i--) {
			System.out.print(" "+g2[x[i]]+"," );
		}
		System.out.println();
		
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		PrintWriter pw = new PrintWriter("C:/Users/Youness/Downloads/" + "Permutation" + ".out");
		int T = in.nextInt();
		for (int i = 1; i <= T; ++i) {
			int R = in.nextInt(); 
			int[] g = new int[R];
			for (int i1 = 0; i1 < R; i1++) {
				g[i1]=in.nextInt();
			} 
			Permutation testCase = new Permutation(R, g, i);
			String res = testCase.solve();
			pw.println("Case #" + i + ": " + res.trim());
			pw.flush();
			System.out.println("Case #" + i + ": " + res.trim());
		}
		in.close();
		pw.close();
	}

	
	private boolean increment() {
		//43210
		//43201
		//43120
		//43102
		//43021
		//43012
		//42310
		//42301
		//42130
		//42103
		//42031
		//42013 3210
		//41321
		for (int i = 0; i < a.length-1; i++) {
			if(a[i+1] > a[i] ) { 
					int ni = -1;
					int jx=0;
					for(int j=0; j <=i; j++  ) {
						if(a[j] < a[i+1] && a[j] > ni) {
							ni=a[j];
							jx=j;
						}
					}
					swap(a, jx, i+1);
					sortBuble(a, 0, i);
					return true;
				 
			}
		}
		return false;
	}
	
	
	private static void sortBuble(int[] x, int s, int e) {
		if(e<s+1) 
			return;
		for(int i=s; i <e; i++) {
			if(x[i]>x[i+1]) {
				swap(x, i, i+1);
			}
		}
		sortBuble(x, s, e-1); 
	}

	private static void sortMerge(int[] x, int s, int e) {
		if(e<s+1) 
			return;
		int m = (e +s )/2;
		sortMerge(x, s, m); 
		sortMerge(x, m, e+1); 
		for(int i=s; i <e; i++) {
			if(x[i]>x[i+1]) {
				swap(x, i, i+1);
			}
		}
		
	}
	

	private static void swap(int[] x, int i, int j) {
		int tmp= x[i];
		x[i]=x[j];
		x[j]=tmp; 
	}
	
	

	private String solve() {
		List<String> shots = dnpSolve();
		String ret = "" + shots.size();
		for (String shot : shots)
			ret += "\n" + shot;
		return ret;
	}
	
	
	

	private List<String> dnpSolve() {
		sortBuble(a, 0, a.length-1); 
		 
		int ii=1; 

		printPermute(g, a, "WRAPING G "+ii); 
		printArray(a,      "INCRMNT "+ii); 
		while(increment()) { 
		ii++;
		}

		printPermute(g, a, "WRAPING G "+ii); 
		printArray(a,      "INCRMNT "+ii); 
		return new ArrayList<String>();
	}

	
	private int factortial(int r2) {
		int ret=1;
		for(int i=2; i<=r2; i++)
			ret*=i;
		return ret;
	}
	private String buildKey() {
		return null;
	}

}
