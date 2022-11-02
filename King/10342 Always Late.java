import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int to, dist;
		
		public Edge(int to, int dist) {
			this.to=to;
			this.dist=dist;
		}
		
		public int compareTo(Edge e) {
			return this.dist-e.dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] edges=new ArrayList [N];
			for (int n=0;n<N;n++) edges[n]=new ArrayList<>();
			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				int dist=Integer.parseInt(st.nextToken());
				edges[n1].add(new Edge(n2,dist));
				edges[n2].add(new Edge(n1,dist));
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Set #");
			sb.append(tc++);
			sb.append('\n');
			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());

				ArrayList<Integer> [] dist=new ArrayList [N];
				for (int n=0;n<N;n++) dist[n]=new ArrayList<>();

				PriorityQueue<Edge> pq=new PriorityQueue<>();
				pq.offer(new Edge(n1,0));
				dist[n1].add(0);
				while (!pq.isEmpty()) {
					Edge e=pq.poll();
					if (dist[e.to].isEmpty()) dist[e.to].add(e.dist);
					else {
						int last=dist[e.to].get(dist[e.to].size()-1);
						if (e.dist!=last) dist[e.to].add(e.dist);
					}
					if (e.to==n2 && dist[e.to].size()==2) break;
					for (int i=0;i<edges[e.to].size();i++) {
						Edge next=edges[e.to].get(i);
						if (dist[next.to].size()>=2) continue;
						pq.offer(new Edge(next.to,e.dist+next.dist));
					}
				}
				sb.append(dist[n2].size()==2?dist[n2].get(1):"?");
				sb.append('\n');
			}
			System.out.print(sb.toString());

			br.readLine();
		}
	}

}
