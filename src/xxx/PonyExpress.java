package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;

public class PonyExpress {
	 
	private static final String IMPOSSIBLE = "IMPOSSIBLE";

	 
	 
	private static boolean enableLog = true;

	public static void log(Object... objects) {
		if (enableLog == false)
			return;
		for (Object o : objects)
			System.out.println(o);
	}
 
	static int N , Q;
	static int[] E;
	static int[] S;
	static int[] D;
	static int[] U;
	static int[] V;  

 

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + PonyExpress.class.getSimpleName() + ".out");
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {

			  
			N = in.nextInt();
			Q = in.nextInt();
			E= new int[N];
			S= new int[N];
			D= new int[N*N];
			U= new int[Q];
			V= new int[Q];
			for (int j = 0; j < N; j++) {
				E[j]=in.nextInt();
				S[j]=in.nextInt();
			}
			for (int j = 0; j < N*N; j++) {
				D[j]=in.nextInt();
			}
			for (int j = 0; j < Q; j++) {
				U[j]=in.nextInt();
				V[j]=in.nextInt();
			}
		  
			String res = resolve();
			System.out.println("Case #" + i + ": " + res);
			pw.println("Case #" + i + ": " + res);

		}

		pw.close();

	}

	private static String resolve() {
		System.out.println("N="+N+" Q="+Q+" ");
		String ret ="";
		String sep="";
		for (int i = 0; i < Q; i++) {
			int u = U[i]-1;
			int v= V[i]-1;
			System.out.println("processing travell from "+u+" to "+v+" using Dijkstra Algowithout parent refering");
			int[] unchecked =new int[N*N];
			double[] costKeep =new double[N*N];
			double[] costChange =new double[N*N];
			double[] rld =new double[N*N];
			int[] ih =new int[N*N];
			double[] rldChange =new double[N*N];
			int[] ihChange =new int[N*N];
			for (int j = 0; j < N*N; j++) {
				unchecked[j] =1;
				costKeep[j] = Double.MAX_VALUE;  
				costChange[j] = Double.MAX_VALUE;  
				rld[j] =  E[j%N];
				ih[j] = j;
				rldChange[j] =  E[j%N];
				ihChange[j] = j;
			}
			unchecked[u] =0;
			costKeep[u] = 0; 
			costChange[u] = 0; 
			int current  = u; 

			double totCost=0;
			while(true) {
				//get all neighbours  
				unchecked[current]=0;
				boolean chooseKeep=true;
				if(costKeep[current] > costChange[current]  ) {
					chooseKeep =false;
				}
				totCost =Math.min( costKeep[current],costChange[current] );
				if(current==v ) {
					break;
				}
				double rId=chooseKeep? rld[current] :  rldChange[current];// remained distance from inherited horse
				int slectedHorseIndex = chooseKeep? ih[current]:ihChange[current] ;
				int is = S[slectedHorseIndex]   ;// speed from inherited horse
				double rLd = E[current];
				int ls = S[current];

				System.out.println("Current Point "+current+" totCost cost "+totCost+" rId="+rId+" is="+is+" rLd="+rLd+" ls="+ls+" chooseKeep="+chooseKeep+" "+slectedHorseIndex); 
				double lowCost = Double.MAX_VALUE;
				int closeNeighbor = -1;
				for (int j = 0; j < N; j++) {
					if(unchecked[j]==0) { 
						continue;
					}
					double dij = D[current*N+j];
					if(dij <=0  || (dij > rLd && dij > rId))
						continue;  
						if(dij <= rLd) {
							double temps = costChange[current]+  1.0*dij/ls;
							System.out.println("1. change horse is ok d"+i+""+j+"= "+dij+" rLd="+rLd+" ls="+ls+"  temps="+temps);
							if(costChange[j]  > temps ) {
								costChange[j] = temps  ; 
								rld[j] =  rLd - dij;
								ih[j]=current;
							}
						}
						if(dij <= rId)  {
							double temps = costKeep[current]+  1.0*dij/is;
							System.out.println("2. keep horse is ok d"+i+""+j+"=  "+dij+" rLd="+rId+" is="+is+" temps="+temps);
							if(costKeep[j]  > temps ) {
								costKeep[j] = temps  ; 
								rldChange[j] =  rId - dij;
								ihChange[j]=chooseKeep?  ih[current] : ihChange[current]; //ih[current];
							}
						}
						
						
					 
					if(costKeep[j] < lowCost ) {
						lowCost =costKeep[j];
						closeNeighbor = j;
					}
					if(costChange[j] < lowCost ) {
						lowCost =costChange[j];
						closeNeighbor = j;
					}
					
				}
				if(closeNeighbor<0) {
					System.out.println("EMPTY QSET");
					break;
				}
				
				current =closeNeighbor;
			}
			ret+=sep+totCost;
			sep=" ";
		}
	 return  ret;
		 
	}



}
