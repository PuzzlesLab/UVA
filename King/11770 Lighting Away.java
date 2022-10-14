import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static Stack<Integer> Order;

	private static void kosaraju(int curr, boolean forward) {
		Visited[curr]=true;
		for (int next: AdjList[curr]) if (!Visited[next]) kosaraju(next, forward);
		if (forward) Order.push(curr);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			AdjList=new ArrayList[N];
			for (int n=0;n<N;n++) AdjList[n]=new ArrayList<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				AdjList[Integer.parseInt(st.nextToken())-1].add(Integer.parseInt(st.nextToken())-1);
			}
			
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
			System.out.printf("Case %d: %d\n", tc, ans);

			br.readLine(); // Empty line
		}
	}

}
