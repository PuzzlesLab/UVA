import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int DP_MAX=10000000;
	private static ArrayList<Integer> [] AdjList;
	private static int [] Src;
	private static int [][] Dp;
	private static boolean [] Visited;
	private static final int MULTI=3000;

	private static int compute(int n, int flag) {
		Visited[n]=true;

		if (Dp[n][flag]==DP_MAX) {
			int ans=flag*MULTI;
			for (int i=0;i<AdjList[n].size();i++) {
				int next=AdjList[n].get(i);
				if (Src[n]==next) continue;
				Src[next]=n;
				if (flag==0) ans+=compute(next,1)+1;
				else ans+=Math.min(compute(next,1),compute(next,0)+1);
			}
			Dp[n][flag]=ans;
		}
		return Dp[n][flag];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	int N=Integer.parseInt(st.nextToken());
        	int M=Integer.parseInt(st.nextToken());
        	
        	AdjList=new ArrayList [N];
        	for (int n=0;n<N;n++) AdjList[n]=new ArrayList<>();
        	
        	int [][] roads=new int [M][2];
        	for (int m=0;m<M;m++) {
        		st=new StringTokenizer(br.readLine());
        		int a=Integer.parseInt(st.nextToken());
        		int b=Integer.parseInt(st.nextToken());
        		AdjList[a].add(b);
        		AdjList[b].add(a);
        		
        		roads[m][0]=a;
        		roads[m][1]=b;
        	}
        	
        	Src=new int [N];
        	Arrays.fill(Src,-1);
        	Dp=new int [N][2];
        	for (int n=0;n<N;n++) Arrays.fill(Dp[n],DP_MAX);
        	Visited=new boolean [N];

        	int min=0;
        	for (int n=0;n<N;n++) if (!Visited[n]) min+=Math.min(compute(n,0),compute(n,1));
        	
        	int ans=min/MULTI;
        	int lamp1=min%MULTI;
            int lamp2=M-lamp1;

        	System.out.printf("%d %d %d\n",ans,lamp2,lamp1);
        	br.readLine();
        	
        }
	}

}