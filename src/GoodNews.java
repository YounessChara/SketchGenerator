
import java.io.*;
import java.util.*;

public class GoodNews {
	int F;
	int P;
	int[][] G;
	private int[] endPhase;

	public GoodNews( int F, int P, int[][] G, int caseNumber) {
		this.F = F;
		this.P = P;
		this.G = G;
		sort(this.G, G.length-1);
		endPhase = new int[P];
		
		for(int i  = 1; i<=F; i++ ) {
			int ep=0;
			for (int j = 0; j < G.length; j++) {
				if(G[j][0]==i || G[j][1]==i)
					ep=j;
			}
			if(endPhase[ep]>0) {
				
			//	throw new RuntimeException("END PHASE ALREADY SET ep="+ep+" i="+i+" endPhase[ep]="+endPhase[ep] );
			}
			endPhase[ep]=i;
		}
		printArray(endPhase, "ENDPHASE");
	}

	private void  sort(int[][] g, int st) {
		if(st<=0)
			return ;
		for(int i = 0; i <st; i++ ) {
			if((Math.min(g[i][0], g[i][1])-Math.min(g[i+1][0], g[i+1][1])) >0) {
				int[] tmp = g[i+1];
				g[i+1]=g[i];
				g[i]=tmp;
			}
		}
		  sort(g, st-1);
	}

	 

	public static void main(String[] args) throws FileNotFoundException {
	/*	Random random = new Random();
		TreeSet<String> dones=new TreeSet<String>();
		System.out.println("100 200");
		for(int i=1; i <=200; i++) {
			int s = random.nextInt(100);
			int e = random.nextInt(100);
			if(e==s || dones.contains(""+s+" "+e)) {
			i=i-1;
			continue;
			}
			System.out.println(""+s+" "+e);
			dones.add(""+s+" "+e);
			
		}*/
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		PrintWriter pw = new PrintWriter("C:/Users/Youness/Downloads/" + "GoodNews" + ".out");
		int T = in.nextInt();
		for (int i = 1; i <= T; ++i) {
			int F = in.nextInt();

			int P = in.nextInt();

			int[][] G = new int[P][3];
			System.out.println("P="+P);
			for (int i1 = 0; i1 < P; i1++) {
				 int f1 = in.nextInt();
				 int f2 = in.nextInt();
				 
				 G[i1][0] = f1;
				 G[i1][1] = f2;
			}

			GoodNews testCase = new GoodNews(F, P, G, i);
			String res = testCase.solve();
			pw.println("Case #" + i + ": " + res.trim());
			pw.flush();
			System.out.println("Case #" + i + ": " + res.trim());
		}
		in.close();
		pw.close();
	}

	private String solve() {
		int[] sr= new int[F]; 
		boolean r= dnpSolve(0, sr, "");
		String ret = "IMPOSSIBLE";
		if(!r)
			return ret;
		ret="";
		String sep="";
		for (int i = 0; i < G.length; i++) {
			ret += sep+ G[i][2];
			sep=" ";
		}
			
		return ret;
	}

	private boolean dnpSolve(int current, int[] sr, String offset) {

		System.out.println(offset+" dnpSolve("+current+", sr=");
		printArray(sr, offset+" -->");
		//
		if(current==P) {
			return check(sr);
		}
		if(current-1 > 0 && endPhase[current-1]!=0) {
			System.out.println(offset+" endPhase["+(current-1)+"]="+endPhase[(current-1)]);
			printArray(sr, offset+" SR=") ;
			if(sr[endPhase[current-1]-1]!=0) {
				return false;
			}
		}
	
		for(int n=-F; n<=F; n++ ) {
			if(n==0)
				continue;
			G[current][2] = n;
			
		
			
			
			sr[G[current][0]-1]+=n;
			sr[G[current][1]-1]-=n;
			if(dnpSolve(current+1, sr, offset+" *** ")) {
				return true;
			}
			sr[G[current][0]-1]-=n;
			sr[G[current][1]-1]+=n;
			
		}

		return false;
	}

	private void printArray(int[] sr, String mes) {
		System.out.print(mes);
		for (int i = 0; i < sr.length; i++) {
			System.out.print(" "+sr[i]);
		}
		System.out.println();
		
	}

	private boolean check(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean check(int[] sr) {
		 
			printArray(sr, "CHECK");
		 
			for (int i = 0; i < sr.length; i++) {
				if(sr[i]!=0) {
					System.out.println("check "+(i+1)+"="+sr[i]);
					return false;
				}
			}
		 
		return true;
	}

	 
}
