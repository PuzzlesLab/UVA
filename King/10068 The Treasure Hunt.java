import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int id;
		int r, c;
		int pickup, carry;

		public Tuple(int r, int c) {
			this.r=r;
			this.c=c;
		}

		public Tuple(int r, int c, int pickup, int carry) {
			this.r=r;
			this.c=c;
			this.pickup=pickup;
			this.carry=carry;
		}
		
		public boolean isSame(int r, int c) {
			return this.r==r && this.c==c;
		}
	}

	private static class Context implements Comparable<Context> {
		int r, c, cost;
		
		public Context(int r, int c, int cost) {
			this.r=r;
			this.c=c;
			this.cost=cost;
		}

		public int compareTo(Context c) {
			return this.cost-c.cost;
		}
	}

	private static class StepSol {
		int cost;
		String path;
		
		public StepSol(int cost, String p) {
			this.cost=cost;
			this.path=p;
		}
		
		public StepSol reverse() {	
			StringBuilder sb=new StringBuilder();
			for (int i=path.length()-1;i>=0;i--) {
				char c=this.path.charAt(i);
				if (c=='S') sb.append('N');
				else if (c=='N') sb.append('S');
				else if (c=='W') sb.append('E');
				else if (c=='E') sb.append('W');
			}
			return new StepSol(this.cost,sb.toString());
		}
	}

	private static final int MAX_STEP=1000000;
	private static int [][] Deltas={{-1,0},{1,0},{0,1},{0,-1}};
	private static char [][] Map;
	private static ArrayList<Tuple> Treasures;
	private static Tuple Start, End;
	private static int BlockC;
	private static StepSol [][] StepsBetween;
	private static StepSol Ans=null;

	private static StepSol getSteps(Tuple from, Tuple to) {
		int [][] minDist=new int[Map.length][Map[0].length];
		for (int i=0;i<minDist.length;i++) Arrays.fill(minDist[i],MAX_STEP);
		Tuple [][] parent=new Tuple [Map.length][Map[0].length];

		minDist[from.r][from.c]=0;
		LinkedList<Context> q=new LinkedList<>();
		q.addLast(new Context(from.r,from.c,0));
		while (!q.isEmpty()) {
			Context c=q.poll();
			if (to.isSame(c.r,c.c)) {
				Tuple curr=new Tuple(c.r,c.c);
				StringBuilder sb=new StringBuilder();
				while (parent[curr.r][curr.c]!=null) {
					Tuple prev=parent[curr.r][curr.c];
					if (prev.r==curr.r) {
						if (prev.c+1==curr.c) sb.append('E');
						else sb.append('W');
					} else {
						if (prev.r+1==curr.r) sb.append('S');
						else sb.append('N');
					}
					curr=prev;
				}
				sb=sb.reverse();
				return new StepSol(c.cost,sb.toString());
			}
			for (int[] delta: Deltas) {
				int nr=c.r+delta[0];
				int nc=c.c+delta[1];
				if (nr>=0 && nr<Map.length && nc>=0 && nc<Map[0].length && Map[nr][nc]!='#') {
					int nCost=c.cost+1;
					if (nCost<minDist[nr][nc]) {
						minDist[nr][nc]=nCost;
						parent[nr][nc]=new Tuple(c.r,c.c);
						q.addLast(new Context(nr,nc,nCost));
					}
				}
			}
		}
		return null;
	}

	private static boolean isAllReachable() {
		boolean [] visited=new boolean [StepsBetween.length];
		visited[0]=true;
		LinkedList<Integer> q=new LinkedList<>();
		q.add(0);
		
		int size=1;
		while (!q.isEmpty() && size<StepsBetween.length) {
			int curr=q.removeFirst();
			for (int n=0;n<StepsBetween.length;n++) {
				if (StepsBetween[curr][n]!=null && !visited[n]) {
					visited[n]=true;
					size++;
					q.addLast(n);
				}
			}
		}
		return size==StepsBetween.length;
	}

	private static void compute(int currSize, Tuple [] combi, boolean [] visited) {
		if (currSize==Treasures.size()) {
			Tuple walk=Start;
			int carryCostPerStep=0;
			int totalCost=0;
			for (int i=0;i<combi.length;i++) {
				Tuple next=combi[i];
				totalCost=totalCost+StepsBetween[walk.id][next.id].cost*(carryCostPerStep+BlockC)+next.pickup;
				carryCostPerStep+=next.carry;
				walk=next;
			}
			totalCost=totalCost+StepsBetween[walk.id][End.id].cost*(carryCostPerStep+BlockC);
			if (Ans==null || totalCost<Ans.cost) {
				Ans=new StepSol(totalCost,"");

				walk=Start;
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<combi.length;i++) {
					sb.append(StepsBetween[walk.id][combi[i].id].path);
					sb.append('P');
					walk=combi[i];
				}
				sb.append(StepsBetween[walk.id][End.id].path);
				Ans.path=sb.toString();
			}
			return;
		}
		for (int i=0;i<Treasures.size();i++) if (!visited[i]) {
			Tuple next=Treasures.get(i);
			if (currSize>0 && StepsBetween[combi[currSize-1].id][next.id]==null) continue; // No path in between.

			visited[i]=true;
			combi[currSize]=next;
			compute(currSize+1,combi,visited);
			visited[i]=false;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());

			Start=null;
			End=null;
			Map=new char [R][C];
			for (int r=0;r<R;r++) {
				Map[r]=br.readLine().toCharArray();
				for (int c=0;c<C;c++) {
					if (Map[r][c]=='S') Start=new Tuple(r,c,0,0);
					if (Map[r][c]=='T') End=new Tuple(r,c,0,0);
				}
			}
			BlockC=Integer.parseInt(br.readLine());

			Treasures=new ArrayList<>();
			st=null;
			for (int r=0;r<R;r++) for (int c=0;c<C;c++) if (Map[r][c]=='*') {
				if (st==null) st=new StringTokenizer(br.readLine());
				Treasures.add(new Tuple(r,c,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}
			
			ArrayList<Tuple> points=new ArrayList<>();
			for (int i=0;i<Treasures.size();i++) {
				Tuple t=Treasures.get(i);
				points.add(t);
				t.id=points.size()-1;
			}
			points.add(Start);
			Start.id=points.size()-1;
			points.add(End);
			End.id=points.size()-1;
			StepsBetween=new StepSol [points.size()][points.size()];
			for (int i=0;i<points.size();i++) {
				StepsBetween[i][i]=new StepSol(0,"");
				for (int i2=i+1;i2<points.size();i2++) {
					StepsBetween[i][i2]=getSteps(points.get(i),points.get(i2));
					if (StepsBetween[i][i2]!=null) {
						StepsBetween[i2][i]=StepsBetween[i][i2].reverse();
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Hunt #");
			sb.append(tc++);
			sb.append('\n');
			if (isAllReachable()) {
				Ans=null;
				compute(0,new Tuple [Treasures.size()], new boolean[Treasures.size()]);
				sb.append("Minimum energy required = ");
				sb.append(Ans.cost);
				sb.append(" cal\n");
				sb.append(Ans.path);
			} else sb.append("The hunt is impossible.");
			sb.append('\n');
			System.out.println(sb);
		}
	}

}
