import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int from, to, length;
		
		public Edge(int from, int to, int length) {
			this.from=from;
			this.to=to;
			this.length=length;
		}

		public int compareTo(Edge edge) {
			return this.length-edge.length;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int P=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] adjList=new ArrayList [P];
			for (int p=0;p<P;p++) adjList[p]=new ArrayList<>();
			for (int t=0;t<T;t++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				int l=Integer.parseInt(st.nextToken());
				adjList[n1].add(new Edge(n1,n2,l));
				adjList[n2].add(new Edge(n2,n1,l));
			}

			ArrayList<Edge> [] bestPathEdges=new ArrayList [P];
			for (int p=0;p<P;p++) bestPathEdges[p]=new ArrayList<>();
			int [] dist=new int [P];
			Arrays.fill(dist,10000000);
			PriorityQueue<Edge> q=new PriorityQueue<>();
			q.offer(new Edge(0,0,0));
			dist[0]=0;
			while (!q.isEmpty()) {
				Edge edge=q.poll();
				if (edge.length>dist[edge.to]) continue;
				for (int i=0;i<adjList[edge.to].size();i++) {
					Edge next=adjList[edge.to].get(i);
					int newLength=edge.length+next.length;
					if (newLength>dist[next.to]) continue;
					if (newLength==dist[next.to]) bestPathEdges[next.to].add(next);
					else {
						bestPathEdges[next.to].clear();
						bestPathEdges[next.to].add(next);
						dist[next.to]=newLength;
						q.offer(new Edge(edge.to,next.to,newLength));
					}
				}
			}

			int sum=0;
			HashSet<Edge> visited=new HashSet<>();
			boolean [] inQ=new boolean [P];
			LinkedList<Integer> q2=new LinkedList<>();
			q2.addLast(P-1);
			inQ[P-1]=true;
			while (!q2.isEmpty()) {
				int pos=q2.removeFirst();
				for (int i=0;i<bestPathEdges[pos].size();i++) {
					Edge edge=bestPathEdges[pos].get(i);
					if (edge==null) continue;
					if (!visited.contains(edge)) {
						visited.add(edge);
						sum+=edge.length;
					}
					if (!inQ[edge.from]) {
						inQ[edge.from]=true;
						q2.addLast(edge.from);
					}
				}
			}
			sum<<=1;
			System.out.println(sum);
		}
	}

}