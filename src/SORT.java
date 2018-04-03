import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SORT {
	public static void main(String[] args) throws Exception {
		List<String> l = new ArrayList<String>();
		l.add("ZAKARIA");
		l.add("XAVIER");
		l.add("MOMO");
		l.add("Ahmed");
		l.add("Fatima");
		l.add("Plan1_1_4.png");
		l.add("c_zone-est-r_1_2.png") ;
		Collections.sort(l);
		System.out.println(l);
	}
}
