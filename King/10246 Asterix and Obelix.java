import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static void floydWarshall(int C, int [][] cost, int [][] maxFeast) {
		for (int k=0;k<C;k++) for (int i=0;i<C;i++) for (int j=0;j<C;j++) {
			int nMaxFeast=Math.max(maxFeast[i][k],maxFeast[k][j]);
			int nCost=cost[i][k]+cost[k][j];
			if (nCost+nMaxFeast<cost[i][j]+maxFeast[i][j]) {
				cost[i][j]=nCost;
				maxFeast[i][j]=nMaxFeast;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int MAX=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int C=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());

			st=new StringTokenizer(br.readLine());
			int [] feast=new int [C];
			for (int c=0;c<C;c++) feast[c]=Integer.parseInt(st.nextToken());
			
			int [][] cost=new int [C][C];
			int [][] maxFeast=new int [C][C];
			for (int c=0;c<C;c++) {
				Arrays.fill(cost[c],MAX);
				cost[c][c]=0;
				maxFeast[c][c]=feast[c];
			}

			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				int c1=Integer.parseInt(st.nextToken())-1;
				int c2=Integer.parseInt(st.nextToken())-1;
				int d=Integer.parseInt(st.nextToken());
				maxFeast[c1][c2]=maxFeast[c2][c1]=Math.max(feast[c1],feast[c2]);
				cost[c1][c2]=cost[c2][c1]=Math.min(cost[c1][c2],d);
			}

			// Run Floyd-Warshall for extra dimension to reduce maxFeast
			for (int c=0;c<C;c++) floydWarshall(C,cost,maxFeast);

			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Case #");
			sb.append(tc++);
			sb.append('\n');
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken())-1;
				int to=Integer.parseInt(st.nextToken())-1;
				if (cost[from][to]==MAX) sb.append(-1);
				else sb.append(cost[from][to]+maxFeast[from][to]);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}