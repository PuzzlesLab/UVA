import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	public static class Edge implements Comparable<Edge> {
		int destx, desty, cost;
		
		public Edge (int x, int y, int cost) {
			this.destx=x;
			this.desty=y;
			this.cost=cost;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.cost-o.cost;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		int [][] delta= {{-1,0},{1,0},{0,-1},{0,1}};
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int M=Integer.parseInt(br.readLine());
			int [][] cost=new int [N][M];
			int [][] lowestCost=new int [N][M];
			for (int i=0;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<M;i2++) {
					cost[i][i2]=Integer.parseInt(st.nextToken());
					lowestCost[i][i2]=Integer.MAX_VALUE;
				}
			}
			
			PriorityQueue<Edge> queue=new PriorityQueue<>();
			queue.offer(new Edge(0, 0, cost[0][0]));
			lowestCost[0][0]=cost[0][0];
			while (!queue.isEmpty()) {
				Edge e=queue.poll();
				
				if (e.destx==N-1 && e.desty==M-1) break;
				
				for (int i=0;i<delta.length;i++) {
					int x=e.destx+delta[i][0];
					int y=e.desty+delta[i][1];
					if (x>=0 && x<N && y>=0 && y<M) {
						int newCost=cost[x][y]+e.cost;
						if (newCost<lowestCost[x][y]) {
							lowestCost[x][y] = newCost;
							queue.offer(new Edge(x,y,newCost));
						}
					}
				}
			}
			
			System.out.println(lowestCost[cost.length-1][cost[0].length-1]);
		}
	}

}