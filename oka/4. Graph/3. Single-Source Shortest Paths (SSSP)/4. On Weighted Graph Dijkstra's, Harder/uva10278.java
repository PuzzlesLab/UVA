import java.util.*;
import java.io.*;

public class Main {	
	public static int dijkstra(int g[][],int d[]) {
		boolean visited[] = new boolean[d.length];
		Queue<Station>q = new PriorityQueue<>();
		for(int i=0;i<d.length;i++) {
			if(d[i]==0) {
				q.add(new Station(i,d[i]));
			}
		}
		while(!q.isEmpty()) {
			Station s = q.poll();
			int cur = s.no;
			if(visited[cur])
				continue;
			visited[cur] = true;
			for(int i=0;i<d.length;i++) {
				if(g[cur][i]!=-1) {
					if(d[cur]+g[cur][i]<d[i]) {
						d[i] = d[cur]+g[cur][i];
					}
					q.add(new Station(i,d[i]));
				}
			}
		}
		int max = 0;
		for(int i=0;i<d.length;i++) {
			//System.out.println("h "+d[i]);
			max = Math.max(max,d[i]);
		}
		return max;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		br.readLine();
		for(int i=0;i<T;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			int g[][] = new int[V][V];
			int d[] = new int[V];

			for(int j=0;j<V;j++) {
				Arrays.fill(g[j],-1);
			}
			
			Arrays.fill(d,Integer.MAX_VALUE);
			
			for(int j=0;j<f;j++) {
				int station = Integer.parseInt(br.readLine())-1;
				d[station] = 0;
			}
			
			String line;
			while((line=br.readLine())!=null&&!line.isEmpty()) {
				st = new StringTokenizer(line);
				int u = Integer.parseInt(st.nextToken())-1;
				int v = Integer.parseInt(st.nextToken())-1;
				int w = Integer.parseInt(st.nextToken());
				g[u][v] = w;
				g[v][u] = w;
			}
			
			int d2[] = new int[V];
			
			int best = 0;
			int max = Integer.MAX_VALUE;
			for(int j=0;j<V;j++) {
				if(d[j]==0)
					continue;
				for(int k=0;k<V;k++) {
					d2[k] = d[k];
				}
				d2[j] = 0;
				int cur = dijkstra(g,d2);
				if(cur<max) {
					max = cur;
					best = j;
				}
			}
			System.out.printf("%d\n",best+1);
			if(i<T-1)
				System.out.println();
		}
	}
}

class Station implements Comparable<Station>{
	int no;
	int d;
	Station(int n,int d){
		this.no = n;
		this.d = d;
	}
	@Override
	public int compareTo(Station s) {
		if(d-s.d!=0)
			return d-s.d;
		return no-s.no;
	}
}