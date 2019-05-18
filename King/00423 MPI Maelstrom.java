import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	public static class Edge implements Comparable<Edge> {
		int dest, weight;
		public Edge(int dest, int weight) { this.dest=dest; this.weight=weight; }
		public int compareTo(Edge e) { return this.weight-e.weight; }
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int [][] adjMat=new int [N][N];
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) adjMat[i][i2]=Integer.MAX_VALUE;
			
			for (int i=1;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<i;i2++) {
					int w=Integer.MAX_VALUE;
					try { w=Integer.parseInt(st.nextToken()); } catch (Exception e) {}
					adjMat[i][i2]=w;
					adjMat[i2][i]=w;
				}
			}
			
			int [] dist=new int [N];
			Arrays.fill(dist,Integer.MAX_VALUE);
			dist[0]=0;
			
			PriorityQueue<Edge> queue=new PriorityQueue<>();
			queue.add(new Edge(0, 0));
			while (!queue.isEmpty()) {
				Edge e=queue.poll();
				for (int i=0;i<N;i++) if (adjMat[e.dest][i]!=Integer.MAX_VALUE) {
					int testWeight=adjMat[e.dest][i]+e.weight;
					if (dist[i]>testWeight) {
						dist[i]=testWeight;
						queue.offer(new Edge(i,testWeight));
					}
				}
			}
			
			int max=0;
			for (int i=0;i<N;i++) max=Math.max(dist[i],max);
			System.out.println(max);
		}
	}

}