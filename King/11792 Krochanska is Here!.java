import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static class Context {
		int n, dist;
		
		public Context(int n, int dist) {
			this.n=n;
			this.dist=dist;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());

			ArrayList<Integer> [] adjList=new ArrayList [N];
			for (int n=0;n<N;n++) adjList[n]=new ArrayList<>();
			int [] inLine=new int [N];

			for (int l=0;l<L;l++) {
				st=new StringTokenizer(br.readLine());
				ArrayList<Integer> stations=new ArrayList<>();
				while (st.hasMoreTokens()) stations.add(Integer.parseInt(st.nextToken())-1);
				stations.remove(stations.size()-1); // Remove the last 0 (delimiter)

				for (int i=0;i<stations.size();i++) {
					int station=stations.get(i);
					inLine[station]++;
					if (i>0) adjList[station].add(stations.get(i-1));
					if (i+1<stations.size()) adjList[station].add(stations.get(i+1));
				}
			}
			
			int impStationsCount=0;
			for (int n=0;n<N;n++) if (inLine[n]>1) impStationsCount++;
			
			int ansStation=N;
			int ansDist=Integer.MAX_VALUE;
			for (int n=0;n<N;n++) {
				if (inLine[n]<=1) continue;

				int dist=0;
				int remImp=impStationsCount;
				boolean [] visited=new boolean [N];
				visited[n]=true;

				LinkedList<Context> q=new LinkedList<>();
				q.add(new Context(n,0));
				while (!q.isEmpty()) {
					Context ctx=q.removeFirst();
					if (inLine[ctx.n]>1) {
						// Reached important station
						dist+=ctx.dist;
						remImp--;
						if (remImp==0) break; // All important stations reached, ends here.
					}
					for (int i=0;i<adjList[ctx.n].size();i++) {
						int next=adjList[ctx.n].get(i);
						if (!visited[next]) {
							visited[next]=true;
							q.addLast(new Context(next,ctx.dist+1));
						}
					}
				}
				
				if (dist==ansDist) ansStation=Math.min(ansStation,n);
				else if (dist<ansDist) {
					ansDist=dist;
					ansStation=n;
				}
			}
			System.out.printf("Krochanska is in: %d\n", ansStation+1);
		}
	}

}