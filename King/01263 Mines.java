import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Stack;

class Main {

	private static class Mine {
		int x, y, d;
		int minx,maxx,miny,maxy;
		
		public Mine(int x, int y, int d) {
			this.x=x;
			this.y=y;
			this.d=d;
			
			this.minx=x-this.d/2;
			this.maxx=x+this.d/2;
			this.miny=y-this.d/2;
			this.maxy=y+this.d/2;
		}
		
		public boolean inRange(Mine m) {
			return m.minx<=this.x && this.x<=m.maxx && m.miny<=this.y && this.y<=m.maxy;
		}
	}

	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static Stack<Integer> Order;

	private static void kosaraju(int curr, boolean forward) {
		Visited[curr]=true;
		for (int next: AdjList[curr]) if (!Visited[next]) kosaraju(next,forward);
		if (forward) Order.push(curr);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			Mine [] mines=new Mine[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				mines[n]=new Mine(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}

			AdjList=new ArrayList [N];
			for (int n=0;n<N;n++) AdjList[n]=new ArrayList<>();
			for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) if (n!=n2 && mines[n].inRange(mines[n2])) AdjList[n2].add(n);

			Order=new Stack<>();
			Visited=new boolean [N];
			for (int n=0;n<N;n++) if (!Visited[n]) kosaraju(n, true);

			int ans=0;
			Visited=new boolean [N];
			while (!Order.isEmpty()) {
				int n=Order.pop();
				if (!Visited[n]) {
					ans++;
					kosaraju(n,false);
				}
			}
			System.out.println(ans);
		}
	}

}