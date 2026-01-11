import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	private static int N;
	private static int S;
	private static int T;
	private static ArrayList<Edge> [] EdgeList;
	private static Edge [] PrevTable;

	private static class Edge implements Comparable<Edge> {
		int prev;
		int curr;
		double d;
		double t;
		
		public Edge(int s, int e, double d, double t) {
			this.prev=s;
			this.curr=e;
			this.d=d;
			this.t=t;
		}

		public int compareTo(Edge e) {
			return Double.compare(this.d, e.d);
		}
	}

	private static boolean reachable(double maxTemp) {
		boolean [] visited=new boolean [N];
		LinkedList<Edge> q=new LinkedList<>();
		q.push(new Edge(S,S,0.0,-100000.0));
		visited[S]=true;

		while (!q.isEmpty()) {
			Edge e=q.removeFirst();
			if (e.curr==T) return true;
			for (int n=0;n<EdgeList[e.curr].size();n++) {
				Edge oE=EdgeList[e.curr].get(n);
				if (oE.t<=maxTemp && !visited[oE.curr]) {
					visited[oE.curr]=true;
					q.addLast(new Edge(e.curr,oE.curr,0.0,Math.max(e.curr,oE.curr)));
				}
			}
		}

		return false;
	}

	private static void findMinDist(double maxTemp) {
		PrevTable=new Edge [N];
		PrevTable[S]=new Edge(S,S,0.0,-1000.0);
		
		PriorityQueue<Edge> q=new PriorityQueue<>();
		q.offer(new Edge(S,S,0.0,0.0));
		while (!q.isEmpty()) {
			Edge e=q.poll();
			if (e.curr==T) return;

			for (int n=0;n<EdgeList[e.curr].size();n++) {
				Edge oE=EdgeList[e.curr].get(n);
				if (oE.t<=maxTemp) {
					Edge nE=new Edge(e.curr,oE.curr,e.d+oE.d,0.0);
					if (PrevTable[oE.curr]==null || nE.d<PrevTable[oE.curr].d) {
						PrevTable[oE.curr]=nE;
						q.offer(nE);
					}
				}
			}
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			st=new StringTokenizer(br.readLine());
			S=Integer.parseInt(st.nextToken())-1;
			T=Integer.parseInt(st.nextToken())-1;

			TreeSet<Double> tempSet=new TreeSet<>();
			EdgeList=new ArrayList [N];
			for (int n=0;n<N;n++) EdgeList[n]=new ArrayList<>();
			for (int e=0;e<E;e++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())-1;
				int y=Integer.parseInt(st.nextToken())-1;
				double R=Double.parseDouble(st.nextToken());
				double D=Double.parseDouble(st.nextToken());
				EdgeList[x].add(new Edge(x,y,D,R));
				EdgeList[y].add(new Edge(y,x,D,R));
				tempSet.add(R);
			}

			boolean [] visited=new boolean [N];
			visited[S]=true;
			ArrayList<Double> temps=new ArrayList<>(tempSet);
			int min=0;
			int max=temps.size()-1;
			int mid=0;
			while (max>min) {
				mid=(min+max)/2;
				if (reachable(temps.get(mid))) max=mid;
				else min=mid+1;
			}
			findMinDist(temps.get(mid));
			if (PrevTable[T]==null) {
				mid++;
				findMinDist(temps.get(mid));
			}

			Stack<Integer> stk=new Stack<>();
			int temp=T;
			stk.push(temp);
			while (PrevTable[temp].prev!=temp) {
				stk.push(PrevTable[temp].prev);
				temp=PrevTable[temp].prev;
			}
			StringBuilder sb=new StringBuilder();
			while (!stk.isEmpty()) {
				sb.append(stk.pop()+1);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			sb.append('\n');
			sb.append(String.format("%.1f %.1f", PrevTable[T].d, temps.get(mid)));
			System.out.println(sb);
		}

	}

}
