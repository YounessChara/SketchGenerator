package you.cha.cj2017;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;


class Action {
	Solder s;
	Tourrel t; 
	int nextR;
	int nextC; 
	String shot;
	public Action(Solder s, int dr, int dc) { 
		this.s = s;
		this.nextC = s.c+dc;
		this.nextR = s.r+dr;
	}
	public Action(Solder s, Tourrel t ) { 
		this.s = s;
		this.t = t;
		this.shot=""+s.id+" "+t.id; 
	}
	
	@Override
	public String toString() {
		if(shot!=null) 
			return "[SHOT] "+shot;
		return "[MOVE] "+s +" ("+nextR+", "+nextC+")";
	}
	

}
 class Solder  { 
	int id,   bullets , steps  ,c , r;
	boolean locked, done;
	boolean dl, du, dr, dd ;
	TreeSet<Integer> ds =new TreeSet<Integer>(); 
	
	public Solder(int id, int bullets, int steps, int c, int r) {  this.id = id; 	this.bullets = bullets; this.steps = steps; this.c = c; this.r = r; }

	public String toString() {
		return "S:"+id+" ("+r+","+c+") M:"+steps+"|B:"+bullets;
	}	
	@Override
	public boolean equals(Object obj) { 
		if(obj instanceof Solder) {
			return this.toString().equals(obj.toString());
		}
		return super.equals(obj);
	}
	@Override
	public int hashCode() { 
		return toString().hashCode();
	}

	public Solder cloneIt() { 
		return new Solder(id, bullets, steps, c, r);
	}
 }
 class Tourrel  { 
	int id , c, r; 
	boolean on=true;
	public Tourrel(int id,   int c, int r, boolean on) {  	this.id = id;  this.c = c; this.r = r; this.on=on;}
	public String toString() {
		return "T:"+id+" ("+r+","+c+") "+(on?"ON":"OFF");
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Tourrel) {
			return this.toString().equals(obj.toString());
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() { 
		return toString().hashCode();
	}
	public Tourrel cloneIt() { 
		return new Tourrel(id, c, r, on);
	}

} 
public class Turrets {
	int C ; 
	int R ;  
	int M ;
	int[][] g  ;
	List<Solder> solders  ;
	List<Tourrel> tourrels  ;
	int SN=0;
	int TN=0;
	public Turrets(int c, int r, int m, int[][] g, List<Solder> solders, List<Tourrel> tourrels, int si, int ti) {
		this.R=r; this.C=c; this.M=m;  this.g = g; this.solders=solders; this.tourrels=tourrels; this.SN=si; this.TN=ti;
	}
	TreeMap<String, List<String>> history=new TreeMap<String, List<String>> ();

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in))); 

		PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + Turrets.class.getSimpleName() + ".out");
		int T = in.nextInt();  
		for (int i = 1; i <= T; ++i) {
			int C = in.nextInt();
			int R = in.nextInt();
			int M = in.nextInt();
			int B =1;
			int[][] g = new int[R][C];
			List<Solder> solders = new ArrayList<Solder>();
			List<Tourrel> tourrels =new ArrayList<>();
			int si=0;
			int ti=0;
			for(int r=0; r < R; r++) {
				String row = in.next();
				for(int c=0; c<C; c++) {
					char v = row.charAt(c);
					if(v=='#') {
						g[r][c]=1;
					} else if(v=='S') {
						si++;
						solders.add(new Solder(si, B, M, c, r));
					} else if(v=='T') {
						ti++;
						tourrels.add(new Tourrel(ti,   c, r, true));
					} 
				}
			}
			
			Turrets turrets = new Turrets(C, R, M, g, solders, tourrels, si, ti);
			String res = turrets.solve();
			pw.println("Case #"+i+": "+ res.trim());
			pw.flush();
			System.out.println("Case #"+i+": "+ res.trim());
		}
		in.close();
		pw.close();
	}

	private String solve() {
		List<String> shots = dnpSolve( cloneSolders(solders),  cloneTourrels(tourrels), "*** " , 0) ;
		String ret = ""+ shots.size();
		for(String shot:shots)
			ret+="\n"+shot;
		return ret;
	}

	
	
	private List<String> dnpSolve(List<Solder> ss, List<Tourrel> ts, String offset, int deep) {
		String HK=buildKey(ss, ts);
		if(history.containsKey(HK)) {
 		//System.out.println("GOT "+HK+" FROM HSITORY");
			return history.get(HK);
		}
		ArrayList<String> shots=new ArrayList<String>();
	//	System.out.println(offset+ "("+deep+")  dnpSolve "+getConvergenceIndex(ss));
	//	System.out.println(offset+ ss);
	//	initialize() 
		 
		while (true) {
			Action nextAction =increment(  ss, ts, offset);
			if(nextAction==null)
				break; 
			ArrayList<String> subShots =new ArrayList<String>(); 
			if(nextAction.shot!=null )
				subShots.add(nextAction.shot);
			List<Solder> css =  cloneSolders(ss);
			List<Tourrel> cts = cloneTourrels(ts);
			applyAction(nextAction, css, cts);  
			subShots.addAll(dnpSolve(css, cts, offset+"*", deep+1));
			if(subShots.size() > shots.size()) {
				shots=subShots;
			} 
		} 
		
		history.put(HK, shots);
		 
		return shots;
	}

	
	private String buildKey(List<Solder> ss, List<Tourrel> ts) {
		TreeSet<String> ks = new TreeSet<String>();
		TreeSet<String> kt = new TreeSet<String>();
		for(Solder s:ss)
			ks.add(""+s.r+"/"+s.c+"/"+s.steps+"/"+s.bullets);
		for(Tourrel t:ts) {
			if(t.on)
				kt.add(""+t.r+"/"+t.c);
		}
		
		return ks+"#"+kt;
	}

	private String  getConvergenceIndex(List<Solder> ss) {
		int ci =0;
		int cb =0;
		int ct = 0;
		for(Solder s: ss) {
			 
		  ci+=s.steps;
		  cb+=s.bullets;
		  ct+=s.steps+s.bullets;
		  
			
		} 
		return " M="+ci+" B="+cb+ " T="+ct;
	}

	private void applyAction(Action nextAction, List<Solder> css, List<Tourrel> cts) {
		if(nextAction.shot!=null ) {
			for(Tourrel t:cts) {
				if(t.id==nextAction.t.id) {
					t.on=false;
					break;
				}
			}
			for(Solder s:css) {
				if(s.id==nextAction.s.id) {
					s.bullets=s.bullets-1; 
				}
				s.locked = s.steps==0 || isLocked(s, cts);
				
			} 
		} else {
			for(Solder s:css) {
				if(s.id==nextAction.s.id) {
					s.steps=s.steps-1;
					s.c=nextAction.nextC;
					s.r=nextAction.nextR;
					s.locked = s.steps==0 || isLocked(s, cts);
					break;
				}
			} 
		}
		
	}

	private boolean isLocked(Solder s, List<Tourrel> cts) {
		for(Tourrel t:cts) {
			if(t.on && isSight(s, t)) {
				 return true;
			}
		}
		return false;
	}

	private Action increment(List<Solder> ss, List<Tourrel> ts, String offset) {
		for(Solder s:ss) {
			if(s.bullets==0)
				continue;
		//	System.out.println(offset+" increment check moves");
			if(s.steps>0 && !s.locked) {
				if(  !s.du && canMove(s, -1, 0)) {
					s.du = true;
					return new Action(s, -1, 0);
				}
				if(  !s.dl && canMove(s, 0, -1)) {
					s.dl = true;
					return new Action(s,  0, -1);
				}
				if(   !s.dd && canMove(s, 1, 0)) {
					s.dd = true;
					return new Action(s,  1, 0);
				}
				if(   !s.dr && canMove(s, 0,  1)) {
					s.dr = true;
					return new Action(s,  0,  1);
				}
			}
		//	System.out.println(offset+" increment check shoots");
			if(s.bullets>0) {
				for(Tourrel t:ts) {
					if( t.on && !s.ds.contains(t.id) && isSight(s, t)) {
						s.ds.add(t.id);
						return new Action(s, t);
					}
				}
			}
		}
		//System.out.println(offset+" NO ACTION");
		return null;
	}

	private boolean isSight(Solder s, Tourrel t) { 
	//	System.out.println("ISSIGT S:"+s+" VS T:"+t);
		if(s.c!=t.c && s.r!=t.r) {
		//	System.out.println("NOT ALIGNED");
			return false;
		}
		if(s.c==t.c) {
			int r0=Math.min(s.r, t.r);
			int r1=Math.max(s.r, t.r);
			for(int i=r0; i < r1; i++) {
				if(g[i][s.c]==1) {
			//		System.out.println("SCREEND BY "+ i+" "+s.c);
					return false;
				}
			}
			return true;
		}
		if(s.r==t.r) {
			int c0=Math.min(s.c, t.c);
			int c1=Math.max(s.c, t.c);
			for(int i=c0; i < c1; i++) {
				if(g[s.r][i]==1) {
				//	System.out.println("SCREEND BY "+ s.r+" "+i);
					return false;
				}
			}
			return true;
		}
		
		return true;
	}

	private boolean canMove(Solder s, int dr, int dc) {
		int c = s.c+dc;
		int r= s.r+dr;
		if(r < 0  ||  r >=R || c <0 || c >=C || g[s.r+dr][s.c+dc]==1)
			return false; 
		return  true;
	}

	List<Solder> cloneSolders (List<Solder> solders2) {
		List<Solder> out = new ArrayList<Solder>();
		for(Solder o: solders2) {
			out.add(o.cloneIt());
		}
		return out;
	}
	List<Tourrel> cloneTourrels (List<Tourrel> tourrels2) {
		List<Tourrel> out = new ArrayList<Tourrel>();
		for(Tourrel o: tourrels2) {
			out.add(o.cloneIt());
			
		}
		return out;
	}

}
