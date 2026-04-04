import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_DIST=1000000;
	private static int NC;
	private static int DC;
	private static Edge [] Edges;
	private static ArrayList<Edge> [] AdjList;
	private static boolean [] FixedCities;
	private static int FixedCount;
	private static int [] Judges;
	private static boolean [][] InAns;
	private static int AnsDist;
	private static int AnsCityCount;
	private static String AnsTrace;

	private static class Reader {
		private BufferedReader br;
		private StringTokenizer st;
	
		public Reader() {
			this.br=new BufferedReader(new InputStreamReader(System.in));
			this.st=new StringTokenizer("");
		}
		
		public int nextInt() throws Exception {
			while (!st.hasMoreTokens()) st=new StringTokenizer(br.readLine());
			return Integer.parseInt(st.nextToken());
		}
	}

	private static class Edge implements Comparable<Edge> {
		int n1, n2, dist;
		
		public Edge(int n1, int n2, int dist) {
			this.n1=n1;
			this.n2=n2;
			this.dist=dist;
		}
		
		public int compareTo(Edge e) {
			return this.dist-e.dist;
		}
	}
	
	private static String tracePath(int mask) {
		int [] parent=new int [NC];
		Arrays.fill(parent,-1);
		boolean [] visited=new boolean [NC];

		LinkedList<Integer> q=new LinkedList<>();
		q.addLast(DC);
		visited[DC]=true;
		int visitedFixCount=0;
		while (!q.isEmpty()) {
			int curr=q.removeFirst();
			if (FixedCities[curr]) {
				visitedFixCount++;
				if (visitedFixCount==FixedCount) break;
			}
			for (int i=0;i<AdjList[curr].size();i++) {
				int n=AdjList[curr].get(i).n2;
				if (!InAns[curr][n]) continue;
				if (visited[n]) continue;

				visited[n]=true;
				q.offer(n);
				parent[n]=curr;
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<Judges.length;i++) {
			sb.append("   ");
			int temp=Judges[i];
			while (temp!=-1) {
				sb.append(temp+1);
				sb.append('-');
				temp=parent[temp];
			}
			sb.setLength(sb.length()-1);
			sb.append('\n');
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}

	private static int getParent(int [] parent, int i) {
		if (parent[i]==i) return i;
		return parent[i]=getParent(parent,parent[i]);
	}
	
	private static void setParent(int [] parent, int i, int i2) {
		int p1=getParent(parent,i);
		int p2=getParent(parent,i2);
		if (p1>p2) parent[p1]=p2;
		else parent[p2]=p1;
	}

	private static void mst(int mask) {
		int [] parent=new int [NC];
		for (int i=0;i<parent.length;i++) parent[i]=i;
		InAns=new boolean [NC][NC];

		int dist=0;
		int cityCount=1;
		for (int i=0;i<Edges.length;i++) {
			Edge e=Edges[i];
			if ((mask&(1<<e.n1))==0) continue;
			if ((mask&(1<<e.n2))==0) continue;
			
			int p1=getParent(parent,e.n1);
			int p2=getParent(parent,e.n2);
			if (p1==p2) continue;
			setParent(parent,p1,p2);
			dist+=e.dist;
			cityCount++;

			InAns[e.n1][e.n2]=true;
			InAns[e.n2][e.n1]=true;
			if (dist>AnsDist) return;
		}

		if (dist==AnsDist && cityCount>AnsCityCount) return;

		boolean flag=true;
		int p=getParent(parent,DC);
		for (int i=0;i<NC && flag;i++) if (FixedCities[i]) flag&=getParent(parent,i)==p;
		if (!flag) return;

		AnsDist=dist;
		AnsCityCount=cityCount;
		String path=tracePath(mask);
		if (AnsTrace==null || path.compareTo(AnsTrace)<0) AnsTrace=path;
	}

	private static void compute(int mask, int curr) {
		if (curr==NC) {
			mst(mask);
			return;
		}

		if (FixedCities[curr]) compute(mask,curr+1); // Ignore the mask.
		else {
			compute(mask|(1<<curr),curr+1);
			compute(mask,curr+1);
		}
	}

	public static void main(String[] args) throws Exception {
		Reader r=new Reader();
		int tc=1;
		while (true) {
			NC=r.nextInt();
			if (NC==-1) break;
			DC=r.nextInt()-1;
			int NR=r.nextInt();

			Edges=new Edge[NR];
			for (int i=0;i<NR;i++) Edges[i]=new Edge(r.nextInt()-1,r.nextInt()-1,r.nextInt());
			Arrays.sort(Edges);
			
			AdjList=new ArrayList [NC];
			for (int i=0;i<NC;i++) AdjList[i]=new ArrayList<>();
			for (int i=0;i<NR;i++) {
				AdjList[Edges[i].n1].add(Edges[i]);
				AdjList[Edges[i].n2].add(new Edge(Edges[i].n2,Edges[i].n1,Edges[i].dist));
			}

			int NJ=r.nextInt();
			Judges=new int [NJ];
			for (int i=0;i<NJ;i++) Judges[i]=r.nextInt()-1;

			FixedCities=new boolean [NC];
			int mask=1<<DC;
			FixedCities[DC]=true;
			FixedCount=1;
			for (int i=0;i<NJ;i++) {
				mask|=1<<Judges[i];
				FixedCities[Judges[i]]=true;
				FixedCount++;
			}
			
			AnsDist=MAX_DIST;
			AnsCityCount=NC;
			AnsTrace=null;
			compute(mask,0);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": distance = ");
			sb.append(AnsDist);
			sb.append('\n');
			sb.append(AnsTrace);
			sb.append('\n');
			System.out.println(sb);
		}
	}

}