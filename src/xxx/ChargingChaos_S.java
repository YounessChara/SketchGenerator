package xxx;
import java.util.*;
import java.io.*;

public class ChargingChaos_S {

	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {
			int N = in.nextInt();
			int L = in.nextInt();
			String[]  outlets = new String[N] ;
			String[]  devices = new String[N] ; 
			for (int j = 0; j < N; j++) {
				outlets[j] = in.next() ;
			}
			Arrays.sort(outlets);
			for (int j = 0; j < N; j++) {
				devices[j] = in.next() ;
			}
		 //	print(outlets, devices, "initial ");
			String res = handle(outlets, devices, N, L );
			System.out.println("Case #" + i + ": " +   res);
		}
	}

	
	private static void print(String[] outlets, String[] devices, String msg) {
		if(outlets!=null)  {
		System.out.println(msg);
		for (int i = 0; i < outlets.length; i++) {
			System.out.println("o="+outlets[i]);
			System.out.println("d="+devices[i]);
		}
		}
		 
		
	}
 


	 
	private static String handle(String[] outlets, String[] devices, int N, int L) { 
		int minFlip=L+1;
		for(int o=0; o <N; o++) {
			Boolean[] flipSeq = new Boolean[L];
			int flipCount =0;
			boolean ok=true;
		//	System.out.println("check flips for device0="+devices[0]+"  outlets["+o+"]="+outlets[o]);
			for(int i=0; i<L; i++) { 
				flipSeq[i]=devices[0].charAt(i)!=outlets[o].charAt(i); flipCount+=flipSeq[i]? 1:0;
			}
			String[] filpedDevices = new String[N];
			for(int i=0; i<N; i++) { 
				char[] chars = devices[i].toCharArray();
				for(int f=0; f<L; f++)
					 chars[f] = flipChar(chars[f],flipSeq[f] );
				filpedDevices[i]=new String(chars);
			}
			Arrays.sort(filpedDevices);
		//	print(outlets, filpedDevices, "fliped device for "+flipCount);
			for(int i=0; i<N; i++) { 
				if(!filpedDevices[i].equals(outlets[i])) {
				 	//System.out.println("BREAK o="+o+" ("+i+ ") "+filpedDevices[i]+" VS "+outlets[i]);
					ok=false; 
					break;
				}
			}
		//	System.out.println("OK="+ok+" flipCount="+flipCount);
			if(ok)
				minFlip=Math.min(minFlip, flipCount);
		}
		
		return minFlip==L+1? "NOT POSSIBLE": ""+minFlip;
	}


	 
	private static char flipChar(char c, Boolean b) {
		if(b) {
			if(c=='0')
				return '1';
			return '0';
		}
		return c;
	}


	private static int compareColumn(String[] outlets, String[] devices, int s, int n, int l) {
		int oo=0, od=0;
		boolean match=true;

	 	 System.out.println("compareColumn "+s);
		for(int i=0; i<n; i++) {
			char co = outlets[i].charAt(s);
			char cd = devices[i].charAt(s);

		  	 System.out.println("outlet:device["+s+"]="+co+":"+cd);
			if(co=='1') oo++;
			if(cd=='1') od++;
			if(cd!=co) match=false;
				
		}
		int ret = -1;
		if(match)
			ret= 0;
		else if(oo==od)
			ret =  2; 
		else if(oo==n-od)
				ret =  1; 

	   System.out.println("compareColumn "+s+"-->match="+match+" oo="+oo+" od="+od+"->>return: "+ret);
		return ret;
		
	}

	 
}
