import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	public static class Edge {
		public int next, weight;
		public Edge(int next, int weight) { this.next=next; this.weight=weight; }
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			boolean [][] adjMat=new boolean [20][20];
			
			for (int i=0;i<19;i++) {
				StringTokenizer st=new StringTokenizer(s);
				int X=Integer.parseInt(st.nextToken());
				for (int x=0;x<X;x++) {
					int y=Integer.parseInt(st.nextToken())-1;
					adjMat[i][y]=true;
					adjMat[y][i]=true;
				}
				s=br.readLine();
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Test Set #");
			sb.append(testCase++);
			sb.append('\n');
			int Q=Integer.parseInt(s);
			for (int q=0;q<Q;q++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int src=Integer.parseInt(st.nextToken())-1;
				int dest=Integer.parseInt(st.nextToken())-1;
				
				int [] dist=new int [20];
				Arrays.fill(dist,Integer.MAX_VALUE);
				
				LinkedList<Edge> queue=new LinkedList<>();
				dist[src]=0;
				queue.add(new Edge(src,0));
				while (!queue.isEmpty()) {
					Edge e=queue.poll();
					if (e.next==dest) break;
					for (int i=0;i<20;i++) if (adjMat[e.next][i] && dist[i]>1+dist[e.next]) {
						dist[i]=1+dist[e.next];
						queue.addLast(new Edge(i, dist[i]));
					}
				}

				sb.append(String.format("%2d to %2d: %d\n", src+1, dest+1, dist[dest]));
			}
			System.out.println(sb.toString());
		}
	}

}