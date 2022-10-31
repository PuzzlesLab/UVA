import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static void floydWarshall(int [][] dist) {
		for (int k=0;k<dist.length;k++) for (int i=0;i<dist.length;i++) for (int j=1;j<dist.length;j++) {
			dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX=100000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			int [][] dist=new int [N+1][N+1];
			for (int n=0;n<dist.length;n++) Arrays.fill(dist[n],MAX);
			for (int n=1;n<=N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken());
				while (st.hasMoreTokens()) dist[from][Integer.parseInt(st.nextToken())]=1;
			}
			floydWarshall(dist);
			
			int [][] dist2=new int [N+1][N+1];
			for (int n=0;n<dist2.length;n++) Arrays.fill(dist2[n],MAX);
			for (int n=1;n<=N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken());
				while (st.hasMoreTokens()) dist2[from][Integer.parseInt(st.nextToken())]=1;
			}
			floydWarshall(dist2);

			StringTokenizer st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			
			boolean match=true;
			for (int n=1;n<=N && match;n++) for (int n2=1;n2<=N && match;n2++) if (n!=n2) match&=A*dist[n][n2]+B>=dist2[n][n2];
			System.out.println(match?"Yes":"No");
		}
	}

}