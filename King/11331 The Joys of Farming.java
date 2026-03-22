import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<int []> Graphs;
	private static int [][][] Dp;
	
	private static boolean check(int g, int remB, int remC) {
		if (remB==0 && remC==0) return true;
		if (g>=Graphs.size()) return false;
		
		if (Dp[g][remB][remC]==0) {
			boolean flag=false;
			int [] currG=Graphs.get(g);
			if (remB>=currG[0] && remC>=currG[1]) flag|=check(g+1,remB-currG[0],remC-currG[1]);
			if (remB>=currG[1] && remC>=currG[0]) flag|=check(g+1,remB-currG[1],remC-currG[0]);
			Dp[g][remB][remC]=flag?1:2;
		}
		return Dp[g][remB][remC]==1;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int B=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			int T=B+C;
			
			ArrayList<Integer> [] adjList=new ArrayList [T];
			for (int i=0;i<adjList.length;i++) adjList[i]=new ArrayList<>();
			for (int a=0;a<A;a++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				adjList[n1].add(n2);
				adjList[n2].add(n1);
			}
			
			char [] color=new char [T];
			boolean flag=true;
			Graphs=new ArrayList<> ();
			for (int t=0;t<T && flag;t++) if (color[t]==0) {
				LinkedList<Integer> q=new LinkedList<>();
				q.add(t);
				color[t]='b';
				
				int countB=1;
				int countW=0;
				while (!q.isEmpty() && flag) {
					int curr=q.removeFirst();
					for (int i=0;i<adjList[curr].size();i++) {
						int next=adjList[curr].get(i);
						if (color[next]==0) {
							color[next]=color[curr]=='b'?'w':'b';
							if (color[next]=='b') countB++;
							else countW++;
							q.addLast(next);
						} else if (color[curr]==color[next]) {
							flag=false;
							break;
						}
					}
				}

				if (flag) Graphs.add(new int [] {countB,countW});
			}

			if (!flag) {
				System.out.println("no");
				continue;
			}

			Dp=new int [Graphs.size()][B+1][C+1];
			System.out.println(check(0,B,C)?"yes":"no");
		}
	}

}