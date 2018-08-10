import java.util.*;
import java.io.*;

public class Main {
	public static int g[][];
	public static int p[];
	
	public static boolean bfs(int s,int t) {
		Arrays.fill(p,-1);
		boolean visited[] = new boolean[p.length];
		Queue<Integer> q = new LinkedList<>();
		q.add(s);
		visited[s] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0;i<p.length;i++) {
				if(g[cur][i]>0&&!visited[i]) {
					visited[i] = true;
					p[i] = cur;
					q.add(i);
				}
			}
		}
		return visited[t];
	}
	
	public static int EK(int s,int t) {
		int mf = 0;
		
		while(bfs(s,t)) {
			int pathFlow = Integer.MAX_VALUE;
			for(int v=t;v!=s;v=p[v]) {
				pathFlow = Math.min(pathFlow,g[p[v]][v]);
			}
			for(int v=t;v!=s;v=p[v]) {
				g[p[v]][v] -= pathFlow;
				g[v][p[v]] += pathFlow;
			}
			mf += pathFlow;
		}
		return mf;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String a;
		int T = 0;
		while((a=br.readLine())!=null&&!a.equals("0")) {
			int N = Integer.parseInt(a);
			StringTokenizer st = new StringTokenizer(br.readLine());
			g = new int[N][N];
			p = new int[N];
			int s = Integer.parseInt(st.nextToken())-1;
			int t = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			for(int i=0;i<c;i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken())-1;
				int v = Integer.parseInt(st.nextToken())-1;
				int w = Integer.parseInt(st.nextToken());
				g[u][v] += w;
				g[v][u] += w;
			}
			int mf = EK(s,t);
			T++;
			System.out.printf("Network %d\n",T);
			System.out.printf("The bandwidth is %d.\n\n",mf);
		}
	}

}
