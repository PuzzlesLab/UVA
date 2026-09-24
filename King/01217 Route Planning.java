import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NULL_ANS=10000000;
	private static int [][] Dist;
	private static int [][] Dp;

	private static int find(ArrayList<Integer> spots, int curr, int mask) {
		if (mask==(1<<spots.size())-1) return (curr==spots.size()-1)?0:NULL_ANS;

		if (Dp[curr][mask]==-1) {
			int ans=NULL_ANS;
			int prev=spots.get(curr);
			for (int i=1;i<spots.size();i++) {
				int next=spots.get(i);
				if ((mask&(1<<i))==0 && Dist[prev][next]!=0) {
					ans=Math.min(ans,Dist[prev][next]+find(spots,i,mask|1<<i));
				}
			}
			Dp[curr][mask]=ans;
		}
		return Dp[curr][mask];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s=br.readLine())!=null) {
        	StringTokenizer st=new StringTokenizer(s);
        	int N=Integer.parseInt(st.nextToken());
        	int R=Integer.parseInt(st.nextToken());

        	Dist=new int [N][N];
        	for (int n=0;n<N;n++) {
        		st=new StringTokenizer(br.readLine());
        		for (int n2=0;n2<N;n2++) Dist[n][n2]=Integer.parseInt(st.nextToken());
        	}

        	for (int r=0;r<R;r++) {
            	ArrayList<Integer> spots=new ArrayList<>();
        		st=new StringTokenizer(br.readLine());
        		while (st.hasMoreTokens()) spots.add(Integer.parseInt(st.nextToken())-1);

        		Dp=new int [spots.size()][1<<spots.size()];
        		for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
        		int ans=find(spots,0,1);
        		if (ans==NULL_ANS) ans=0;
        		System.out.println(ans);
        	}
        	
        }
	}

}