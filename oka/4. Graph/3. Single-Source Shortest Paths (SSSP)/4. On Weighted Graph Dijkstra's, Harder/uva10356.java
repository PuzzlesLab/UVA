import java.util.*;
import java.io.*;

public class Main {	
	public static int dijkstra(int source,int dest,int g[][]) {
		//second dimension is for two state, either walk or cycle
		int d[][] = new int[g.length][2];		
		boolean visited[][] = new boolean[g.length][2];
		for(int i=0;i<d.length;i++) {
			Arrays.fill(d[i],Integer.MAX_VALUE);
		}
		d[source][0] = 0;
		Queue<Road>q = new PriorityQueue<>();
		q.add(new Road(source,d[source][0],0));
		
		while(!q.isEmpty()) {
			Road r = q.poll();
			int junction = r.junction;
			int dist = r.dist;
			int cycle = r.cycle;
			if(visited[junction][cycle]) {
				continue;
			}
			visited[junction][cycle] = true;
			for(int i=0;i<g.length;i++) {
				if(g[junction][i]!=-1) {
					int nextCycle = (cycle+1)%2; // flip the result
					if(dist+g[junction][i]<d[i][nextCycle]) {
						d[i][nextCycle] = dist+g[junction][i];
						q.add(new Road(i,d[i][nextCycle],nextCycle));
					}
				}
			}
		}
		return d[dest][0];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		int T = 0;
		while((s=br.readLine())!=null&&!s.isEmpty()) {
			StringTokenizer st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int g[][] = new int[n][n];
			for(int i=0;i<n;i++) {
				Arrays.fill(g[i],-1);
			}
			for(int i=0;i<r;i++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				g[u][v] = w;
				g[v][u] = w;
			}
			T++;
			System.out.printf("Set #%d\n",T);
			int ans = dijkstra(0,n-1,g);
			if(ans==Integer.MAX_VALUE)
				System.out.println("?");
			else
				System.out.println(ans);
		}
	}
}

class Road implements Comparable<Road>{
	int junction;
	int dist;
	int cycle;
	Road(int j,int d,int c){
		this.junction = j;
		this.dist = d;
		this.cycle = c;
	}
	@Override
	public int compareTo(Road r) {
		if(dist-r.dist!=0)
			return dist-r.dist;
		return junction-r.junction;
	}
}