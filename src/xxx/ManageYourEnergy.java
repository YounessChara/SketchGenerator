package xxx;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

public class ManageYourEnergy {
	private static int toolCap(int x, int i, int s) {
		if (i < s) {
			if (x < i)
				x = i;
			if (x > s)
				x = s;
		}
		if (s < i) {
			if (x < s)
				x = s;
			if (x > i)
				x = i;
		}
		return x;
	}
	private static boolean enableLog = true;

	public static void log(Object... objects) {
		if (enableLog == false)
			return;
		for (Object o : objects)
			System.out.println(o);
	}

	static int E;
	static int N;
	static int R;
	static int restW;
	static Activity[] activities;
	static TreeSet<Activity> activitiesSet;

	static class Activity implements Comparable<Activity> {
		int rank;
		int val;
		int invest;
		int constraint = -1;
		Activity previous;
		Activity next;

		@Override
		public int compareTo(Activity o) {
			if( o.val != val)
				return o.val - val;
			return rank -o.rank;
		}
		@Override
		public String toString() { 
			return  "act ["+rank+" val="+val+" constraint="+constraint+" ] prev="+(previous!=null?previous.rank : "-")+ " next="+(next!=null?next.rank : "-");
		}
	 

	}

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		PrintWriter pw = new PrintWriter("C:\\Users\\Youness\\Downloads\\" + ManageYourEnergy.class.getSimpleName() + ".out");
		int t = in.nextInt(); // Scanner has functions to read ints, longs,
								// strings, chars, etc.
		for (int i = 1; i <= t; ++i) {

			E = in.nextInt();
			R = in.nextInt();
			N = in.nextInt();
			restW = E / R;
			activities = new Activity[N];
			activitiesSet = new TreeSet<Activity>();
			Activity previous = null;
			
			for (int as = 0; as < N; as++) {
				int val = in.nextInt();
				Activity a = new Activity();
				a.rank = as;
				a.val = val;
				a.previous = previous;
				if (previous != null)
					previous.next = a;
				activities[as] = a;
				activitiesSet.add(a);
				previous = a;

			}
			System.out.println("activitiesSet.size() = "+activitiesSet.size());
			int res = resolve();
			System.out.println("Case #" + i + ": " + res);
			pw.println("Case #" + i + ": " + res);

		}

		pw.close();

	}

	private static int resolve() {
		for (Activity activity : activitiesSet) {
			System.out.println("Add constraint to "+activity);
			if (activity.constraint >= 0) {
				continue;
			}
			activity.constraint = E;

			int e = E;
			System.out.println("activity.next-->"+activity.next);
			if(activity.next!=null && activity.next.constraint==-1) {
				activity.next.constraint=0;
			}
				
			Activity current=activity;
			while (current.previous != null && current.previous.constraint == -1 && e > 0) {
				if (e < R)
					break;
				current.previous.constraint = e - R;
				System.out.println("add previous constraint to "+current.previous);
				current=current.previous;
				e = e - R;

			}

		}
		int e = E;
		int g = 0;
		for (Activity activity : activities) {
			System.out.println(activity +" e="+e+" g="+g);
			if (activity.next == null) {
				g += e * activity.val;
				e = 0;
			} else {
				int c = activity.next.constraint;
				int inv =toolCap(e-c+R, 0, R);
				if(c==0) {
					  inv =e; 
				}
			 
				System.out.println("inv ="+inv);
				 
					g += inv * activity.val;
					e = e-inv;
				 
			}
			e =toolCap(  e + R,0, E);

		}
		return g;
	}



}
