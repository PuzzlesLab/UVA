import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	private static class Tuple implements Comparable<Tuple> {
		int src, dest, length;
		public Tuple(int s, int d, int l) {
			this.src=s;
			this.dest=d;
			this.length=l;
		}
		public int compareTo(Tuple t) {
			return this.length-t.length;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;
			
			st=new StringTokenizer(br.readLine());
			int S=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			
			HashSet<Tuple> [] adjList=new HashSet [N];
			for (int n=0;n<N;n++) adjList[n]=new HashSet<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int U=Integer.parseInt(st.nextToken());
				int V=Integer.parseInt(st.nextToken());
				adjList[U].add(new Tuple(U, V, Integer.parseInt(st.nextToken())));
			}
			
			int [] minDist=new int [N];
			Arrays.fill(minDist,Integer.MAX_VALUE);
			ArrayList<Tuple> ancestors []=new ArrayList [N];
			for (int n=0;n<N;n++) ancestors[n]=new ArrayList<>();
			
			PriorityQueue<Tuple> q=new PriorityQueue<>();
			q.offer(new Tuple(S,S,0));
			minDist[S]=0;
			while (!q.isEmpty()) {
				Tuple t=q.poll();
				for (Tuple adj : adjList[t.dest]) {
					int d=t.length+adj.length;
					if (d<minDist[adj.dest]) {
						ancestors[adj.dest].clear();
						minDist[adj.dest]=d;
						q.offer(new Tuple(adj.src, adj.dest,d));
					}
					if (d==minDist[adj.dest]) ancestors[adj.dest].add(adj);
				}
			}
			
			if (minDist[D]!=Integer.MAX_VALUE) {
				Stack<Integer> stk=new Stack<>();
				stk.push(D);
				while (!stk.isEmpty()) {
					int dest=stk.pop();
					for (Tuple t : ancestors[dest]) {
						adjList[t.src].remove(t);
						stk.push(t.src);
					}
				}

				Arrays.fill(minDist,Integer.MAX_VALUE);
				q=new PriorityQueue<>();
				q.offer(new Tuple(S,S,0));
				minDist[S]=0;
				while (!q.isEmpty()) {
					Tuple t=q.poll();
					for (Tuple adj : adjList[t.dest]) {
						int d=t.length+adj.length;
						if (d<minDist[adj.dest]) {
							minDist[adj.dest]=d;
							q.offer(new Tuple(adj.src, adj.dest,d));
						}
					}
				}
			}

			System.out.println(minDist[D]==Integer.MAX_VALUE ? -1 : minDist[D]);
		}
	}

}