import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static class Edge {
		int src, dest, weight;
		public Edge (int src, int dest, int weight) { this.src=src; this.dest=dest; this.weight=weight; }
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=0;
			try { N=Integer.parseInt(st.nextToken()); } catch (Exception e) { break; }
			int [] busyness=new int [N];
			for (int n=0;n<N;n++) busyness[n]=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> edges=new ArrayList<>();
			boolean [][] adjMat=new boolean [N][N];
			int R=Integer.parseInt(br.readLine());
			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				int src=Integer.parseInt(st.nextToken())-1;
				int dest=Integer.parseInt(st.nextToken())-1;
				int weight=busyness[dest]-busyness[src];
				edges.add(new Edge(src,dest,weight*weight*weight));
				adjMat[src][dest]=true;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Set #");
			sb.append(testCase++);
			sb.append('\n');
			
			int Q=Integer.parseInt(br.readLine());
			if (Q>0) {
				for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) adjMat[i][j] |= adjMat[i][k] && adjMat[k][j];
				
				int [] dist=new int [N];
				Arrays.fill(dist, Integer.MAX_VALUE);
				dist[0]=0;
				for (int n=0;n<N;n++) for (Edge e : edges) if (dist[e.src]!=Integer.MAX_VALUE) dist[e.dest]=Math.min(dist[e.dest], dist[e.src]+e.weight);
				
				for (int q=0;q<Q;q++) {
					int finalDestination=Integer.parseInt(br.readLine())-1;
					
					boolean hasCycle=false;
					for (Edge e : edges) if (dist[e.src]!=Integer.MAX_VALUE) hasCycle|=dist[e.dest]>dist[e.src]+e.weight && adjMat[e.dest][finalDestination];
					
					if (hasCycle || dist[finalDestination]<3 || dist[finalDestination]==Integer.MAX_VALUE) sb.append('?');
					else sb.append(dist[finalDestination]);
					sb.append('\n');
				}
			}
			
			System.out.print(sb.toString());
		}
	}

}
