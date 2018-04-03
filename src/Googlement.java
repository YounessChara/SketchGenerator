
import java.io.*;
import java.util.*;

public class Googlement {
	String G;
	int L ;
	int[] digits;
	public Googlement( String G, int caseNumber) {
		this.G = G;
		this.L = G.length();
		digits=new int[L];
		for(int i=0; i <L; i++) {
			digits[i] = G.charAt(i)-'0';
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		PrintWriter pw = new PrintWriter("C:/Users/Youness/Downloads/" + "Googlement" + ".out");
		int T = in.nextInt();
		for (int i = 1; i <= T; ++i) {
			String G = in.next();

			Googlement testCase = new Googlement(G, i);
			int res = testCase.solve();
			pw.println("Case #" + i + ": " + res );
			pw.flush();
			System.out.println("Case #" + i + ": " + res );
		}
		in.close();
		pw.close();
	}

	private int  solve() {
		int tot =1;
		while(true) {
		int[] partition = new int[L];
		int sum=0;
		int denominatorFact = 1;
		for (int i = 0; i < L; i++) {
			partition[i] = digits[i];
			sum+=digits[i];
			denominatorFact*=factorial(digits[i]);
		}
		
		denominatorFact*=factorial(L-sum) ;
//01234
		if(sum >L) {
			break;
		}
		System.out.println("L="+L+" factorial(L)="+factorial(L)+" denominatorFact="+denominatorFact);
		int possibilities = factorial(L)/denominatorFact;
		tot+=possibilities;
		int[] nexDigit=new int[L] ;
		int k=0;
		for (int i = 0; i < partition.length; i++) {
			//1100
			//1002 --> 4*2+1
			//2001 --> 2+4
			//1200 --> 1+4
			//2100 --> 2+1
		//	System.out.println("partition["+i+"]="+partition[i]);
			for(int j=1; j<=partition[i]; j++) {
				nexDigit[k]=i+1;
				k++;
			}
		}
		if(equalsArray(nexDigit, digits)) {
			tot=tot-1;
			break;
		}

		printArray(nexDigit) ;
		 digits=nexDigit;
		}
		
		 
		return tot;
	}
	private boolean equalsArray(int[] x, int[] y) {
		if(x.length!=y.length)
			return false;
		for (int i = 0; i < y.length; i++) {
			if(x[i]!=y[i])
				return false;
		}
		return true;
	}

	private void printArray(int[] partition) {
		for (int i = 0; i < partition.length; i++) {
			System.out.print(""+partition[i]);
		}
		System.out.println();
		
	}

	int factorial (int n) {
		int x = 1;
		for(int i=2;i<=n; i++ )
			x*=i;
		return x;
	}
	private int sumDigits(int[] d) {
		int tot=0;
		for (int i = 0; i < d.length; i++) {
			tot+=d[i];
		}
		return tot;
	}

	private List<String> dnpSolve() {

		return null;
	}

	private String buildKey() {
		return null;
	}

}
