import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Character> [] EdgeList;
	private static char Start;
	private static char End;
	private static ArrayList<Character> [] Prev=new ArrayList [128];
	private static HashSet<Character> Nodes=new HashSet<>();

	private static class Edge implements Comparable<Edge> {
		long rem;
		long cost;
		char n;
		
		public Edge(char n, long c, long r) {
			this.n=n;
			this.cost=c;
			this.rem=r;
		}
		
		public int compareTo(Edge e) {
			return Long.compare(this.cost,e.cost);
		}
	}

	private static long compute(long init) {
		long [] minCost=new long [128];
		Arrays.fill(minCost, init);
		for (char n: Nodes) Prev[n]=new ArrayList<>();

		PriorityQueue<Edge> q=new PriorityQueue<Edge>();
		q.add(new Edge(Start,0,init));
		minCost[Start]=0;
		
		long ret=Long.MAX_VALUE;
		while (!q.isEmpty()) {
			Edge e=q.poll();
			if (e.n==End) {
				ret=Math.min(e.rem,ret);
				continue;
			}
			if (EdgeList[e.n]==null) continue;

			for (int i=0;i<EdgeList[e.n].size();i++) {
				char node=EdgeList[e.n].get(i);
				long nCost=-1;
				if (node>='A' && node<='Z') { // Town
					nCost=e.rem/20;
					if (nCost*20<e.rem) nCost++;
				}
				else if (node>='a' && node <='z') nCost=1; // Village

				if (nCost==-1) continue;
				if (nCost>e.rem) continue;

				if (e.cost+nCost<=minCost[node]) {
					if (e.cost+nCost<minCost[node]) Prev[node].clear();
					Prev[node].add(e.n);
					minCost[node]=e.cost+nCost;
					q.offer(new Edge(node,e.cost+nCost,e.rem-nCost));
				}
			}
		}

		if (ret==Long.MAX_VALUE) ret=0;
		return ret;
	}
	
	private static List<Character> Solution=null;

	private static ArrayList<Character> reverseList(List<Character> list, int end) {
		ArrayList<Character> ret=new ArrayList<>();
		for (int i=end-1;i>=0;i--) ret.add(list.get(i));
		return ret;
	}

	private static void getSolHelper(ArrayList<Character> nList, int idx, char curr) {
		if (curr==Start) {
			if (Solution==null) Solution=reverseList(nList,idx);
			else {
				List<Character> toCmp=reverseList(nList,idx);
				int maxIdx=Math.min(Solution.size(), toCmp.size());
				for (int i=0;i<maxIdx;i++) {
					char c1=Solution.get(i);
					char c2=toCmp.get(i);
					if (c1==c2) continue;
					if (c1>c2) {
						Solution=toCmp;
						return;
					} else return;
				}
				if (toCmp.size()<Solution.size()) Solution=toCmp;
			}
		} else if (Prev[curr]!=null) {
			for (int i=0;i<Prev[curr].size();i++) {
				char n=Prev[curr].get(i);
				nList.set(idx,n);
				getSolHelper(nList,idx+1,n);
			}
		}
	}

	private static String getSol() {
		Solution = null;
		ArrayList<Character> nList=new ArrayList<>();
		for (int i=0;i<60;i++) nList.add('!');
		nList.set(0,End);
		getSolHelper(nList,1,End);

		StringBuilder sb=new StringBuilder();
		for (int i=0;i<Solution.size();i++) {
			sb.append(Solution.get(i));
			sb.append('-');
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}

	public static void main(String [] args) throws Exception {
		for (int i=0;i<Prev.length;i++) Prev[i]=new ArrayList<>();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine().trim()).equals("-1")) {
			int N=Integer.parseInt(s);

			EdgeList=new ArrayList [128];
			Nodes.clear();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				char n1=st.nextToken().charAt(0);
				char n2=st.nextToken().charAt(0);
				if (EdgeList[n1]==null) EdgeList[n1]=new ArrayList<>();
				if (EdgeList[n2]==null) EdgeList[n2]=new ArrayList<>();

				EdgeList[n1].add(n2);
				EdgeList[n2].add(n1);
				Nodes.add(n1);
				Nodes.add(n2);
			}

			StringTokenizer st=new StringTokenizer(br.readLine());
			long target=Long.parseLong(st.nextToken());
			Start=st.nextToken().charAt(0);
			End=st.nextToken().charAt(0);

			long min=0;
			long max=Long.MAX_VALUE>>1;
			int loop=64;
			while (max>min+1 && loop-->0) {
				long mid=(min+max)>>1;
				if (compute(mid)>=target) max=mid;
				else min=mid;
			}
			compute(max);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(":\n");
			sb.append(max);
			sb.append('\n');
			sb.append(getSol());

			System.out.println(sb);
		}
	}

}
