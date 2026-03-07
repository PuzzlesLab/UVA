import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int n1, n2, cost;
		
		public Edge(int n1, int n2, int c) {
			this.n1=n1;
			this.n2=n2;
			this.cost=c;
		}
		
		public int compareTo(Edge e) {
			return this.cost-e.cost;
		}
	}

	private static int getParent(int [] parent, int n) {
		if (parent[n]==n) return n;
		return parent[n]=getParent(parent,parent[n]);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int E=Integer.parseInt(br.readLine());
			
			int [] parent=new int [N];
			for (int n=0;n<N;n++) parent[n]=n;
			ArrayList<Edge> edges=new ArrayList<>();
			
			PriorityQueue<Edge> q=new PriorityQueue<>();
			for (int e=0;e<E;e++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Edge edge=new Edge(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
				q.offer(edge);
				edges.add(edge);
			}

			int [] state=new int [N];
			boolean flag=true;
			for (int i=0;i<edges.size();i++) {
				Edge edge=edges.get(i);
				if (state[edge.n1]==2 || state[edge.n2]==1) flag=false;
				if (state[edge.n1]==0) state[edge.n1]=1;
				if (state[edge.n2]==0) state[edge.n2]=2;
			}

			if (flag) {
				int ans=0;
				while (!q.isEmpty()) {
					Edge edge=q.poll();
					if (state[edge.n1]==state[edge.n2]) continue;

					int p1=getParent(parent,edge.n1);
					int p2=getParent(parent,edge.n2);
					if (p1!=p2) {
						if (p1>p2) parent[p1]=p2;
						else parent[p2]=p1;
						ans+=edge.cost;
					} else if (edge.cost<0) ans+=edge.cost; // Quick win!
				}
				System.out.println(ans);
			} else System.out.println("Invalid data, Idiot!");
		}
	}

}