import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static ArrayList<Integer> [] AdjListR;
	private static boolean [] Visited;
	private static Stack<Integer> Order;
	private static int [] CompId;
	private static int CompIdMax;

	private static void kosaraju(int curr, boolean forward) {
		Visited[curr]=true;
		if (forward) {
			for (int next: AdjList[curr]) if (!Visited[next]) kosaraju(next, forward);
			Order.push(curr);
		} else {
			CompId[curr]=CompIdMax;
			for (int next: AdjListR[curr]) if (!Visited[next]) kosaraju(next, forward);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			AdjList=new ArrayList [N];
			AdjListR=new ArrayList [N];
			Visited=new boolean [N];
			for (int n=0;n<N;n++) {
				AdjList[n]=new ArrayList<>();
				AdjListR[n]=new ArrayList<>();
			}

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				AdjList[n1].add(n2);
				AdjListR[n2].add(n1);
			}

			Order=new Stack<>();
			Visited=new boolean [N];
			for (int n=0;n<N;n++) if (!Visited[n]) kosaraju(n, true);

			CompId=new int [N];
			CompIdMax=0;
			Visited=new boolean [N];
			while (!Order.isEmpty()) {
				int n=Order.pop();
				if (!Visited[n]) {
					kosaraju(n,false);
					CompIdMax++;
				}
			}

			// Construct component graph (Group of nodes to another group of nodes)
			int [] size=new int [CompIdMax];
			for (int n=0;n<N;n++) size[CompId[n]]++;
			
			ArrayList<Integer> [] compAdjList=new ArrayList [CompIdMax];
			HashSet<Integer> [] compExistsAdj=new HashSet [CompIdMax];
			for (int i=0;i<compAdjList.length;i++) {
				compAdjList[i]=new ArrayList<>();
				compExistsAdj[i]=new HashSet<>();
			}
			int [] inDeg=new int [CompIdMax];
			for (int from=0;from<N;from++) for (int i=0;i<AdjList[from].size();i++) {
				int to=AdjList[from].get(i);
				int cFrom=CompId[from];
				int cTo=CompId[to];
				if (cFrom!=cTo && !compExistsAdj[cFrom].contains(cTo)) {
					compAdjList[cFrom].add(cTo);
					compExistsAdj[cFrom].add(cTo);
					inDeg[cTo]++;
				}
			}

			int [] dp=new int [CompIdMax];
			for (int i=0;i<CompIdMax;i++) dp[i]=size[i];

			// Topological sort.
			LinkedList<Integer> q=new LinkedList<>();
			for (int i=0;i<CompIdMax;i++) if (inDeg[i]==0) q.addLast(i);
			while (!q.isEmpty()) {
				int comp=q.removeFirst();
				for (int i=0;i<compAdjList[comp].size();i++) {
					int next=compAdjList[comp].get(i);
					dp[next]=Math.max(dp[next],dp[comp]+size[next]);
					inDeg[next]--;
					if (inDeg[next]==0) q.addLast(next);
				}
			}

			int ans=0;
			for (int i=0;i<dp.length;i++) ans=Math.max(ans,dp[i]);
			
			System.out.println(ans);
		}
	}

}