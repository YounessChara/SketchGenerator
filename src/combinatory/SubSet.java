package combinatory;

public class SubSet {
	static int count;
	public static void subset(int[] w, int s, int e,  int k, String pref) {
 	System.out.println("s="+s+" e="+e+" k="+k+" e-s="+(e-s));
		if(k ==e-s ) {
			print(pref , w, s, e);
			return  ;
		} 
		if(k>e-s ||  e==s)
			return;
		subset (w, s+1, e, k-1, pref+ " "+ w[s]+"," ) ;
		subset (w, s+1, e, k, pref  ) ;
		 
	}

	private static void print(String pref, int[] w, int s, int e) {
		count++;
		System.out.print(pref );
		for(int i =s; i <e; i++) 
			System.out.print( " "+w[i]+", ");
		System.out.println();
		
	}
	
	public static void main(String[] args) {
		int[] array = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9} ;
		subset(array, 0, 10, 0, "");
		System.out.println("count="+count);
	}
}
	 
