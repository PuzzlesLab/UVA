import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static class Edge implements Comparable<Edge> {
		public int next;
		public double weight;
		public Edge (int next, double weight) { this.next=next; this.weight=weight; }
		public int compareTo (Edge e) {
			if (this.weight>e.weight) return 1;
			else if (this.weight==e.weight) return 0;
			return -1;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] edges=new ArrayList [N];
			for (int n=0;n<N;n++) edges[n]=new ArrayList<>();
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				int w=Integer.parseInt(st.nextToken());
				edges[n1].add(new Edge(n2,w));
				edges[n2].add(new Edge(n1,w));
			}
			
			//Run djikstra to from start to all nodes.
			double [] dist=new double [N];
			Arrays.fill(dist, Integer.MAX_VALUE);
			PriorityQueue<Edge> queue=new PriorityQueue<>();
			dist[0]=0;
			queue.offer(new Edge(0,0));
			while (!queue.isEmpty()) {
				Edge edge=queue.poll();
				for (Edge nextEdge : edges[edge.next]) {
					double nextDist=dist[edge.next]+nextEdge.weight;
					if (nextDist<dist[nextEdge.next]) {
						dist[nextEdge.next]=nextDist;
						queue.offer(new Edge(nextEdge.next, nextDist));
					}
				}
			}
			
			double maxDist=0;
			int maxDomino1=0, maxDomino2=-1;
			for (int i=0;i<dist.length;i++) if (dist[i]>maxDist) {
				maxDist=dist[i];
				maxDomino1=i;
			}
			
			for (int i=0;i<dist.length;i++) for (Edge e : edges[i]) {
				double meetDist=(dist[i]+dist[e.next]+e.weight)/2;
				if (meetDist>maxDist) {
					maxDist=meetDist;
					maxDomino1=i;
					maxDomino2=e.next;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("System #");
			sb.append(testCase++);
			sb.append('\n');
			sb.append("The last domino falls after ");
			sb.append(String.format("%.1f",maxDist));
			if (maxDomino2==-1) {
				sb.append(" seconds, at key domino ");
				sb.append(maxDomino1+1);
			} else {
				sb.append(" seconds, between key dominoes ");
				sb.append(Math.min(maxDomino1,maxDomino2)+1);
				sb.append(" and ");
				sb.append(Math.max(maxDomino1,maxDomino2)+1);
			}
			sb.append(".\n");
			System.out.println(sb.toString());
		}
	}

}