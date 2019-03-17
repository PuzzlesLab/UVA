import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static class Edge implements Comparable<Edge>{
		int cell, dist;
		public Edge(int c, int d) {
			this.cell=c;
			this.dist=d;
		}
		
		public int compareTo(Edge e) {
			return this.dist-e.dist;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int N=Integer.parseInt(br.readLine());
			int E=Integer.parseInt(br.readLine())-1;
			int T=Integer.parseInt(br.readLine());
			int M=Integer.parseInt(br.readLine());
			
			ArrayList<Edge> [] edgeList=new ArrayList [N];
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				if (edgeList[a]==null) edgeList[a]=new ArrayList<>();
				edgeList[a].add(new Edge(b, Integer.parseInt(st.nextToken())));
			}
			
			int count=0;
			for (int n=0;n<N;n++) {
				int [] minDist=new int [N];
				Arrays.fill(minDist, Integer.MAX_VALUE);
				PriorityQueue<Edge> queue=new PriorityQueue<>();
				queue.offer(new Edge(n, 0));
				while (!queue.isEmpty()) {
					Edge currEdge=queue.poll();
					if (currEdge.cell==E) {
						count++;
						break;
					}
					if (edgeList[currEdge.cell]!=null) {
						for (Edge nextEdge : edgeList[currEdge.cell]) {
							int totalDist = currEdge.dist+nextEdge.dist;
							if (totalDist<=T && totalDist<minDist[nextEdge.cell]) {
								minDist[nextEdge.cell]=totalDist;
								queue.offer(new Edge(nextEdge.cell, totalDist));
							}
						}
					}
				}
			}
			if (testCase>0) System.out.println();
			System.out.println(count);
		}
	}

}
