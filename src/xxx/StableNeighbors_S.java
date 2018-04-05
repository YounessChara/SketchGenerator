package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

public class StableNeighbors_S {
	public static class Stall {
		char color;
		Stall next ;
		Stall prev;
		public Stall(char c) {
			color=c;
		}
		boolean acceptNeighbor(char c) {
			if(color==c)
				return false;
			if(color=='O' && (c=='R' || c=='Y'))
				return false;
			if(c=='O' && (color=='R' || color=='Y'))
				return false;
			if(color=='G' && (c=='B' || c=='Y'))
				return false;
			if(c=='G' && (color=='B' || color=='Y'))
				return false;
			
			if(color=='V' && (c=='B' || c=='R'))
				return false;
			if(c=='V' && (color=='B' || color=='R'))
				return false;
			return true;
		}
	}
	public static class Stable {
		 ArrayList<Stall> ring  =new ArrayList<>();
		 
		 /**
		 * @param s
		 * @param force
		 * @return
		 */
		/**
		 * @param s
		 * @param force
		 * @return
		 */
		boolean addStall (Stall s, boolean force) {
			 int size=ring.size();
			 if(size  == 0 ) {
				 ring.add(s);
				 return true;
			 }
			 if(size  == 1 ) {
				 ring.add(s);
				 if(ring.get(0).acceptNeighbor(s.color) ) {
					
					 return true;
				 }
				 return false;
			 }
			 
			 for (int i = 0; i < size; i++) {
				 int j=(i+1)%size;
					Stall si = ring.get(i);
					Stall sj = ring.get(j);
				if( si.color!=sj.color && si.acceptNeighbor(s.color) && sj.acceptNeighbor(s.color)) {
						//		System.out.println("OK");
								ring.add(j, s);
								return true;
							}
							
			} 
			 for (int i = 0; i < size; i++) {
				 int j=(i+1)%size;
				Stall si = ring.get(i);
				Stall sj = ring.get(j);
			//	System.out.println("try insert "+s.color+" in "+si.color +" and "+sj.color);
				if(si.acceptNeighbor(s.color) && sj.acceptNeighbor(s.color)) {
			//		System.out.println("OK");
					ring.add(j, s);
					return true;
				}
			}
			 if(force) {
				 ring.add(s) ;
			 } 
			 return false;
		 }
		 boolean check() { 
			 int size=ring.size();
			 for (int i = 0; i < size; i++) {
				 int j=(i+1)%size;
				Stall si = ring.get(i);
				Stall sj = ring.get(j);
				if(!si.acceptNeighbor(sj.color) ) { 
					return false;
				}
			}
			 return true;
		 }
		 
		 @Override
		public String toString() { 
			 String r ="";
			 for (Stall stall : ring) {
				r+=stall.color;
			}
			return r;
		}
		 void optimize() {
				int l= ring.size();
				for (int i = 0; i < l; i++) {
					 
					if(ring.get((i+l-1)%l).color!=ring.get((i+1)%l).color) {
						continue;
					}
					System.out.println("try to optimize "+ring.get(i).color+" at position "+i+" in "+this.toString() );
					Stall r = ring.remove(i);
					int reinserted=-1;
					for (int k = 0; k < l-1; k++) {
						 int j=(k+1)%(l-1);
							Stall sk = ring.get(k);
							Stall sj = ring.get(j);
							if(sk.color!=sj.color && sk.acceptNeighbor(r.color) && sj.acceptNeighbor(r.color)) {
								ring.add(j, r);
								reinserted=j;
								break;
							}
					} 
					if(reinserted> 0)
						i=i;
					else {
						ring.add(r);
						return ;
					}
					
				}
			}
		void resolve() {
			int l= ring.size();
			List<String> done=new ArrayList<String>();
			for (int i = 0; i < l; i++) {
				if(ring.get(i).acceptNeighbor(ring.get((i+1)%l).color)) {
					continue;
				}
				String task ="i-"+this.toString();
				if(done.contains(task))
					return ;
				done.add(task);
				System.out.println("try to fix "+ring.get(i).color+" at position "+i+" in "+this.toString() );
				Stall r = ring.remove(i);
				boolean reinserted=false;
				for (int k = 0; k < l-1; k++) {
					 int j=(k+1)%(l-1);
						Stall sk = ring.get(k);
						Stall sj = ring.get(j);
						if(sk.acceptNeighbor(r.color) && sj.acceptNeighbor(r.color)) {
							ring.add(j, r);
							reinserted=true;
							break;
						}
				} 
				if(reinserted)
					i=-1;
				else {
					ring.add(r);
					return ;
				}
				
			}
		}
	}
	private static boolean enableLog = true;

	public static void log(Object... objects) {
		if (enableLog == false)
			return;
		for (Object o : objects)
			System.out.println(o);
	}
 
	static int N; 
	static int R, O, Y, G, B,  V; 

 

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + StableNeighbors_S.class.getSimpleName() + ".out");
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {

			  
			N = in.nextInt();
			R = in.nextInt();
			O = in.nextInt();
			Y = in.nextInt();
			G = in.nextInt();
			B = in.nextInt();
			V = in.nextInt();
			 
			System.out.println("processinf case "+i+" N="+N+ " R="+R+ " O="+O+" Y="+Y+" G="+G+" B="+G+" V="+V);
			 
			String res = resolve();
			System.out.println("Case #" + i + ": " + res);
			pw.println("Case #" + i + ": " + res);

		}

		pw.close();

	}

	private static String resolve() {
		Stable s =new Stable();
		 
			 while(R>0)  {
				 R--;
				 s.addStall(new Stall('R'), true) ; 
			 }
			 while(G>0)  {
				 G--;
				 s.addStall(new Stall('G'), true) ; 
			 }
			 while(B>0)  {
				 B--; 
				 s.addStall(new Stall('B'), true) ;  
				 
			 }
			 s.optimize();
			 while(O>0)  {
				 O--; 
				 s.addStall(new Stall('O'), true) ;  
				 
			 }
			 while(Y>0)  {
				 Y--; 
				 s.addStall(new Stall('Y'), true) ;  
				 
			 }
			 
			 
			 s.optimize();
			 while(V>0)  {
				 V--; 
				 s.addStall(new Stall('V'), true) ;  
				 
			 }
			 s.resolve();
			 if(s.check())
				 return s.toString();
			 else {
				 System.out.println(""+s);
				 return "IMPOSSIBLE";
			 }
					
			 
			 
			 
 
		 
	}



}
