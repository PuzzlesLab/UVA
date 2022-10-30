import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int L=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			
			int [][] dist=new int [L+1][L+1];
			for (int l=1;l<=L;l++) {
				Arrays.fill(dist[l],MAX);
				dist[l][l]=0;
			}
			for (int d=0;d<D;d++) {
				st=new StringTokenizer(br.readLine());
				int u=Integer.parseInt(st.nextToken());
				int v=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				dist[u][v]=dist[v][u]=Math.min(dist[u][v],c);
			}
			for (int k=1;k<=L;k++) for (int i=1;i<=L;i++) for (int j=1;j<=L;j++) dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);

			int ans=MAX;
			for (int l=6;l<=L;l++) {
				int refDist=dist[l][1];
				if (refDist==MAX) continue; // Ignore if it is not reachable from building 1
				boolean isCenter=true;
				for (int b=2;b<=5 && isCenter;b++) isCenter&=refDist==dist[l][b]; // Check dist to building 2-5 are same with to building 1
				if (isCenter) {
					int furthest=0;
					for (int to=1;to<=L;to++) furthest=Math.max(furthest,dist[l][to]); // Find furthest distance
					if (furthest!=MAX) ans=Math.min(ans,furthest);
				}
			}
			
			System.out.printf("Map %d: %d\n", tc, ans==MAX?-1:ans);
		}
	}

}
