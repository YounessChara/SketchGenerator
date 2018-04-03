import java.math.BigInteger;
import java.util.Scanner;
 
public class Main {
 
    static final long MOD = 1000000007L;
    static final BigInteger zero = new BigInteger(String.valueOf(0));
    static final BigInteger one = new BigInteger(String.valueOf(1));
    static final BigInteger two = new BigInteger(String.valueOf(2));
    static final BigInteger three = new BigInteger(String.valueOf(3));
    static final BigInteger four = new BigInteger(String.valueOf(4));
 
 
    static long pow(long base, BigInteger exp){
        if (exp.equals(zero)){
            return 1;
        }
 
        long nextBase = (base * base) % MOD;
        if (exp.mod(two).equals(one)){
            return (base * pow(nextBase, exp.divide(two))) % MOD;
        }
 
        return pow(nextBase, exp.divide(two));
    }
 
    static long colorfulGrids(BigInteger N, long C){
        final BigInteger NQ = N.multiply(N);
        final BigInteger NQ5 = NQ.multiply(new BigInteger(String.valueOf(5)));
        long result;
 
        if (N.mod(two).equals(one)){
            result = pow(C, NQ5) + pow(C, NQ5.add(three).divide(four)) * 2 + pow(C, NQ5.add(one).divide(two));
        }
        else{
            result = pow(C, NQ5) + pow(C, NQ5.divide(four)) * 2 + pow(C, NQ5.divide(two));
        }
 
        return ( (result % MOD) * 250000002) % MOD;
    }
 
    public static void mainx(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        
        for (int i = 0; i < T; i++){
            BigInteger N = scanner.nextBigInteger();
            long C = scanner.nextLong();
 
            System.out.println(colorfulGrids(N, C));
        }
 
    }
    
    public static void main(String[] args) {
    	String affectedResouce="CHARA YOUNES (chara@youness)";
		System.out.println((""+affectedResouce).replaceAll(".*\\(", "").replace(")", ""));
	}
}
  