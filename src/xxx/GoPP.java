package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;  

public class GoPP {
	
	
	private static boolean enableLog=true;
	public static void log(Object...objects  ) {
		if(enableLog==false)
			return;
		for(Object o:objects)
			System.out.println(o);
	}
	
	
	static String B  ;
	static String[] G ;
	static int N;
	static int L; 
	public static void main(String[] args) throws Exception {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		   PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\"+GoPP.class.getSimpleName()+".out");
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) { 
		         N = in.nextInt();
		         L= in.nextInt();
		         G = new String[N];
		       for(int as=0; as <N; as++) { 
		    	   G[as]=in.next(); 
		       } 
		       B=in.next();
		        
 
		       String res =   resolve( );
		      
		       System.out.println("Case #" + i + ": " +  res  );
		       pw.println("Case #" + i + ": " +  res  );
		    }
		    
		    pw.close();
		    
	}
	private static String resolve() {
		String p1 ="";
		for(int i=0;i<L-1;i++)p1+="?";
		String p2="";
		String p2Bz="";
		String p2Az="";
		for (int i = 0; i < G.length; i++) {
			char[] chars = G[i].toCharArray();
			char lastChar='x';
			String sp="";
			String spBz="";
			String spAz="";
			boolean zeroInserted=false;
			for (int j = 0; j < chars.length; j++) {
				if(chars[j]==lastChar)
					continue;
				lastChar=chars[j];
				sp=sp+lastChar;
				if(lastChar=='0' && !zeroInserted) {
					sp+="?";
					zeroInserted=true;
					continue;
				}
				if(zeroInserted) {
					spBz+=chars[j];
				} else {
					spAz+=chars[j];
				}
			}
			System.out.println("sp="+sp);
			if(p2.trim().isEmpty())
				p2=sp;
			else if(p2.contains(sp))
				continue;
			else if(p2.contains("0?")) {
				p2=spBz+p2+spAz;
			}
			
			
		}
		if(!p2.contains("0")) {
			System.out.println("p2="+p2);
			return "IMPOSSIBLE";
		}
		return p1 +" "+p2;
	}

	
	 
	 

}
