import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Edge {
		int to, dist;
		
		public Edge(int to, int d) {
			this.to=to;
			this.dist=d;
		}
	}

	private static final int NULL_DIST=-1000000;
	private static ArrayList<Edge> [] Edges;
	private static boolean [] InNegCycleDp;
	private static boolean [] Visited;
	private static int [] Dist;


	private static boolean inNegCycle(int curr, int currDist) {
		if (InNegCycleDp[curr]) return true;
		if (Visited[curr]) return Dist[curr]>currDist;
		if (Dist[curr]>currDist) return InNegCycleDp[curr]=true;

		Dist[curr]=currDist;
		Visited[curr]=true;
		boolean flag=false;
		for (int i=0;i<Edges[curr].size();i++) {
			Edge e=Edges[curr].get(i);
			flag|=inNegCycle(e.to,currDist+e.dist);
		}
		Dist[curr]=NULL_DIST;
		Visited[curr]=false;

		InNegCycleDp[curr]=flag;
		return InNegCycleDp[curr];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			br.readLine(); // Blank.
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			Edges=new ArrayList [N];
			for (int n=0;n<N;n++) Edges[n]=new ArrayList<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				Edges[Integer.parseInt(st.nextToken())].add(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}

			Dist=new int [N];
			Arrays.fill(Dist,NULL_DIST);
			InNegCycleDp=new boolean [N];
			Visited=new boolean [N];
			ArrayList<Integer> ans=new ArrayList<>();
			for (int n=0;n<N;n++) {
				Arrays.fill(Visited, false);
				if (inNegCycle(n,0)) ans.add(n);
			}

			if (!ans.isEmpty()) {
				StringBuilder sb=new StringBuilder();
				sb.append("Case ");
				sb.append(tc);
				sb.append(':');
				for (int i=0;i<ans.size();i++) {
					sb.append(' ');
					sb.append(ans.get(i));
				}
				System.out.println(sb);
			} else System.out.printf("Case %d: impossible\n", tc);
		}
	}

}
