import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;
	private static boolean [] Visited;
	private static int [] Pair;

	private static int mcbm(int l) {
		if (Visited[l]) return 0;
		
		Visited[l]=true;
		for (int i=0;i<AdjList[l].size();i++) {
			int r=AdjList[l].get(i);
			if (Pair[r]==-1 || mcbm(Pair[r])==1) {
				Pair[r]=l;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=1;tc<=TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	int N=Integer.parseInt(st.nextToken());
        	int [] A=new int [N];
        	for (int n=0;n<N;n++) A[n]=Integer.parseInt(st.nextToken());
        	
        	st=new StringTokenizer(br.readLine());
        	int M=Integer.parseInt(st.nextToken());
        	int [] B=new int [M];
        	for (int m=0;m<M;m++) B[m]=Integer.parseInt(st.nextToken());
        	
        	AdjList=new ArrayList [N];
        	for (int l=0;l<N;l++) {
        		AdjList[l]=new ArrayList<>();
        		for (int r=0;r<M;r++) {
        			if (A[l]==0 && B[r]==0) AdjList[l].add(r);
        			else if (A[l]!=0 && B[r]%A[l]==0) AdjList[l].add(r);
        		}
        	}

        	Pair=new int [M];
        	Arrays.fill(Pair,-1);
        	int ans=0;
        	for (int n=0;n<N;n++) {
        		Visited=new boolean [N];
        		ans+=mcbm(n);
        	}
        	System.out.printf("Case %d: %d\n",tc,ans);
        }
	}

}