import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int to;
		long cost;
		
		public Edge(int to, long cost) {
			this.to=to;
			this.cost=cost;
		}
		
		public int compareTo(Edge e) {
			return Long.compare(this.cost, e.cost);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int P=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			
			ArrayList<Edge> [] fAdjList=new ArrayList [P];
			for (int p=0;p<P;p++) fAdjList[p]=new ArrayList<>();
			ArrayList<Edge> [] bAdjList=new ArrayList [P];
			for (int p=0;p<P;p++) bAdjList[p]=new ArrayList<>();

			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int p1=Integer.parseInt(st.nextToken())-1;
				int p2=Integer.parseInt(st.nextToken())-1;
				long cost=Long.parseLong(st.nextToken());
				fAdjList[p1].add(new Edge(p2,cost));
				bAdjList[p2].add(new Edge(p1,cost));
			}
			
			long [] fCost=new long [P];
			Arrays.fill(fCost,Long.MAX_VALUE);
			fCost[0]=0;
			PriorityQueue<Edge> fQ=new PriorityQueue<>();
			fQ.offer(new Edge(0,0));
			while (!fQ.isEmpty()) {
				Edge e=fQ.poll();
				if (e.cost>fCost[e.to]) continue;
				for (int i=0;i<fAdjList[e.to].size();i++) {
					Edge ne=fAdjList[e.to].get(i);
					long nc=e.cost+ne.cost;
					if (nc>=fCost[ne.to]) continue;
					fCost[ne.to]=nc;
					fQ.offer(new Edge(ne.to,nc));
				}
			}
			
			long [] bCost=new long [P];
			Arrays.fill(bCost,Long.MAX_VALUE);
			bCost[0]=0;
			PriorityQueue<Edge> bQ=new PriorityQueue<>();
			bQ.offer(new Edge(0,0));
			while (!bQ.isEmpty()) {
				Edge e=bQ.poll();
				if (e.cost>bCost[e.to]) continue;
				for (int i=0;i<bAdjList[e.to].size();i++) {
					Edge ne=bAdjList[e.to].get(i);
					long nc=e.cost+ne.cost;
					if (nc>=bCost[ne.to]) continue;
					bCost[ne.to]=nc;
					bQ.offer(new Edge(ne.to,nc));
				}
			}
			
			long sum=0;
			for (int p=0;p<P;p++) sum+=fCost[p]+bCost[p];
			System.out.println(sum);
		}
	}

}
