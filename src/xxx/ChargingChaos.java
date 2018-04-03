package xxx;
import java.util.*;
import java.io.*;

public class ChargingChaos {

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
		 	print(outlets, devices);
			int res = handle(outlets, devices, N, L );
			System.out.println("Case #" + i + ": " + (res < 0 ? "NOT POSSIBLE" : "" + res));
		}
	}

	
	private static void print(String[] outlets, String[] devices) {
		if(outlets!=null)  {
		System.out.println("outlets:");
		for (int i = 0; i < outlets.length; i++) {
			System.out.println(outlets[i]);
		}
		}
		System.out.println("devices:");
		for (int i = 0; i < devices.length; i++) {
			System.out.println(devices[i]);
		}
		
	}
	private static void print( String[] devices, String mes) {
		 
		System.out.println(mes);
		for (int i = 0; i < devices.length; i++) {
			System.out.println(devices[i]);
		}
		
	}


	 
	private static int handle(String[] outlets, String[] devices, int N, int L) {
		int s=0, tot=0 , ip=0 ;
		String res="";
		for(int i=0; i<L; i++) {
			ip=(i==0)? 0: i-1;
			Arrays.sort(devices);
	 	 	print(  devices, "before "+i+" ::"); 
	 
			int cmp = compareColumn(outlets, devices , i, N, L);
			if(cmp==0)  { // columns matches;
				//sort devices: bubling
			
			} else if(cmp==1) { // flip devices
				tot++; 
				res+="->"+i;
				for(int kk=0; kk <N; kk++) {
					char c = devices[kk].charAt(i);
					char[] chars=devices[kk].toCharArray();
					if(c=='0')
						chars[i] = '1';
					else
						chars[i] = '0';
					
					devices[kk]=new String(chars);
				} 
				Arrays.sort(devices);
		 	 	print(  devices, "fliped "+i+" ::"); 
			} else {
				return -1;
			}
		}
		System.out.println("flips="+res);
		return tot;
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
