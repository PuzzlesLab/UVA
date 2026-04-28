import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class State implements Comparable<State> {
		int n,time,remK;

		public State(int n, int time, int remK) {
			this.n=n;
			this.time=time;
			this.remK=remK;
		}
		
		public int compareTo(State s) {
			return this.time-s.time;
		}
	}

	private static class Edge {
		int n, dist;
		
		public Edge(int n, int dist) {
			this.n=n;
			this.dist=dist;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX_DIST=10000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			int N=A+B;
			int M=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());

			ArrayList<Edge> [] adjList=new ArrayList [N+1];
			int [][] nodeDist=new int [N+1][N+1];
			for (int i=0;i<nodeDist.length;i++) {
				adjList[i]=new ArrayList<>();
				Arrays.fill(nodeDist[i],MAX_DIST);
			}
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				nodeDist[x][y]=Math.min(nodeDist[x][y],d);
				nodeDist[y][x]=Math.min(nodeDist[y][x],d);
				
				adjList[x].add(new Edge(y,d));
				adjList[y].add(new Edge(x,d));
			}
			for (int k=1;k<=A;k++) for (int i=1;i<=N;i++) for (int j=1;j<=N;j++) {
				nodeDist[i][j]=Math.min(nodeDist[i][j],nodeDist[i][k]+nodeDist[k][j]);
			}

			int ans=MAX_DIST;
			State init=new State(N,0,K);
			int [][] minDist=new int [N+1][K+1];
			for (int n=0;n<minDist.length;n++) Arrays.fill(minDist[n],MAX_DIST);
			PriorityQueue<State> q=new PriorityQueue<>();
			minDist[init.n][init.remK]=0;
			q.offer(init);
			while (!q.isEmpty()) {
				State curr=q.poll();
				if (curr.n==1) {
					ans=curr.time;
					break;
				}
				
				// No boot.
				for (int i=0;i<adjList[curr.n].size();i++) {
					Edge e=adjList[curr.n].get(i);
					State nS=new State(e.n,curr.time+e.dist,curr.remK);
					if (minDist[nS.n][nS.remK]>nS.time) {
						minDist[nS.n][nS.remK]=nS.time;
						q.offer(nS);
					}
				}

				// With boot.
				if (curr.remK>0) {
					for (int next=0;next<=N;next++) if (nodeDist[curr.n][next]<=L) {
						State nS=new State(next,curr.time,curr.remK-1);
						if (minDist[nS.n][nS.remK]>nS.time) {
							minDist[nS.n][nS.remK]=nS.time;
							q.offer(nS);
						}
					}
				}
			}
			System.out.println(ans);
		}
	}

}