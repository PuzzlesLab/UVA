import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static char [] Nodes;
	private static int [] NodeIdx;
	private static ArrayList<Integer> [] Edges;
	private static boolean [][] DpExists;
	private static String [][] Dp;

	private static String find(int curr, int end, int visited, int rem) {
		if (rem==0) return (curr==end) ? String.valueOf(Nodes[end]) : null;
		else {
			if (!DpExists[curr][visited]) {
				String sol=null;
				for (int next: Edges[curr]) if ((visited&(1<<next))==0) {
					String result=find(next,end,visited|(1<<next),rem-1);
					if (result!=null && (sol==null || result.compareTo(sol)<0)) {
						sol=result;
					}
				}

				if (sol!=null) {
					StringBuilder sb=new StringBuilder();
					sb.append(Nodes[curr]);
					sb.append(sol);
					Dp[curr][visited]=sb.toString();
				}
				DpExists[curr][visited]=true;
			}
			return Dp[curr][visited];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			br.readLine();
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			Nodes=new char [N];
			NodeIdx=new int [128];
			for (int n=0;n<N;n++) {
				Nodes[n]=st.nextToken().charAt(0);
				NodeIdx[Nodes[n]]=n;
			}

			Edges=new ArrayList [N];
			for (int i=0;i<Edges.length;i++) Edges[i]=new ArrayList<>();
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=NodeIdx[st.nextToken().charAt(0)];
				int n2=NodeIdx[st.nextToken().charAt(0)];
				
				Edges[n1].add(n2);
				Edges[n2].add(n1);
			}

			Dp=new String [N][(int)Math.pow(2,N)];
			DpExists=new boolean [N][(int)Math.pow(2,N)];
			String solution=find(0,N-1,1,N-1);
			
			System.out.printf("Case %d: %s\n", testCase, solution==null ? "impossible" : solution);
		}
	}

}
