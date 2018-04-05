package xxx;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TeachingAssistant {
	public static void main(String[] args) {
		   Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
		    for (int i = 1; i <= t; ++i) { 
		      char[] cours  = in.next().toCharArray();
		      int n = cours.length;
		      char[] stack =new char[n];
		      int cursor=-1;
		      int tot = 0;
		      for (int j = 0; j < cours.length; j++) {
		 //   	  System.out.println("deb cursor="+cursor+" j="+j+" tot="+tot);
				if(cursor<0) {					
					stack[++cursor]=cours[j]; continue;
				}
				if(stack[cursor]==cours[j]) {
					tot++;
					cursor--;continue;
				} 
				stack[++cursor]=cours[j];
				
			}
		      
		      System.out.println("Case #" + i + ": " + (tot*10 + (n/2-tot)*5) );
		    }
	}

}
