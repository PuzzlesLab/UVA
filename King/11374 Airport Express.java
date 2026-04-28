import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class State implements Comparable<State> {
		int n,time,remT;
		State parent;

		public State(int n, int time, int remT, State s) {
			this.n=n;
			this.time=time;
			this.remT=remT;
			this.parent=s;
		}

		public int compareTo(State s) {
			return this.time-s.time;
		}
	}

	private static class Edge {
		int n, time;
		boolean isCommExp;
		
		public Edge(int n, int time, boolean isCommExp) {
			this.n=n;
			this.time=time;
			this.isCommExp=isCommExp;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX_TIME=10000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=0;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());

			ArrayList<Edge> [] adjList=new ArrayList [N+1];
			for (int n=0;n<N+1;n++) adjList[n]=new ArrayList<>();

			int M=Integer.parseInt(br.readLine());
			for (int m=0;m<M;m++) {
				 st=new StringTokenizer(br.readLine());
				 int X=Integer.parseInt(st.nextToken());
				 int Y=Integer.parseInt(st.nextToken());
				 int Z=Integer.parseInt(st.nextToken());
				 adjList[X].add(new Edge(Y,Z,false));
				 adjList[Y].add(new Edge(X,Z,false));
			}
			int K=Integer.parseInt(br.readLine());
			for (int k=0;k<K;k++) {
				 st=new StringTokenizer(br.readLine());
				 int X=Integer.parseInt(st.nextToken());
				 int Y=Integer.parseInt(st.nextToken());
				 int Z=Integer.parseInt(st.nextToken());
				 adjList[X].add(new Edge(Y,Z,true));
				 adjList[Y].add(new Edge(X,Z,true));
			}
			
			State ans=null;
			int [][] minTime=new int [N+1][2];
			for (int n=0;n<minTime.length;n++) Arrays.fill(minTime[n],MAX_TIME);

			PriorityQueue<State> q=new PriorityQueue<>();
			State init=new State(S,0,1,null);
			minTime[init.n][init.remT]=init.time;
			q.offer(init);
			while (!q.isEmpty()) {
				State curr=q.poll();
				if (curr.n==E) {
					ans=curr;
					break;
				}
				
				for (int i=0;i<adjList[curr.n].size();i++) {
					Edge e=adjList[curr.n].get(i);
					if (e.isCommExp && curr.remT==0) continue;

					State nS=new State(e.n,curr.time+e.time,curr.remT-(e.isCommExp?1:0),curr);
					if (minTime[nS.n][nS.remT]>nS.time) {
						minTime[nS.n][nS.remT]=nS.time;
						q.offer(nS);
					}
				}
			}

			int ticketSt=-1;
			int ansT=ans.time;

			Stack<State> stk=new Stack<>();
			while (ans!=null) {
				stk.push(ans);
				ans=ans.parent;
			}
			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			while (!stk.isEmpty()) {
				ans=stk.pop();
				if (ticketSt==-1 && ans.remT==0) ticketSt=ans.parent.n;
				sb.append(ans.n);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			sb.append('\n');
			if (ticketSt==-1) sb.append("Ticket Not Used");
			else sb.append(ticketSt);
			sb.append('\n');
			sb.append(ansT);
			
			System.out.println(sb);
			br.readLine(); // empty.
			tc++;
		}
	}

}