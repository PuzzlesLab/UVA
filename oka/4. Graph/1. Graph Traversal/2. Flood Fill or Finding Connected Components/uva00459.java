import java.util.*;
import java.io.*;

public class Main {
	public static void bfs(int start,boolean g[][],boolean visited[]) {
		Queue<Integer>q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(visited[cur])
				continue;
			visited[cur] = true;
			for(int i=0;i<g.length;i++) {
				if(g[cur][i]) {
					q.add(i);
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		br.readLine();
		for(int i=0;i<T;i++) {
			int V = br.readLine().charAt(0)-'A'+1;
			boolean g[][] = new boolean[V][V];
			boolean visited[] = new boolean[V];
			String s;
			while((s=br.readLine())!=null&&!s.isEmpty()) {
				int u = s.charAt(0)-'A';
				int v = s.charAt(1)-'A';
				g[u][v] = true;
				g[v][u] = true;
			}
			int total = 0;
			for(int j=0;j<V;j++) {
				if(!visited[j]) {
					bfs(j,g,visited);
					total++;
				}
			}
			System.out.println(total);
			if(i<T-1)
				System.out.println();
		}
	}
}
