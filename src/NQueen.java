
import java.io.*;
import java.util.*;

public class NQueen {
	int B;
	int N;
	int[][] g ;
	int[] DX = new int[] {-1, 0, 1, 1, 1, 0, -1, -1};
	int[] DY = new int[] {-1, -1, -1, 0, 1, 1, 1, 0};
	int[] reservedX ;
	int[] reservedY ;
	int[] reservedDP ;

	int[] reservedDM ;
	public NQueen(
			int B, int N, int caseNumber) {
		this.B = B;
		this.N = N;
		this.g=new int[B][B];
		reservedX = new int[B];
		reservedY = new  int[B];
		reservedDP = new int[2*B+1];
		reservedDM= new  int[2*B+1];
		
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

		PrintWriter pw = new PrintWriter("C:/Users/Youness/Downloads/" + "NQueen" + ".out");
		int T = in.nextInt();
		for (int i = 1; i <= T; ++i) {
			int B = in.nextInt();

			int N = in.nextInt();

			NQueen testCase = new NQueen(B, N, i);
			String res = testCase.solve();
			pw.println("Case #" + i + ": " + res.trim());
			pw.flush();
			System.out.println("Case #" + i + ": " + res.trim());
		}
		in.close();
		pw.close();
	}

	private String solve() {
		//g[0][0]=1;
	
		boolean res =  dnpSolve(0, 0, g ,  0, ""); 
		 String ret=(res? "POSSIBLE": "IMPOSSIBLE")+ "\n";
		for (int i = 0; i < B; i++) {
			for (int j = 0; j < B; j++) {
				ret+=g[i][j]==1?"Q ":"X ";
			}
			ret+="\n";
		}
		return ret;
	}

	

	 
	private boolean dnpSolve(int x, int y , int[][] g, int deep, String offset) {
		if(deep==N) {
			return true;
		}
		for(int ny=0; ny <B; ny++) {
			if(reservedY[ny]==1)
				continue;
		//	for(int nx=0; nx <B; nx++) {	 
			int nx=x;
				if(reservedX[nx]==1)
					continue;
				if(reservedDM[B+ny-nx]==1)
					continue;
				if(reservedDP[ny+nx]==1) 
					continue;
		//	boolean accepted = accepted(nx, ny, g);
		//	System.out.println(offset+" accepted = "+accepted+" nx="+nx+" ny="+ny);
		//	if(accepted) {
				reservedX[nx]=1;
				reservedY[ny]=1;
				reservedDP[ny+nx]=1; 
				reservedDM[B+ny-nx]=1;
				g[ny][nx] = 1;
				if(dnpSolve(nx+1, ny, g,    deep+1, offset )) {
					return true;
				} else {
					reservedX[nx]=0;
					reservedY[ny]=0;
					reservedDP[ny+nx]=0;
					reservedDM[B+ny-nx]=0;
					g[ny][nx] = 0;
				}
		//	}
		//	}
		}
		return false;
	}

	private boolean accepted(int x, int y, int[][] a) {
		if(x<0 || x>=B || y <0 || y>=B || a[y][x]==1)
			return false;
		for (int i = 0; i < B; i++) {
			for (int j = 0; j < B; j++) {
				if(a[j][i]==1) {
			//		System.out.println("PLACED QUEEN in x="+i+" y="+j);
				}
				if(a[j][i]==1 &&  (y==j || x==i || x-y== i-j || x+y==i+j)) {
				//	System.out.println("NOT ACCEPTED x="+x+" y="+y+" i="+i+" j="+j);
					return false;
				}
			}
		}
		return true;
	}

	private String buildKey() {
		return null;
	}

}
