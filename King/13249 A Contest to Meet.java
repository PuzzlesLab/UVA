import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=100000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String in;
		while ((in=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(in);
			int N=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			
			int [][] dist=new int [N][N];
			for (int n=0;n<N;n++) {
				Arrays.fill(dist[n],MAX);
				dist[n][n]=0;
			}
			
			for (int s=0;s<S;s++) {
				st=new StringTokenizer(br.readLine());
				int i=Integer.parseInt(st.nextToken());
				int j=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				dist[i][j]=dist[j][i]=Math.min(dist[i][j],d);
			}
			
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
			
			st=new StringTokenizer(br.readLine());
			double spdA=Integer.parseInt(st.nextToken());
			double spdB=Integer.parseInt(st.nextToken());
			double spdC=Integer.parseInt(st.nextToken());
			double ans=0.0;
			for (int E=0;E<N;E++) for (int other=E+1;other<N;other++) { // Graph is symmetrical, no need to repeat from 0 again.
				ans=Math.max(ans,dist[E][other]/spdA);
				ans=Math.max(ans,dist[E][other]/spdB);
				ans=Math.max(ans,dist[E][other]/spdC);
			}
			System.out.println((int)Math.ceil(ans));
		}
	}

}