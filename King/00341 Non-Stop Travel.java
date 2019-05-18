import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main {

	private static class Edge implements Comparable<Edge> {
		int dest, weight;
		public Edge(int dest, int weight) {this.dest=dest;this.weight=weight;}
		public int compareTo(Edge e) { return this.weight-e.weight; }
	}
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCase=1;
		while (true) {
			int N=sc.nextInt();
			if (N==0) break;
			int [][] adjMat = new int [N][N];
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) adjMat[i][i2]=Integer.MAX_VALUE;
			
			for (int i=0;i<N;i++) {
				int C=sc.nextInt();
				for (int c=0;c<C;c++) adjMat[i][sc.nextInt()-1]=sc.nextInt();
			}
			
			int src=sc.nextInt()-1;
			int dest=sc.nextInt()-1;
			int [] dist=new int [N];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[src]=0;
			
			int [] parent=new int [N];
			for (int n=0;n<N;n++) parent[n]=n;
			
			PriorityQueue<Edge> queue=new PriorityQueue<>();
			queue.offer(new Edge(src,0));
			while (!queue.isEmpty()) {
				Edge e=queue.poll();
				if (e.dest==dest) break;
				for (int i=0;i<N;i++) if (adjMat[e.dest][i]!=Integer.MAX_VALUE) {
					int weight=e.weight+adjMat[e.dest][i];
					if (dist[i]>weight) {
						dist[i]=weight;
						parent[i]=e.dest;
						queue.offer(new Edge(i, weight));
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			int curr=dest;
			do {
				StringBuilder temp=new StringBuilder();
				temp.append(' ');
				temp.append(curr+1);
				temp.append(sb.toString());
				sb=temp;
				curr=parent[curr];
			} while (curr!=parent[curr]);
			
			System.out.printf("Case %d: Path = %d%s; %d second delay\n", testCase++, src+1, sb.toString(), dist[dest]);
		}
	}

}
