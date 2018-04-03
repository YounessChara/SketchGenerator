package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*; 

public class RebelEmpire_S {
	private static boolean enableLog=false;
	public static void log(Object...objects  ) {
		if(enableLog==false)
			return;
		for(Object o:objects)
			System.out.println(o);
	}
	static class Point {
		int x, y, z;
		int vx, vy, vz;
		int i;
		double val = Double.MAX_VALUE;
		 
		@Override
		public String toString() {
			return "  (" + x + ", " + y + ", " + z + ")";
		}
		 
	}
	static ArrayList<Point> astroids ;
	static  double[] distances;
	static int N;
	static int S;
	public static void main(String[] args) throws Exception {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		   PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\"+RebelEmpire_S.class.getSimpleName()+".out");
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) { 
		         N = in.nextInt();
		         S = in.nextInt();
				Point[] points =new Point[N];
		       for(int as=0; as <N; as++) {
		    	   Point p =new Point();
		    	   p.x=in.nextInt();
		    	   p.y=in.nextInt();
		    	   p.z=in.nextInt();
		    	   p.vx=in.nextInt();
		    	   p.vy=in.nextInt();
		    	   p.vz=in.nextInt(); 
		    	   p.i=as;
		    	   
		    	   points[as]=p;
		       } 
		        distances = new double[N*N];
		       
		       for(int as=0; as <N; as++) {
		    	   for(int bs=0; bs <=as; bs++) {
		    		   distances[as*N+bs]=distance(points[as], points[bs]);
		    		   distances[bs*N+as]= distances[as*N+bs];
		    	   }
		       } 

		        astroids =new ArrayList<Point>( ); 
		       for(int as=0; as <N; as++) {
		    	   astroids.add(points[as]);
		       }
		       
		       double res =    dijkstra();
		      
		       System.out.println("Case #" + i + ": " + Math.sqrt(res) );
		       pw.println("Case #" + i + ": " + Math.sqrt(res) );
		    }
		    
		    pw.close();
	}
	 
	private static double dijkstra() {
		List<Point> selected =new ArrayList<>();
		Point current =null;
		astroids.get(0).val=0; 
		while(!astroids.isEmpty()) {
			current = selectMin();
			if(current==null) {
				log("select min return null ");
				break;
			}
			selected.add(current);
			astroids.remove(current);
			if(current.i==1) { 
				log("first astroid is reached ");
				break;
			}
			List<Point> neighbours = selectNeibours(current);
			for(Point p:neighbours) {
				double nd = Math.max(distances[p.i*N+current.i],current.val);
				if(nd < p.val)
					p.val=nd;
			}
			
		}
		log("select path ", selected) ;
		if(current!=null) {
			log("dijkstra ends width current point not null") ;
			return current.val;
		}
		return 0;
	}

	private static List<Point> selectNeibours(Point current) {
		 
		return astroids;
	}

	private static Point selectMin() {
		Double d=Double.MAX_VALUE;
		Point min=null;
		for(Point p:astroids) {
			if(d > p.val) {
				min=p; d=p.val;
			}
		}
		return min;
	}

	static double distance(Point p1, Point p2) {
		return (p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y)+(p1.z-p2.z)*(p1.z-p2.z);
	}
 
	 
	 
	private static double resolve(ArrayList<Point> astroids, double threshold, String deb, double[] distances, int N) {
	  	System.out.println(deb+">> resolve "+astroids.size()+" >> " +astroids + "   threshold="+threshold);
		if(astroids.size()<2)
			return 0;
		Point p0 = astroids.get(0);
		Point p1=astroids.get(1);
		double d1=distances[p0.i*N+p1.i];
		if(astroids.size()==2)
			return d1;
	 	 System.out.println(deb+"d1="+d1);
		for(int i=2; i <astroids.size(); i++) {
			Point pi = astroids.get(i);
			double di = distances[p0.i*N+pi.i];
			
		 	//System.out.println(deb+"("+i+")   di="+di+" d1="+d1);
			if(di < d1) { 
				ArrayList<Point> na = new ArrayList<>();
				na.addAll(astroids.subList(2, astroids.size()));
				na.remove(pi);
				na.add(0, p1);
				na.add(0, pi);
				double sdi = resolve(na, di, deb+"\t", distances, N);
			 	 System.out.println(deb+"("+i+") >> sdi="+sdi+" di="+di+" d1="+d1);
				if(sdi < d1)
					d1=Math.max(di, sdi);
				 
			}
			
		}
		//System.out.println(deb+"<<"+d1);
		return d1;
	}

}
