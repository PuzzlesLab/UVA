import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static int [] LeafPos;
	private static int [] PosLeaf;

	private static boolean dfs(int curr, int dest, int depth) {
		if (curr==dest) {
			LeafPos[curr]=depth;
			PosLeaf[depth]=curr;
			return true;
		}
		for (int next: AdjList[curr]) {
			if (Visited[next]) continue;
			
			Visited[next]=true;
			if (dfs(next,dest,depth+1)) {
				LeafPos[curr]=depth;
				PosLeaf[depth]=curr;
				return true;
			}
			Visited[next]=false;
		}
		return false;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			AdjList=new ArrayList [N+1];
			for (int n=1;n<=N;n++) AdjList[n]=new ArrayList<>();

			for (int n=1;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				AdjList[a].add(b);
				AdjList[b].add(a);
			}
			int L=Integer.parseInt(br.readLine());
			for (int l=0;l<L;l++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());

				Visited=new boolean [N+1];
				LeafPos=new int [N+1];
				PosLeaf=new int [N+1];
				Visited[a]=true;
				dfs(a,b,1);
				int diff=Math.abs(LeafPos[a]-LeafPos[b]);
				int mid=(LeafPos[a]+LeafPos[b])>>1;
				if ((diff&1)==0) {
					System.out.printf("The fleas meet at %d.\n", PosLeaf[mid]);
				} else {
					int min=Math.min(PosLeaf[mid],PosLeaf[mid+1]);
					int max=Math.max(PosLeaf[mid],PosLeaf[mid+1]);
					System.out.printf("The fleas jump forever between %d and %d.\n",min,max);
				}
			}
		}
	}

}
