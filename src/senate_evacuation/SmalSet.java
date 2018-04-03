package senate_evacuation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class SmalSet {
	static String fileInPathLarge = "c:/YOUNESS/codeJam/senateEvacuation/A-large-practice.in";
	static String fileOutPathLarge = "c:/YOUNESS/codeJam/senateEvacuation/A-large-practice.out";
	static String fileInPathSamll = "c:/YOUNESS/codeJam/senateEvacuation/A-small-practice.in";
	static String fileOutPathSamll = "c:/YOUNESS/codeJam/senateEvacuation/A-small-practice.out";
	static String fileInPathTest = "c:/YOUNESS/codeJam/senateEvacuation/practice.in";
	static String fileOutPathTest = "c:/YOUNESS/codeJam/senateEvacuation/practice.out";
	private static ArrayList<ArrayList<Entry>> allCase;

	public static class Entry implements Comparable<Entry> {
		char pc;
		int pn;
		int number;
		int initialNumber;

		public Entry(int p, int n) {
			this.pn = p;
			this.pc = (char) ('A' + p);
			this.number = n;
			this.initialNumber = n;
		}

		@Override
		public int compareTo(Entry o) {
			return o.number - number;
		}

		@Override
		public String toString() {

			return "" +  pc + " :" + number;
		}

	}

	public static void readInput(String filePath) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;
		int T = 0;
		int NP = 0;
		allCase = new ArrayList<ArrayList<Entry>>();
		while ((line = reader.readLine()) != null) {
			if (line.trim().isEmpty())
				continue;
			line = line.trim();
			if (T == 0) {
				T = Integer.parseInt(line);
				continue;
			}
			if (NP == 0) {
				NP = Integer.parseInt(line);
				continue;
			}
			String[] tkns = line.split("\\s");
			ArrayList<Entry> entries = new ArrayList<Entry>();
			if (tkns.length != NP)
				throw new Exception("NP MISMATCH ERROR");
			for (int i = 0; i < tkns.length; i++) {
				String tkn = tkns[i];
				entries.add(new Entry(i, Integer.parseInt(tkn)));
			}
			allCase.add(entries);
			NP = 0;

		}
		reader.close();
		System.out.println("Allcase=" + allCase);
	}

	private static String runContest() throws InterruptedException {
		int ci = 0;
		String ret ="";
		for (ArrayList<Entry> cas : allCase) {
			ci++;
			String res = "Case #"+ci+":" ;
			if(ret.isEmpty()==false)
				ret+="\n";
			System.out.println("case=" + ci); 
			int wi = 0;
			int tot = 0;
			for(Entry e:cas)
				tot+=e.number;

			Collections.sort(cas);
			while (true) {
		//		Thread.sleep(100L);
				wi++;
				System.out.println("wi=" + wi);
				System.out.println("sorted cas="+cas);
				Entry first = cas.get(0);
				System.out.println("first ="+first+" tot="+tot);
				if(first.number==0)
					break;
				String fe = ""+ first.pc ;
				String se ="";
				first.number=first.number-1;
				tot--;
				int si = cas.size()>1?1:0;
				int ti = cas.size()>2?2:si;
				Entry sec = cas.get(si);
				int nextMax =  sec.number <first.number ? first.number-1:
					( sec.number==first.number ?sec.number :sec.number-1);
				if(ti==2)
					nextMax = Math.max(nextMax, cas.get(ti).number);
				System.out.println("si="+si+" sec="+sec+" fist="+first+" risk="+ (tot-1.0)/2.0+" nextMax=" +nextMax);
				
				
				if(sec.number <first.number)  {
					se = ""+ first.pc;
					first.number=first.number-1;
					tot--;
					
					
					
				} else if(sec.number ==first.number &&sec.number ==0){
					System.out.println("sec.number ==first.number==0");
				} else  if((tot-1.0)/2.0 <nextMax) {
					System.out.println("SAFE CONDITITON RISK");
				} else  if(sec.number ==first.number) {
					se = ""+ first.pc;
					first.number=first.number-1;
					tot--;
				} else if(sec.number >0) {
					se = ""+ sec.pc;
					sec.number=sec.number-1;
					tot--;
				} else {
					System.out.println("BIZARRE");
					return "" ;
				}

				Collections.sort(cas);
				res+=" "+fe+""+se;

			}
			ret+=res;
			
		}
return ret;
	}
	private static void writeResult(String fileOutPath , String ret) throws FileNotFoundException { 
		PrintWriter pw =new PrintWriter(fileOutPath);
		pw.write(ret);
		pw.flush();
		pw.close();
	}
	public static void main(String[] args) throws Exception {
		readInput(fileInPathLarge);
		String ret = runContest();
		System.out.println("ret ="+ret);
		writeResult(fileOutPathLarge, ret);
	}

	

}
