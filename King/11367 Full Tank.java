import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static class Data implements Comparable<Data> {
		int src, fuelLeft, cost;
		public Data (int s, int f, int c) {
			this.src=s;
			this.fuelLeft=f;
			this.cost=c;
		}
		public int compareTo(Data d) {
			return this.cost-d.cost;
		}
	}
	
	public static class Road {
		int dest, length;
		public Road(int d, int l) {
			this.dest=d;
			this.length=l;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int [] fuel=new int [N];
		st=new StringTokenizer(br.readLine());
		for (int n=0;n<N;n++) fuel[n]=Integer.parseInt(st.nextToken());
		
		ArrayList<Road> [] adjList=new ArrayList [N];
		for (int i=0;i<N;i++) adjList[i]=new ArrayList<>();
		for (int m=0;m<M;m++) {
			st=new StringTokenizer(br.readLine());
			int n1=Integer.parseInt(st.nextToken());
			int n2=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			adjList[n1].add(new Road(n2,d));
			adjList[n2].add(new Road(n1,d));
		}
		
		int Q=Integer.parseInt(br.readLine());
		for (int q=0;q<Q;q++) {
			st=new StringTokenizer(br.readLine());
			int C=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			
			int [][] maxCost=new int [N][C+1];
			for (int i=0;i<maxCost.length;i++) Arrays.fill(maxCost[i], Integer.MAX_VALUE);
			PriorityQueue<Data> queue=new PriorityQueue<>();
			queue.offer(new Data(S,0,0));
			maxCost[S][0]=0;
			while (!queue.isEmpty()) {
				Data d=queue.poll();
				if (d.src==E) break;
				for (Road r : adjList[d.src]) if (d.fuelLeft>=r.length) {
					int newFuel=d.fuelLeft-r.length;
					if (d.cost<maxCost[r.dest][newFuel]) {
						queue.offer(new Data(r.dest,newFuel,d.cost));
						maxCost[r.dest][newFuel]=d.cost;
					}
				}
				if (d.fuelLeft<C) {
					int newFuel=d.fuelLeft+1;
					int newCost=d.cost+fuel[d.src];
					if (newCost<maxCost[d.src][newFuel]) {
						queue.offer(new Data(d.src,newFuel,newCost));
						maxCost[d.src][newFuel]=newCost;
					}
				}
			}
			
			int min=Integer.MAX_VALUE;
			for (int i=0;i<maxCost[0].length;i++) min=Math.min(min, maxCost[E][i]);
			System.out.println(min!=Integer.MAX_VALUE ? min : "impossible");
		}
	}

}