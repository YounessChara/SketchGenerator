package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

public class StableNeighbors {
	 
	private static final String IMPOSSIBLE = "IMPOSSIBLE";

	static	boolean acceptNeighbor(char c1, char c2) {
			if(c1==c2)
				return false;
			if(c1=='O' && (c2=='R' || c2=='Y'))
				return false;
			if(c2=='O' && (c1=='R' || c1=='Y'))
				return false;
			if(c1=='G' && (c2=='B' || c2=='Y'))
				return false;
			if(c2=='G' && (c1=='B' || c1=='Y'))
				return false;
			
			if(c1=='V' && (c2=='B' || c2=='R'))
				return false;
			if(c2=='V' && (c1=='B' || c1=='R'))
				return false;
			return true;
		}
	 
	public static class Stable {
		 ArrayList<Character> ring  =new ArrayList<Character>();
		 
		 
		boolean addStall (Character s, boolean force) {
			 int size=ring.size();
			 if(size  == 0 ) {
				 ring.add(s);
				 return true;
			 }
			 if(size  == 1 ) {
				 ring.add(s);
				 if( acceptNeighbor(s, ring.get(0)) ) {
					
					 return true;
				 }
				 return false;
			 }
			 
			 for (int i = 0; i < size; i++) {
				 int j=(i+1)%size;
				 Character si = ring.get(i);
				 Character sj = ring.get(j);
				if( si !=sj  &&  acceptNeighbor(s, si) &&  acceptNeighbor(s, sj)) { 
								ring.add(j, s);
								return true;
							}
							
			} 
			 for (int i = 0; i < size; i++) {
				 int j=(i+1)%size;
				 Character si = ring.get(i);
				 Character sj = ring.get(j); 
				if(acceptNeighbor(s, si) &&  acceptNeighbor(s, sj)) {
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
				 Character si = ring.get(i);
				 Character sj = ring.get(j);
				if(! acceptNeighbor(sj, si) ) { 
					return false;
				}
			}
			 return true;
		 }
		 
		 @Override
		public String toString() { 
			 String r ="";
			 for (Character stall : ring) {
				r+=stall ;
			}
			return r;
		}
		 
		void resolve() {
			int l= ring.size();
			List<String> done=new ArrayList<String>();
			for (int i = 0; i < l; i++) {
				if(acceptNeighbor(ring.get((i+1)%l) , ring.get(i))) {
					continue;
				}
				String task ="i-"+this.toString();
				if(done.contains(task))
					return ;
				done.add(task);
				System.out.println("try to fix "+ring.get(i) +" at position "+i+" in "+this.toString() );
				Character r = ring.remove(i);
				boolean reinserted=false;
				for (int k = 0; k < l-1; k++) {
					 int j=(k+1)%(l-1);
					 Character sk = ring.get(k);
					 Character sj = ring.get(j);
						if(acceptNeighbor(r, sk) &&  acceptNeighbor(r, sj)) {
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
		public void addExt(char c, char d) {
			int insertIndex=0;
			for (int i = 0; i < ring.size(); i++) {
				if(ring.get(i)==c) {
					insertIndex=i;
					break;
				}
			}
			ring.add(insertIndex, d);
			ring.add(insertIndex, c);
			
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
		PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + StableNeighbors.class.getSimpleName() + ".out");
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
		if(G>R || O>B || V>Y)
			return IMPOSSIBLE;
		if(2*B>N || 2*R>N || 2*Y>N)
			return IMPOSSIBLE;
		Stable s =new Stable();
		R=R-G;
		B=B-O;
		Y=Y-V;
		 while(true) {
			 if(R>0)  {
				 R--;
				 s.addStall('R', true) ; 
			 }
			 if(Y>0)  {
				 Y--;
				 s.addStall( 'Y', true) ; 
			 }
			 if(B>0)  {
				 B--; 
				 s.addStall( 'B', true) ;  
				 
			 }
			 if(R ==0 && B==0 && Y==0)
				 break;
		 }
		 s.resolve();
			while (G>0) {
				G--;
				s.addExt('R', 'G');
			}
			while (V>0) {
				V--;
				s.addExt('Y', 'V');
			}
			while (O>0) {
				O--;
				s.addExt('B', 'O');
			}
			 
			
			 if(s.check())
				 return s.toString();
			 else {
				 System.out.println(""+s);
				 return "IMPOSSIBLE";
			 }
					
			 
			 
			 
 
		 
	}



}
