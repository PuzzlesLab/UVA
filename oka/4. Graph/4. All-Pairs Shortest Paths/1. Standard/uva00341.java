// this question separate test case does not have blank line
// else get Runtime Error
import java.util.*;
import java.io.*;

public class Main {
	private static int Inf = 1000000;
	private static int Invalid = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int t = 0;
		while((s=br.readLine())!=null&&!s.isEmpty()) {
			if(s.equals("0")) {
				break;
			}
			int N = Integer.parseInt(s);
			int g[][] = new int[N][N];
			for(int k[]:g) {
				Arrays.fill(k,Inf);
			}
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int T = Integer.parseInt(st.nextToken());
				for(int j=0;j<T;j++) {
					int v = Integer.parseInt(st.nextToken())-1;
					int w = Integer.parseInt(st.nextToken());
					g[i][v] = w;
				}
			}
			int d[][] = new int[N][N];
			int next[][] = new int[N][N];
			
			for(int k[]:d) {
				Arrays.fill(k, Inf);
			}
			
			for(int i=0;i<N;i++) {
				d[i][i] = 0;
			}
			
			for(int k[]:next) {
				Arrays.fill(k,Invalid);
			}
			
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(g[i][j]!=Inf) {
						d[i][j] = g[i][j];
						next[i][j] = j;
					}
				}
			}
			
			for(int m=0;m<N;m++) {
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						if(d[i][j]>d[i][m]+d[m][j]) {
							d[i][j] = d[i][m]+d[m][j];
							next[i][j] = next[i][m];
						}
					}
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int source = Integer.parseInt(st.nextToken())-1;
			int dest = Integer.parseInt(st.nextToken())-1;
			t++;
			System.out.printf("Case %d: Path = %d",t,source+1);
			int u = source;
			int v = dest;
			while(next[u][v]!=v&&next[u][v]!=Invalid) {
				u = next[u][v];
				System.out.printf(" %d",u+1);
			}
			System.out.printf(" %d; %d second delay\n",dest+1,d[source][dest]);
		}
	}

}
