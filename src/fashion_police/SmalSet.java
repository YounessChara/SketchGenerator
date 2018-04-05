package fashion_police;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SmalSet {
	static String fileInPathLarge = "c:/YOUNESS/codeJam/fashionPolice/C-large-practice.in";
	static String fileOutPathLarge = "c:/YOUNESS/codeJam/fashionPolice/C-large-practice.out";
	static String fileInPathSamll = "c:/YOUNESS/codeJam/fashionPolice/C-small-practice.in";
	static String fileOutPathSamll = "c:/YOUNESS/codeJam/fashionPolice/C-small-practice.out";
	static String fileInPathTest = "c:/YOUNESS/codeJam/fashionPolice/practice.in";
	static String fileOutPathTest = "c:/YOUNESS/codeJam/fashionPolice/practice.out";
	private static ArrayList<Entry> allCase;

	public static class Entry {

		int j;
		int p;
		int s;
		int k;
		int cn;
		int cnJP;
		int cnPS;
		int cnSJ;

		TreeMap<String, Integer> jp =new TreeMap<String, Integer>();
		TreeMap<String, Integer> ps =new TreeMap<String, Integer>();
		TreeMap<String, Integer> sj =new TreeMap<String, Integer>();
		private int caseNumber;

		public Entry(int caseNumber, int j, int p, int s, int k) {
			this.caseNumber = caseNumber;
			this.p = p;
			this.j = j;
			this.s = s;
			this.k = k;
			cn = p * s * j;
			cnJP = p * j;
			cnPS = p * s;
			cnSJ = s * j;
		}

		@Override
		public String toString() {

			return "" + j + "J " + p + "P " + s + "S k=" + k + " cnJP=" + cnJP + " cnPS=" + cnPS + " cnSJ=" + cnSJ;
		}

		List<String> resolve() {
			ArrayList<String> ret = new ArrayList<String>();
			int pc = 0;
			for (int ij = 1; ij <= j; ij++) {
				for (int ip = 1; ip <= p; ip++) {
					
					for (int is = 1; is <= s; is++) {
						if(limit( ij, ip, jp, "JP"))
							continue;
						if(limit( ip, is, ps, "PS"))
							continue;
						if(limit( is, ij, sj, "SJ"))
							continue;
						String garmentCmbine = ""+ij+" "+ip+ " "+is;
						ret.add(garmentCmbine);
						increment( ij, ip, jp, "JP");
						increment( ip, is, ps, "PS");
						increment( is, ij, sj, "SJ");
						pc++;
					}
				}
			}
			ret.add(0, "Case #" + caseNumber + ": " + pc);
			return ret;
		}

		private boolean limit(int ij, int ip, Map<String, Integer> map, String tag) {
			Integer h  =  map.get(""+ij+"-"+ip);
			if(h==null   ) {
				h=0;
			}
	//		System.out.println(" LIMIT "+caseNumber+" "+tag+" ("+ij+"-"+ip+") h="+h+"/k="+k);
			if(h < k) {
				 return false;
			}
			return true;
		}
		private void increment(int ij, int ip, Map<String, Integer> map, String tag) {
			Integer h  =  map.get(""+ij+"-"+ip);
			if(h==null   ) {
				h=0;
			}
		//	System.out.println(" increment "+caseNumber+" "+tag+" ("+ij+"-"+ip+") h="+h+"/k="+k);
			map.put(""+ij+"-"+ip, h+1);
		}
	}

	public static void readInput(String filePath) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;
		int T = 0;
		allCase = new ArrayList<Entry>();
		int cn = 0;
		while ((line = reader.readLine()) != null) {
			if (line.trim().isEmpty())
				continue;
			line = line.trim();
			if (T == 0) {
				T = Integer.parseInt(line);
				continue;
			}

			String[] tkns = line.split("\\s"); 
			if (tkns.length != 4)
				throw new Exception("NP MISMATCH ERROR");
			cn++;
			allCase.add(new Entry(cn, Integer.parseInt(tkns[0]), Integer.parseInt(tkns[1]), Integer.parseInt(tkns[2]), Integer.parseInt(tkns[3])));

		}
		reader.close();
		System.out.println("Allcase=" + allCase);
	}

	private static String runContest() throws InterruptedException {

		String ret = "";
		String sep = "";
		for (Entry cas : allCase) {
			for (String res : cas.resolve()) {
				ret += sep + res;
				sep = "\n";
			}
		}
		return ret;
	}

	private static void writeResult(String fileOutPath, String ret) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(fileOutPath);
		pw.write(ret);
		pw.flush();
		pw.close();
	}

	public static void main(String[] args) throws Exception {
		readInput(fileInPathLarge);
		String ret = runContest();
		System.out.println("ret =" + ret);
		writeResult(fileOutPathLarge, ret);
	}

}
