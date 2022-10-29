import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Edge implements Comparable<Edge> {
		int from, to, start, end;
		
		public Edge(int from, int to, int start, int end) {
			this.from=from;
			this.to=to;
			this.start=start;
			this.end=end;
		}
		
		public int compareTo(Edge e) {
			if (this.end!=e.end) return this.end-e.end;
			return e.start-this.start;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int C=Integer.parseInt(s);
			ArrayList<Edge> [] fromEdges=new ArrayList [C];
			ArrayList<Edge> [] toEdges=new ArrayList [C];
			for (int c=0;c<C;c++) {
				fromEdges[c]=new ArrayList<>();
				toEdges[c]=new ArrayList<>();
			}

			HashMap<String,Integer> nameToIdx=new HashMap<>();
			for (int c=0;c<C;c++) nameToIdx.put(br.readLine(),c);

			int T=Integer.parseInt(br.readLine());
			for (int t=0;t<T;t++) {
				int R=Integer.parseInt(br.readLine());
				Edge lastMove=null;
				for (int r=0;r<R;r++) {
					StringTokenizer st=new StringTokenizer(br.readLine());
					int endTime=Integer.parseInt(st.nextToken());
					int to=nameToIdx.get(st.nextToken());
					Edge e=new Edge(to,to,endTime,endTime);
					fromEdges[e.to].add(e);
					if (lastMove!=null) {
						e=new Edge(lastMove.to,to,lastMove.end,endTime);
						toEdges[e.from].add(e);
						fromEdges[e.to].add(e);
					}
					lastMove=e;
				}
			}

			int earliestStart=Integer.parseInt(br.readLine());
			int start=nameToIdx.get(br.readLine());
			int end=nameToIdx.get(br.readLine());

			PriorityQueue<Edge> q=new PriorityQueue<>();
			Edge solution=null;
			for (int i=0;i<fromEdges[start].size();i++) {
				Edge e=fromEdges[start].get(i);
				if (e.end>=earliestStart) q.offer(e);
			}
			while (!q.isEmpty()) {
				Edge e=q.poll();
				if (e.to==end) {
					solution=e;
					break;
				}
				for (int i=0;i<toEdges[e.to].size();i++) {
					Edge train=toEdges[e.to].get(i);
					if (e.end>train.start) continue;
					Edge ne=new Edge(e.to,train.to,e.start,train.end);
					q.offer(ne);
				}
			}
			if (solution!=null) System.out.printf("%04d %04d\n",solution.start,solution.end);
			else System.out.println("No connection");
		}
	}

}
