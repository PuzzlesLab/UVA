import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=100000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int B=Integer.parseInt(st.nextToken());
			int P=Integer.parseInt(st.nextToken());
			
			int [][] cost=new int [P+1][P+1];
			for (int p=0;p<=P;p++) Arrays.fill(cost[p],MAX);

			st=new StringTokenizer(br.readLine());
			int Q=Integer.parseInt(st.nextToken());
			int [] initial=new int [Q];
			for (int q=0;q<Q;q++) {
				initial[q]=Integer.parseInt(st.nextToken());
				cost[0][initial[q]]=0;
			}

			for (int p=1;p<=P;p++) {
				st=new StringTokenizer(br.readLine());
				int N=Integer.parseInt(st.nextToken());
				for (int n=0;n<N;n++) {
					int coin=Integer.parseInt(st.nextToken());
					int p2=Integer.parseInt(st.nextToken());
					cost[p][p2]=Math.min(cost[p][p2],coin);
				}
			}
			
			for (int k=0;k<=P;k++) for (int i=0;i<=P;i++) for (int j=0;j<=P;j++) cost[i][j]=Math.min(cost[i][j],cost[i][k]+cost[k][j]);

			int minCost=MAX;
			for (int q=0;q<Q;q++) minCost=Math.min(minCost, cost[initial[q]][0]);

			int ans=0;
			if (minCost!=MAX && minCost<=B) {
				int temp=0;
				while (true) {
					if (temp+minCost>=B) break;
					temp=temp+(minCost-1);
					ans++;
				}
			}
			System.out.println(ans);
		}
		br.readLine(); // empty
	}

}
