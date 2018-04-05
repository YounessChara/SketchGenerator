package xxx;
import java.util.*;

public class ZZGraphe {
	static class Edge {
		Node first;
		Node last;
		boolean removed = false;
		TreeMap<String, Integer> marks = new TreeMap<String, Integer>();

		boolean contains(Node n) {
			return n != null && first != null && last != null && (last.ref == n.ref || first.ref == n.ref);
		}

		boolean contains(int i) {
			return first != null && last != null && (last.ref == i || first.ref == i);
		}

		@Override
		public String toString() {
			return " edge ( " + first.ref + "--" + last.ref + " )  removed="+removed; 
		}

		void marks(String s, int v) {
			marks.put(s, v);
		}

		int getMark(String s, int def) {
			Integer i = marks.get(s);
			if (i != null)
				return i;
			return def;
		}

	}

	static class Node {
		int ref;
		int parentRef = -1;
		boolean removed;
		Node parent;
		HashMap<Node, Double> children = new HashMap<Node, Double>();
		HashSet<Node> neigbours = new HashSet<Node>();
		TreeMap<String, Integer> marks = new TreeMap<String, Integer>();
		double val = Double.MAX_VALUE;

		@Override
		public String toString() {
			return " node ( " + ref + " ) removed="+removed;
		}

		void marks(String s, int v) {
			marks.put(s, v);
		}

		int getMark(String s, int def) {
			Integer i = marks.get(s);
			if (i != null)
				return i;
			return def;
		}

	}

	public void print() {
		System.out.println("GRAPH "+nodes.length+" nodes  "+edges.length+" edges");
		for (Edge edge : edges) {
			System.out.println(""+edge);
		}
		
		for (Node n : nodes) {
			System.out.println(""+n);
		}
		
	};
	Node[] nodes;
	Edge[] edges;

	public ZZGraphe(Node[] nodes, Edge[] edges) {
		this.nodes = nodes;
		this.edges = edges;
	}

	Set<Edge> removeNode(int i) {
		Set<Edge>  es=new HashSet<Edge>();
		for (Node n : nodes) {
			if (n.ref == i && !n.removed) {
				n.removed = true;
				for (Edge e : edges) {
					if (!e.removed&& e.contains(i)) {
						e.removed = true;
						es.add(e);
					}
				}
				return es;
			}
		}
		return es;
	}

	public   boolean checkBinnary( ) { 
		HashMap<Integer, Integer> degrees = new HashMap<Integer, Integer>();
		for(Node n:nodes) {
			if(n.removed)
				continue;
			degrees.put(n.ref, 0);
		}
		for (Edge e : edges) {
			if(e.removed)
				continue;
			if (degrees.containsKey(e.first.ref)) {
				degrees.put(e.first.ref, degrees.get(e.first.ref) + 1);
			} else {
				degrees.put(e.first.ref, 1);
			}
			if (degrees.containsKey(e.last.ref)) {
				degrees.put(e.last.ref, degrees.get(e.last.ref) + 1);
			} else {
				degrees.put(e.last.ref, 1);
			}
		}
		
		
		int root = 0;
		int leaves = 0;
		int inners = 0;
		int others = 0; 
		for (Integer i : degrees.keySet()) {
			if (degrees.get(i) == 2) {
				root++; 
			}
			else if (degrees.get(i) == 3)
				inners++;
			else if (degrees.get(i) == 1)
				leaves++;
			else
				others++;
		}
		//System.out.println("checkBinnary root="+root+" leaves="+leaves+" inners="+inners+" others="+others+" "+degrees);
		if( root != 1)
			return false;
		
		return root==1 && others==0 ;
	}

	boolean checkOk() {
		return checkConnex() && checkBinnary( );
	}
	public   boolean checkConnex( ) {
 
		markAll(edges, "checkConnex", 0);
		Edge orgin = edges[0];
		orgin.marks("checkConnex", 1);
		propagateSignal(  orgin);
		for (Edge e : edges) {
			if (!e.removed && e.getMark("checkConnex", 0) == 0)
				return false;
		}
		return true;
	}

	public   void propagateSignal(  Edge orgin) { 
		Node f = orgin.first;
		Node l = orgin.last;
		for (Edge ex : edges) {
			if (!ex.removed && ex.getMark("checkConnex", 0) == 0 && (ex.contains(f) || ex.contains(l))) {
			//	System.out.println("propagateSignal " + ex.first + " " + ex.last + " mark=" + ex.getMark("checkConnex", 0));
				ex.marks("checkConnex", 1);
				propagateSignal(  ex);

			}
		}

	}

	private static void markAll(Edge[] es, String m, int i) {
		for (Edge e : es) {
			if (!e.removed)
				e.marks(m, i);
		}

	}
	
	int resolve() {
		int nl = nodes.length;
		long subSetNumber = 1 << nl;
		int mr=nl;
		for(int si=0; si <subSetNumber; si++) {
			//rest all removed
			for(Edge e:edges) {
				e.removed=false;
				e.first.removed=false;
				e.last.removed=false;
			}

			int r = 0;
			for(int i = 0; i < nl; i++) { 
				//System.out.println("--"+si+">>"+i+" = "+(si>>i));
				if(((si>>i)&1)==1 ) {
					removeNode(i); 
					r++;
				}
			}

			//System.err.println("resole try "+si+" r="+r+" mr="+mr);
			if( r <mr && checkOk() ) {
				mr=r;
			 //	if(r==1 || r==0)
			//	 	return r;
			}
			
		}
		
		return mr;
		
		
	}
	int resolve ( String prx ) { 

		//System.out.println(prx  );
		if(empty())
			return 0;
		if(checkOk()) {
		//	System.out.println(prx +" ok ");
			return 0;
		}
		int mr = nodes.length;
		for(Node node : nodes) {

			if(node.removed)
				continue;
			Set<Edge> es = removeNode(node.ref);
			int sr = resolve( prx+ " ++ "+node.ref )+1;
			if(sr <mr)
				mr=sr;
			add(node, es);
			
		}
		return mr;
	}

	private boolean empty() {
		for(Edge e:edges) {
			if(!e.removed)
				return false;
		} 
		return true;
	}

	private void add(Node node, Set<Edge> es) {
		node.removed=false;
		if(es!=null) {
			for(Edge e:es)
				e.removed=false;
		}
		
	}
}
