import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Edge {
		int num, den;
		String source, dest;
		
		private static int gcd(int a, int b) {
			return b==0 ? a : gcd(b,a%b);
		}
		
		public Edge(String s, String sd, int n, int d) {
			this.source=s;
			this.dest=sd;
			this.num=n/gcd(n,d);
			this.den=d/gcd(n,d);
		}
	}
	
	public static class Query {
		String left, right;
		
		public Query(String l, String r) {
			this.left=l;
			this.right=r;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		HashMap<String, ArrayList<Edge>> adjList=new HashMap<>();
		while (!(s=br.readLine()).equals(".")) {
			StringTokenizer st=new StringTokenizer(s);
			char c=st.nextToken().charAt(0);
			if (c=='!') {
				int leftV=Integer.parseInt(st.nextToken());
				String left=st.nextToken();
				st.nextToken();
				int rightV=Integer.parseInt(st.nextToken());
				String right=st.nextToken();
				
				if (!adjList.containsKey(left)) adjList.put(left, new ArrayList<>());
				adjList.get(left).add(new Edge(left, right, rightV, leftV));
				
				if (!adjList.containsKey(right)) adjList.put(right, new ArrayList<>());
				adjList.get(right).add(new Edge(right, left, leftV, rightV));
			} else if (c=='?') {
				String left=st.nextToken();
				st.nextToken();
				String right=st.nextToken();
				Query q=new Query(left, right);
				
				LinkedList<Edge> queue=new LinkedList<>();
				queue.add(new Edge(q.left, q.left, 1, 1));
				
				int numAns=0, denAns=0;
				HashSet<String> visited=new HashSet<>();
				while (!queue.isEmpty()) {
					Edge curr=queue.removeFirst();
					if (curr.dest.equals(q.right)) {
						numAns=curr.num;
						denAns=curr.den;
					}
					for (Edge e : adjList.get(curr.dest)) if (!visited.contains(e.dest)) {
						visited.add(e.dest);
						queue.add(new Edge(q.left, e.dest, e.num*curr.num, e.den*curr.den));
					}
				}
				if (numAns!=0 || denAns!=0) System.out.printf("%s %s = %s %s\n", String.valueOf(denAns), q.left, String.valueOf(numAns), q.right);
				else System.out.printf("%s %s = %s %s\n", "?", q.left, "?", q.right);
				
			}
		}
	}

}