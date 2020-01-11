package uva;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static class Hole {
		int floor, holeNo;
		ArrayList<Edge> edges;
		public Hole(int f, int h) {
			this.floor=f;
			this.holeNo=h;
			this.edges=new ArrayList<>();
		}
	}
	
	public static class Edge implements Comparable<Edge> {
		Hole next;
		int time;
		public Edge(Hole n, int t) {
			this.next=n;
			this.time=t;
		}
		public int compareTo(Edge e) {
			return this.time-e.time;
		}
	}
	/*
	 * Input Data Explanation:
	 * N = Floor, M= Hole M
	 * Line 1 (F0H0) - <Time to F1H0> <Time to F1H1> ... <Time to F1HM>
	 * Line 2 (F0H1) - <Time to F1H0> <Time to F1H1> ... <Time to F1HM>
	 * ....
	 * Line Z (F (N-1) HM) - <Time to FNH0> <Time to FNH1> ... <Time to FNHM>
	 */
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			String name=s;
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			Hole [][] holes=new Hole[N][M];
			
			for (int n=0;n<N;n++) for (int m=0;m<M;m++) holes[n][m]=new Hole(n,m);
			for (int n=0;n<N-1;n++) for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				for (int x=0;x<M;x++) holes[n][m].edges.add(new Edge(holes[n+1][x],Integer.parseInt(st.nextToken())));
			}
			
			HashMap<Hole,Integer> bestTime=new HashMap<>();
			PriorityQueue<Edge> q=new PriorityQueue<>();
			for (int m=0;m<M;m++) {
				bestTime.put(holes[0][m], 0);
				q.offer(new Edge(holes[0][m], 0));
			}
			int ans=-1;
			while (!q.isEmpty()) {
				Edge e=q.poll();
				if (e.next.floor==N-1) {
					ans=e.time;
					break;
				}
				for (Edge n : e.next.edges) {
					int totalTime=e.time+n.time+2;
					if (totalTime < bestTime.getOrDefault(n.next,Integer.MAX_VALUE)) {
						bestTime.put(n.next, totalTime);
						q.offer(new Edge(n.next,totalTime));
					}
				}
			}
			
			System.out.println(name);
			System.out.println(ans);
		}
	}

}