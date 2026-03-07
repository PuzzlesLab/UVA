import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static class State implements Comparable<State> {
		Tuple t;
		int day, cost;

		public State(Tuple t, int day, int cost) {
			this.t=t;
			this.day=day;
			this.cost=cost;
		}
		
		public int compareTo(State s) {
			return this.cost-s.cost;
		}
	}

	private static final int MAX_COST=10000000;
	private static final int [][] Deltas= {{0,0},{0,-1},{0,1},{-1,0},{1,0}};
	private static int [][] CostMap;
	private static Tuple [] Friends;

	private static int[][][] compute(Tuple start, int day) {
		int [][][] lowestCost=new int [day+1][CostMap.length][CostMap.length];
		for (int i=0;i<lowestCost.length;i++) for (int i2=0;i2<lowestCost[i].length;i2++) Arrays.fill(lowestCost[i][i2],MAX_COST);

		PriorityQueue<State> q=new PriorityQueue<>();
		q.offer(new State(start,0,CostMap[start.x][start.y]));
		lowestCost[0][start.x][start.y]=CostMap[start.x][start.y];
		while (!q.isEmpty()) {
			State s=q.poll();
			if (s.day==day) continue; 
			for (int [] delta: Deltas) {
				int nx=s.t.x+delta[0];
				int ny=s.t.y+delta[1];
				if (nx>=0 && nx<CostMap.length && ny>=0 && ny<CostMap.length) {
					Tuple next=new Tuple(nx,ny);
					int nCost=s.cost+CostMap[nx][ny];
					if (lowestCost[s.day+1][nx][ny]>nCost) {
						lowestCost[s.day+1][nx][ny]=nCost;
						q.offer(new State(next,s.day+1,nCost));
					}
				}
			}
		}

		return lowestCost;
	}
	
	private static int getLowestCost(int [][][] costTable, Tuple dest) {
		int minCost=MAX_COST;
		for (int day=0;day<costTable.length;day++) {
			if (costTable[day][dest.x][dest.y]!=MAX_COST) {
				minCost=Math.min(minCost,costTable[day][dest.x][dest.y]-CostMap[dest.x][dest.y]);
			}
		}
		return minCost;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int F=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());

			CostMap=new int [N][N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) CostMap[n][n2]=Integer.parseInt(st.nextToken());
			}
			
			Friends=new Tuple [F];
			for (int f=0;f<F;f++) {
				st=new StringTokenizer(br.readLine());
				Friends[f]=new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}

			int [][][][] fCosts=new int [F][][][];
			for (int f=0;f<F;f++) fCosts[f]=compute(Friends[f],T);

			int minCost=MAX_COST;
			Tuple sol=null;
			for (int x=0;x<N;x++) for (int y=0;y<N;y++) {
				Tuple dest=new Tuple(x,y);
				int currCost=0;
				for (int f=0;f<F;f++) {
					int fCost=getLowestCost(fCosts[f],dest);
					if (fCost==MAX_COST) {
						currCost=MAX_COST;
						break;
					} else currCost+=fCost;
				}
				if (currCost<minCost) {
					minCost=currCost;
					sol=dest;
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc++);
			sb.append(": ");
			if (sol==null) sb.append("Impossible.");
			else {
				sb.append("Selected city (");
				sb.append(sol.x);
				sb.append(',');
				sb.append(sol.y);
				sb.append(") with minimum cost ");
				sb.append(minCost);
				sb.append('.');
			}
			System.out.println(sb.toString());
		}
	}

}