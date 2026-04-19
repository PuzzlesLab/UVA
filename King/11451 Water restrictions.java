import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int L;
	private static int [] Si;
	private static int [] Mi;

	private static int dfs(int currS, int remFlow, int mask) {
		if (currS==Si.length) return Integer.bitCount(mask);

		int ans=dfs(currS+1,remFlow,mask); // Don't give.
		if (remFlow>0) {
			int tempMask=mask|(1<<Si[currS]); // Give at least one.
			for (int currFlow=1;currFlow<=Math.min(remFlow,Mi[currS]);currFlow++) {
				int l=Si[currS]-currFlow;
				int r=Si[currS]+currFlow;
				if (l>=1) tempMask|=1<<l;
				if (r<=L) tempMask|=1<<r;
				ans=Math.max(ans,dfs(currS+1,remFlow-currFlow,tempMask));
			}
		}
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			L=Integer.parseInt(br.readLine());
			int S=Integer.parseInt(br.readLine());
			Si=new int [S];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<S;i++) Si[i]=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(br.readLine());
			Mi=new int [S];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<S;i++) Mi[i]=Integer.parseInt(st.nextToken());

			int ans=dfs(0,C,0);
			System.out.println(ans);
		}
	}

}