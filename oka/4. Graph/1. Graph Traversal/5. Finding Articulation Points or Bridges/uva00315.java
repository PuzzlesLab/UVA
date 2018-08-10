import java.util.*;
import java.io.*;

public class Main {
	public static boolean g[][];
	public static boolean visited[];
	public static int connect() {
		int cc = 0;
		int total = 0;
		for(int i=-1;i<g.length;i++) {
			int count = 0;
			visited = new boolean[g.length];
			if(i>-1)
				visited[i] = true;
			for(int j=0;j<g.length;j++) {
				if(!visited[j]) {
					dfs(j);
					count++;
				}
			}
			if(i==-1) {
				cc = count;
			}else {
				if(count>cc) {
					total++;
				}
			}
		}
		return total;
	}
	
	public static void dfs(int start) {
		visited[start] = true;
		for(int i=0;i<g.length;i++) {
			if(g[start][i]&&!visited[i])
				dfs(i);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while(!(s=br.readLine()).equals("0")) {
			int N = Integer.parseInt(s);
			g = new boolean[N][N];
			while(!(s=br.readLine()).equals("0")) {
				StringTokenizer st = new StringTokenizer(s);
				int u = Integer.parseInt(st.nextToken())-1;
				while(st.hasMoreTokens()) {
					int v = Integer.parseInt(st.nextToken())-1;
					g[u][v] = true;
					g[v][u] = true;
				}
			}
			System.out.println(connect());
		}
	}
}
