import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		int dist, nodes;

		public Tuple(int dist, int nodes) {
			this.dist=dist;
			this.nodes=nodes;
		}

		public int compareTo(Tuple t) {
			if (this.dist!=t.dist) return this.dist-t.dist;
			return this.nodes-t.nodes;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());
			
			Tuple [][] dist=new Tuple[N][N];
			for (int n=0;n<N;n++) dist[n][n]=new Tuple(0,0);
			int [][] parent=new int[N][N];
			for (int n=0;n<N;n++) Arrays.fill(parent[n],n);

			for (int p=0;p<P;p++) {
				st=new StringTokenizer(br.readLine());
				int ei=st.nextToken().charAt(0)-'A';
				int ej=st.nextToken().charAt(0)-'A';
				int d=Integer.parseInt(st.nextToken());
				dist[ei][ej]=dist[ej][ei]=new Tuple(d,1);
			}

			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
				if (dist[i][k]==null || dist[k][j]==null) continue;
				Tuple testEdge=new Tuple(dist[i][k].dist+dist[k][j].dist,dist[i][k].nodes+dist[k][j].nodes);
				if (dist[i][j]==null || dist[i][j].compareTo(testEdge)>0) {
					dist[i][j]=testEdge;
					parent[i][j]=parent[k][j];
				}
			}

			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int ek=st.nextToken().charAt(0)-'A';
				int em=st.nextToken().charAt(0)-'A';

				Stack<Character> stk=new Stack<>();
				int tempEm=em;
				while (ek!=tempEm) {
					stk.push((char)(parent[ek][tempEm]+'A'));
					tempEm=parent[ek][tempEm];
				}
				
				StringBuilder sb=new StringBuilder();
				while (!stk.isEmpty()) {
					sb.append(stk.pop());
					sb.append(' ');
				}
				sb.append((char)(em+'A'));
				System.out.println(sb.toString());
			}
		}
	}

}
