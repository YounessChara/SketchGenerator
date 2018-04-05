
import java.io.*;
import java.util.*;


public class Sliders {
	class Node {
		int id;
		TreeSet<Integer> children =new TreeSet<Integer>();
	}
	int B;
	int M;
	int[][]  paires;
	int[][]  g;
	Node[] nodes;
	public Sliders( int B, int M, int caseNumber) {
		this.B = B;
		this.M = M;
		nodes= new Node[B];
		paires  = new int[B*(B-1)/2][2];
		g=new int[B][B];
		int x=0;
		for(int i=0; i <B; i++) {
			for(int j=i+1; j<B; j++) {
				paires[x][0]=i;
				paires[x][1]=j;
			 x++;
			}
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		PrintWriter pw = new PrintWriter("C:/Users/Youness/Downloads/" + "Sliders" + ".out");
		int T = in.nextInt();
		for (int i = 1; i <= T; ++i) {
			int B = in.nextInt();

			int M = in.nextInt();

			Sliders testCase = new Sliders(B, M, i);
			String res = testCase.solve();
			pw.println("Case #" + i + ": " + res.trim());
			pw.flush();
			System.out.println("Case #" + i + ": " + res.trim());
		}
		in.close();
		pw.close();
	}

	private String solve() {
		int[] done = createArray(B-1);
		ArrayList<int[]> joints = new ArrayList<int[]>();
		boolean r = bruteForce();
		if(r) {
			String ret = "POSSIBLE\n" ;
			for (int i = 0; i < B; i++) {
				for (int j = 0; j < B; j++) { 
						ret+=""+g[i][j]; 
				} 
				ret+="\n";
			}
			 
			return ret;
		} else {
			return "IMPOSSIBLE";
		}
		
	}

	

	private boolean isIn(int i, int j, ArrayList<int[]> joints) {
		for(int[] a:joints) {
			if(a[0]==i && a[1]==j)
				return true;
		}
		return false;
	}

	private boolean bruteForce(  ) {  
		long Z=(B*(B-1)/2);
		for(int n=0; n<(1<<Z ) ; n++ ) { 
			for(int i=0; i<(B*(B-1)/2); i++) {
				int[] paire =paires[i];
				if((n&(1<<i)) !=0) {
					g[paire[0]][paire[1]] =1;
				} else { 
					g[paire[0]][paire[1]] =0;
				}
			}
			 
			 
			int[] done = new int[B] ;
			int nbr = dfs(0,  g, done, M); 
			if(nbr==M) {
				return true;
			}
		}
		return false ;
	}
	
	private int dfs(int id,  int[][] g2, int[] done, int limit) {
		 
		if(id==B-1)
			return 1;
		int found =0;
		for(int jd =id; jd<B; jd++ ) {
			if(done[jd]==0 && g2[id][jd]==1) {
				done[jd]=1;
				found += dfs(jd, g2, done, limit);
				done[jd] =0;
			}
			if(found>limit) 
				return found;
		}
		  return found;
	}

	private int dnpSolve( int src , int target, int[] done, ArrayList<int[]> joints, String offset) {  
 
		int forkNbr =1; 
		if(forkNbr>=target) { 
			return forkNbr;
		}
		joints.add(new int[] {src, B-1});
			for (int i = 0; i < done.length; i++) {
			//	System.out.println("DBG "+offset+" i="+i);
				if(i==src)
					continue;
				if(done[i]==1)
					continue;
				
				if(accepted (src, i, joints)) {

				//	System.out.println("DBG "+offset+" ACCEPTED="+src+" "+i);
					joints.add(new int[] {src, i});
				 
					done[src]=1;
					int sf = dnpSolve(i, target-1, done , joints , offset+" #");
					done[src]=0;
					forkNbr+=sf;
					if(forkNbr>=target) {
						System.out.println("XXXXX "+forkNbr+" "+target);
						return forkNbr;
					}
				}
			} 

			System.out.println("DBG "+offset+" forkNbr="+forkNbr+" " );
		 return forkNbr;
	}

	 
	private int[] cloneArray(int[] done) {
		int[] x=new int[done.length];
		for (int i = 0; i < x.length; i++) {
			x[i]=done[i];
		}
		return x;
	}
	
	void f(Integer x) { };

	
	private boolean accepted(int src, int i, ArrayList<int[]> joints) {
		long l=0;
		f( (int ) l);
		
		for(int[] j:joints) {
			if(j[0]==i) {
				if(j[1]==src)
					return false;
				if(!accepted(src, j[1], joints)) {
					return false;
				}
			}
		}
		return true;
	}

	private int[] createArray(int size) {
		int[] x = new int[size];
		return x;
	}
	 
}
