import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Edge {
		int idx;
		BigInteger value;
		
		public Edge(int idx, BigInteger value) {
			this.idx=idx;
			this.value=value;
		}
	}

	private static ArrayList<Edge> [] EdgeList;
	private static BigInteger [] DistFromRoot;
	private static boolean [] Visited;
	private static int [] H;
	private static int [] L;
	private static int [] E;
	private static int EIdx;
	private static int [][] SpTable;

	private static void dfs(int curr, int depth) {
		H[curr]=EIdx;
		E[EIdx]=curr;
		L[EIdx++]=depth;

		for (Edge edge: EdgeList[curr]) {
			if (Visited[edge.idx]) continue;

			Visited[edge.idx]=true;
			DistFromRoot[edge.idx]=DistFromRoot[curr].add(edge.value);
			dfs(edge.idx,depth+1);
			E[EIdx]=curr;
			L[EIdx++]=depth;
		}
	}
	
	private static void buildSpTable() {
		int maxPow=(int)Math.ceil(Math.log(EIdx)/Math.log(2));
		SpTable=new int [EIdx][maxPow+1];
		for (int i=0;i<EIdx;i++) SpTable[i][0]=L[i];

		for (int pow=1;pow<=maxPow;pow++) {
			for (int i=0;i+(1<<pow)-1<SpTable.length;i++) {
				SpTable[i][pow]=Math.min(SpTable[i][pow-1],SpTable[i+(1<<(pow-1))][pow-1]);
			}
		}
	}

	private static int lca(int n1, int n2) {
		int min=Math.min(H[n1],H[n2]);
		int max=Math.max(H[n1],H[n2]);
		int maxPow=(int)Math.floor(Math.log(max-min+1)/Math.log(2));

		int l=min;
		int r=max;
		while (l<r && maxPow>=0) {
			if (SpTable[l][maxPow]>SpTable[r-(1<<maxPow)+1][maxPow]) {
				l=r-(1<<maxPow)+1;
			} else {
				r=l+(1<<maxPow);
			}
			maxPow--;
		}
		return SpTable[l][0]<SpTable[r][0] ? E[l] : E[r];
	}

	private static void clear(int N) {
		DistFromRoot=new BigInteger [N];
		DistFromRoot[0]=BigInteger.ZERO;
		Visited=new boolean [N];
		Visited[0]=true;
		H=new int [N];
		E=new int [(N<<1)-1];
		L=new int [(N<<1)-1];
		EIdx=0;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;

			EdgeList=new ArrayList [N];
			for (int n=0;n<N;n++) EdgeList[n]=new ArrayList<>();

			for (int from=1;from<N;from++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int to=Integer.parseInt(st.nextToken());
				BigInteger len=new BigInteger(st.nextToken());
				EdgeList[from].add(new Edge(to,len));
				EdgeList[to].add(new Edge(from,len));
			}

			clear(N);
			dfs(0,0);
			buildSpTable();

			int Q=Integer.parseInt(br.readLine());
			StringBuilder sb=new StringBuilder();
			for (int q=0;q<Q;q++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				int lca=lca(from,to);

				sb.append(DistFromRoot[from].add(DistFromRoot[to]).subtract(DistFromRoot[lca].shiftLeft(1)));
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb);
		}
	}

}