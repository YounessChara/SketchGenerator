package xxx;
import java.util.*;
import java.util.Map.Entry;

import com.sun.java.swing.plaf.motif.resources.motif;

import java.io.*;

public class FashionShow_S {

	public static final int SX = 2;
	public static final int SP = 3;
	public static final int SO = 4;

	static class Matrice {
		int n;
		int[] ss;
		TreeSet<Integer> constraintColumn = new TreeSet<Integer>();
		TreeSet<Integer> constraintRow = new TreeSet<Integer>();
		TreeSet<Integer> constraintDiagSum = new TreeSet<Integer>();
		TreeSet<Integer> constraintDiagDiff = new TreeSet<Integer>();
		ArrayList<String> modif = new ArrayList<String>();
 

		Matrice(int n) {
			this.n = n;
			ss = new int[n * n];
		}

		int get(int r, int c) {
			return ss[(r - 1) * n + (c - 1)];
		}

		void add(int v, int r, int c) {
			 
			if (v == SX || v == SO) {
				
				constraintColumn.add(c);
				constraintRow.add(r);
			} 
			if (v == SP || v == SO) {
				constraintDiagSum.add(r + c);
				constraintDiagDiff.add(r - c);
			}

			ss[(r - 1) * n + (c - 1)] = v;

		}

		void setC(char v, int r, int c) {
			add(w(v), r, c);
		}

		char getC(int r, int c) {
			return rw(ss[(r - 1) * n + (c - 1)]);
		}

		private int w(char v) {
			if (v == 'x')
				return SX;
			if (v == '+')
				return SP;
			if (v == 'o')
				return SO;
			return 0;
		}

		private char rw(int v) {
			if (v == SX)
				return 'x';
			if (v == SP)
				return '+';
			if (v == SO)
				return 'o';
			return '.';
		}

		void print() {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					System.out.print("" + getC(i, j) + " ");
				}
				System.out.println();
			}
		}

		public void handle() {
		 	upgradAll();
		 	 
			placeAllO();
			placeAllX();
			placeAllP();
			/*
			 * System.out.println("constraintColumn ="+constraintColumn.size());
			 * System.out.println("constraintRow ="+constraintRow.size());
			 * System.out.println("constraintDiagSum ="
			 * +constraintDiagSum.size()); System.out.println(
			 * "constraintDiagDiff ="+constraintDiagDiff.size());
			 */
		}

		private void upgradAll() {
			for (int r = 1; r <= n; r++) {
				for (int c = 1; c <= n; c++) {
					if (get(r, c) == SP) {
					//	System.out.println("upgradAll + "+r+", "+c);
						ss[(r - 1) * n + (c - 1)] = SO;
						if(!checkDiag(r, c) || !checkOrth(r, c))
							ss[(r - 1) * n + (c - 1)] =SP;
						else {
							constraintColumn.add(c);
							constraintRow.add(r);
							modif.add("o "+r+" "+c); 
						}
							
					 
					} else if (get(r, c) == SX) {
					//	System.out.println("upgradAll X "+r+", "+c);
						ss[(r - 1) * n + (c - 1)] = SO;
						if(!checkDiag(r, c) || !checkOrth(r, c))
							ss[(r - 1) * n + (c - 1)] =SX;
						else {
							constraintDiagSum.add(r+c);
							constraintDiagDiff.add(r-c);
							modif.add("o "+r+" "+c);
						}
						 
					}
				}
			}

		}

	 

		private boolean checkOrth(int r, int c) { 
			for(int rr=1; rr <=n; rr++) { 
				for(int cc=1; cc <=n; cc++) { 
					if(rr==r &&  cc==c) 
						continue;
					if(rr!=r &&  cc!=c) 
						continue;
					int w=get( rr, cc);
					if(w==SO || w==SX) {
					//	System.out.println("checkOrth FALSE "+r+"/"+c +"(rr="+rr+"/cc="+cc+")");
						return false;
					}
				}
			} 
			return true;
		}

		private boolean checkDiag(int r, int c) {
			int s= r+c;
			for(int rr=1; rr <=n; rr++) {
				int cc = s-rr ;
				if((rr==r &&  cc==c) || cc <1 || cc>n )
					continue;
				
				int w=get( rr, cc); 
				if(w==SO || w==SP) {

					//System.out.println("checkDIAG SUM FALSE "+r+"/"+c +"(rr="+rr+"/cc="+cc+")");
					return false;
				}
			}
			int  d= r-c;
			for(int rr=1; rr <=n; rr++) {
				int cc = rr-d ;
				if((rr==r &&  cc==c) || cc <1 || cc>n )
					continue;
				int w=get( rr, cc);
				if(w==SO || w==SP) {
				//	System.out.println("checkDIAG DIF FALSE "+r+"/"+c +"(rr="+rr+"/cc="+cc+")");
					return false;
				}
			}
			return true;
		}

		private void placeAllO() {
			for (int c = 1; c <= n; c++) {

				if (constraintColumn.contains(c))
					continue;
				for (int r = 1; r <= n; r++) {
					if (constraintRow.contains(r))
						continue;
					if (constraintDiagSum.contains(r + c))
						continue;
					if (constraintDiagDiff.contains(r - c))
						continue;
					add(SO, r, c);

					modif.add("o "+r+" "+c); 
					break;

				}
			}

		}

		private void placeAllX() {
			for (int c = 1; c <= n; c++) {
				if (constraintColumn.contains(c))
					continue;
				for (int r = 1; r <= n; r++) {
					if (constraintRow.contains(r))
						continue;
					if (get(r, c) > 0)
						continue;

					add(SX, r, c);
					modif.add("x "+r+" "+c); 
					break;
				}
			}
		}

		private void placeAllP() {
			for (int c = 1; c <= n; c++) {
				for (int r = 1; r <= n; r++) {
					if (constraintDiagSum.contains(r + c))
						continue;
					if (constraintDiagDiff.contains(r - c))
						continue;
					if (get(r, c) > 0)
						continue;
					add(SP, r, c);
					modif.add("+ "+r+" "+c); 

				}
			}

		}

		public int getEarned() {
			int total = 0;

			for (int c = 1; c <= n; c++) {
				for (int r = 1; r <= n; r++) {
					total += get(r, c) / 2;
				}
			}
			return total;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			int N = in.nextInt();
			int M = in.nextInt();
			Matrice matrice = new Matrice(N);
			for (int j = 1; j <= M; ++j) {
				String s = in.next();
				int r = in.nextInt();
				int c = in.nextInt();
				matrice.setC(s.charAt(0), r, c);
			}
			matrice.handle();
			System.out.println("Case #" + i + ": " + matrice.getEarned() + " " + matrice.modif.size());
			for(String s:matrice.modif)
				System.out.println(s);
			//matrice.print();
		}
		in.close();
	}

}
