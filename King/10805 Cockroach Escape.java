import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] AdjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

                  // Problem description is quite ambigious. The rule is as follow:
			// Cockroach takes 1 time unit from nest to trail, and 1 time unit from trail to nest.
			AdjList=new ArrayList [N+M];
			for (int n=0;n<AdjList.length;n++) AdjList[n]=new ArrayList<>();			

			int [][] dist=new int [AdjList.length][AdjList.length];
			for (int n=0;n<dist.length;n++) {
				Arrays.fill(dist[n],1000000);
				dist[n][n]=0;
			}

			for (int m=N;m<AdjList.length;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());

				AdjList[n1].add(m);
				dist[n1][m]=1;
				AdjList[m].add(n1);
				dist[m][n1]=1;

				AdjList[n2].add(m);
				dist[n2][m]=1;
				AdjList[m].add(n2);
				dist[m][n2]=1;
			}

			for (int k=0;k<dist.length;k++) {
				for (int i=0;i<dist.length;i++) {
					for (int j=0;j<dist.length;j++) {
						dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
					}
				}
			}

			// Check diameter from any place (nest+trail) to any nest.
			int ans=Integer.MAX_VALUE;
			for (int i=0;i<dist.length;i++) {
				int currMax=0;
				for (int j=0;j<N;j++) currMax=Math.max(dist[i][j],currMax);
				ans=Math.min(ans,currMax);
			}

			System.out.printf("Case #%d:\n%d\n\n",testCase,ans);
		}
	}

}
