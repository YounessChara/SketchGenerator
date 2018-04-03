package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;  

public class GoodLuck {
	
	
	private static boolean enableLog=true;
	public static void log(Object...objects  ) {
		if(enableLog==false)
			return;
		for(Object o:objects)
			System.out.println(o);
	}
	
	
	static int M  ; 
	static int N;
	static int K; 
	static int R; 
	static TreeMap<Long, TreeMap<Long, Integer> > stats = new TreeMap<Long, TreeMap<Long, Integer>> ();
	static long[] products;
	private static TreeMap<Long, Integer> ncompbines=new TreeMap<Long, Integer>();

	public static void mainx(String[] args) throws Exception {
		System.out.println("---"+1234+"-->"+getPropaCombine(1234L));
		System.out.println("---"+3334L+"-->"+getPropaCombine(3334L));
		System.out.println("---"+5588+"-->"+getPropaCombine(5588L));
		System.out.println("---"+1111+"-->"+getPropaCombine(1111L));
		System.out.println("f(3)="+f(3));
	}
	public static void main(String[] args) throws Exception {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		   PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\"+GoodLuck.class.getSimpleName()+".out");
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) { 
		    	 R= in.nextInt(); 
		         N = in.nextInt();
		         M= in.nextInt();
		         K= in.nextInt();
		         
		         presetting();

			       System.out.println("Case #" + i + ": "   );
			       pw.println("Case #" + i + ": "   );
		       for(int as=0; as <R; as++) { 

			         products = new long[K];
			         for (int j = 0; j < K; j++) {
			        	 products[j]=in.nextLong();
					}

				       String res =   resolve( );

				       System.out.println(res );
				       pw.println(res  );
		       } 
		        
 
		      
		    }
		    
		    pw.close();
		    
	}
	private static void presetting() {
		String combine="";
		int[] s=new int[12];
		int count=0;
		 for(int i1 = 2; i1 <=8; i1++) {
			 for(int i2 = i1; i2 <=8; i2++) {
				 for(int i3 = i2; i3 <=8; i3++) {
					 System.out.println("DBX "+i1+"/"+i2+"/"+i3+" count="+count);
					 for(int i4 = i3; i4 <=8; i4++) {
						 for(int i5 = i4; i5 <=8; i5++) {
							 for(int i6 = i5; i6 <=8; i6++) { 
								 for(int i7 = i6; i7 <=8; i7++) {
									 for(int i8 = i7; i8 <=8; i8++) {
										 for(int i9 = i8; i9 <=8; i9++) {
											 for(int i10 = i9; i10 <=8; i10++) {
												 for(int i11 = i10; i11 <=8; i11++) {
													 for(int i12 = i11; i12 <=8; i12++) {
														 s[0]=(i1);
														 s[1 ]=(i2);
														 s[2 ]=(i3);
														 s[3 ]=(i4);
														 s[4 ]=(i5);
														 s[5 ]=(i6);
														 s[6 ]=(i7);
														 s[ 7]=(i8);
														 s[ 8]=(i9);
														 s[9 ]=(i10);
														 s[10]=(i11);
														 s[ 11]=(i12); 
														  combine=(""+i1)+(""+i2)+(""+i3)+(""+i4)+
																  (""+i5)+(""+i6)+(""+i7)+(""+i8)+
																  (""+i9)+(""+i10)+(""+i11)+(""+i12);
														   generateAllProduct(s, Long.parseLong(combine) );
														   count++;
														  
													 }  
												 }  
											 }  
										 }  
									 }
								 }
							 }
						 }  
					 }  
				 } 
			 }
		 }
		 System.out.println("total combine ="+count);
	}
	private static  void generateAllProduct(int[] s,   long combine) {
		int l=s.length;
		int ns = 1 << s.length;
		TreeMap<Integer, Integer> res=new TreeMap<Integer, Integer>();
		ncompbines.put(combine, ns);
		for(int i=0; i< ns; i++) {
			long p =1;
			for(int j = 0; j < l; j++) { 
				if(((i>>j)&1)==1)
					p=p*s[j];
			}
			if(!stats.containsKey(p)) {
				stats.put(p, new TreeMap<Long, Integer>());
			} 
			TreeMap<Long, Integer> m = stats.get(p);
			if(!m.containsKey(combine)) {
				m.put(combine, 1);
			} else {
				m.put(combine, m.get(combine)+1);
			}
			
			
		}
		 
	}
	private static String resolve() {
		String res ="";
		TreeMap<Long, Integer> combines=new TreeMap<>(); 
		TreeMap<Long , TreeMap<Long , Double>> allCombine =new TreeMap<Long , TreeMap<Long , Double>>();
		 for (int i = 0; i < products.length; i++) {
			 Long p = products[i];
			 TreeMap<Long, Integer> combinesForP = stats.get(p);
			 if(combinesForP==null) {
				 return "ERROR  for "+p;
			 }
			 if(combines.isEmpty()) {
				 combines.putAll(combinesForP);
				 System.out.println("combines created "+combines.size());
			 } else {
				 Iterator<Entry<Long, Integer>> iter = combines.entrySet().iterator();
				 while(iter.hasNext()) { 
					 Entry<Long, Integer> entrey = iter.next();
					 if(!combinesForP.containsKey(entrey.getKey()))
						 iter.remove();
				 }
				 ;
				 System.out.println("combines filtred "+combines.size());
				 
			 }
			 
		}
		 Long selected=null;
		 double max=0;
		 
		 for(Long c:combines.keySet()) {
			 double proba=getPropaCombine(c);
			 for (int i = 0; i < products.length; i++) {
				 Long p = products[i];
				 TreeMap<Long, Integer> combinesForP = stats.get(p);
				 proba=proba *  combinesForP.get(c) /ncompbines.get(c);
				
			 }
			// System.out.println("proba for "+c+"-->"+proba);
			 if(proba>max) {
				 max=proba;
				 selected=c;
			 }
		 }
		 
		return  ""+selected;
	}
	static long f(long n) {return n<=1?1:n*f(n-1);}
	private static double getPropaCombine(Long l) {
		char[] c= (""+l).toCharArray();
		int sm =1;
		char prec='x';
		double piPer=1;
		for (int i = 0; i < c.length; i++) {
			if(c[i]==prec) {
				sm++;
			} else {
				 
				piPer=piPer*f(sm); 
				sm=1;
				prec=c[i];
			}
		}
		piPer=piPer*f(sm); 
	//	System.out.println("getPropaCombine for "+l+" piPer "+piPer+" "+sm);
		return 1.0*f(c.length)/piPer;
	}

	
	 
	 

}
