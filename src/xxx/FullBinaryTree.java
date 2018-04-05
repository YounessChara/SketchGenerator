package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  

public class FullBinaryTree {
	
	
	private static boolean enableLog=true;
	public static void log(Object...objects  ) {
		if(enableLog==false)
			return;
		for(Object o:objects)
			System.out.println(o);
	}
	
	
	static ZZGraphe.Node[] points  ;
	static ZZGraphe.Edge[] edges ;
	static int N;
	static int S;
	public static void main(String[] args) throws Exception {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		   PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\"+FullBinaryTree.class.getSimpleName()+".out");
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) { 
		         N = in.nextInt();
		         
			  points =new ZZGraphe.Node[N];
			  edges = new ZZGraphe.Edge[N-1];
		       for(int as=0; as <N; as++) { 
		    	   points[as]=new ZZGraphe.Node();
		    	   points[as].ref=as+1;
		       } 
		       for(int as=0; as <N-1; as++) { 
		    	  int x = in.nextInt()-1;
		    	  int y = in.nextInt()-1;
		    	//  System.out.println("x="+x+" y="+y+" N="+N);
		    	  points[y].neigbours.add(points[x]) ;
		    	  points[x].neigbours.add(points[y]) ; 
		    	  ZZGraphe.Edge e =new ZZGraphe.Edge();
		    	  e.first= points[y];
		    	  e.last= points[x];
		    	  edges[as]=e;
		       } 
		       
		       ZZGraphe zzGraphe =new ZZGraphe(points, edges);
		       
		    zzGraphe.print();
 
		       int res =    zzGraphe.resolve( );
		      
		       System.out.println("Case #" + i + ": " +  res  );
		       pw.println("Case #" + i + ": " +  res  );
		    }
		    
		    pw.close();
		    
	}

	
	 
	 

}
