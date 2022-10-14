import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static Stack<Integer> Order;
	private static boolean [] Visited;
	
	private static void kosaraju(int start, boolean forward) {
		Visited[start]=true;
		for (int next: AdjList[start]) if (!Visited[next]) kosaraju(next, forward);
		if (forward) Order.push(start);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			AdjList=new ArrayList[N];
			for (int n=0;n<N;n++) AdjList[n]=new ArrayList<Integer>();

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())-1;
				int y=Integer.parseInt(st.nextToken())-1;
				AdjList[x].add(y);
			}

			Order=new Stack<>();
			Visited=new boolean [N];
			for (int n=0;n<N;n++) if (!Visited[n]) kosaraju(n, true);

			int ans=0;
			Visited=new boolean [N];
			while (!Order.isEmpty()) {
				int curr=Order.pop();
				if (!Visited[curr]) {
					ans++;
					kosaraju(curr, false);
				}
			}
			System.out.println(ans);
		}
	}

}