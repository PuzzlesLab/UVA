import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	public static class Edge {
		public int n1, n2;
		public boolean visited=false;
		
		public Edge (int n1, int n2) {
			this.n1=n1;
			this.n2=n2;
		}
	}
	
	public static int Max=0;
	public static void search (int node, ArrayList<Edge> [] edges, int currLength) {
		Max=Math.max(currLength, Max);
		for (Edge e : edges[node]) if (!e.visited) {
			e.visited=true;
			int nextNode=e.n1 == node? e.n2 : e.n1;
			search(nextNode, edges, currLength+1);
			e.visited=false;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] edges=new ArrayList [N];
			for (int n=0;n<N;n++) edges[n]=new ArrayList<>();

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				Edge e=new Edge(n1, n2);
				edges[n1].add(e);
				edges[n2].add(e);
			}

			Max=0;
			for (int n=0;n<N;n++) search(n, edges, 0);
			System.out.println(Max);
		}
	}

}
