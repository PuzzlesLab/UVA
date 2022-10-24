import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Scanner {
		private BufferedReader reader;
		private StringTokenizer st;

		public Scanner() {
			this.reader=new BufferedReader(new InputStreamReader(System.in));
		}
		
		public boolean hasNextInt() throws IOException {
			if (st!=null && st.hasMoreTokens()) return true;
			String nextLine=this.reader.readLine();
			if (nextLine==null) return false;

			this.st=new StringTokenizer(nextLine);
			return true;
		}
		
		public int nextInt() throws IOException {
			if (st==null || !st.hasMoreTokens()) this.st=new StringTokenizer(this.reader.readLine());
			return Integer.parseInt(st.nextToken());
		}
	}

	private static class Edge implements Comparable<Edge> {
		int to, time;
		
		public Edge(int to, int time) {
			this.to=to;
			this.time=time;
		}
		
		public int compareTo(Edge edge) {
			return this.time-edge.time;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX_DIST=1000000;

		Scanner sc=new Scanner(); // Seems like there is malicious judge input that differs from problem statement. Tested RTE! :/
		while (sc.hasNextInt()) {
			int N=sc.nextInt();
			int M=sc.nextInt();
			int B=sc.nextInt();
			int P=sc.nextInt();

			ArrayList<Edge> [] adjList=new ArrayList [N];
			for (int n=0;n<N;n++) adjList[n]=new ArrayList<>();
			for (int m=0;m<M;m++) {
				int u=sc.nextInt();
				int v=sc.nextInt();
				int t=sc.nextInt();
				adjList[u].add(new Edge(v,t));
				adjList[v].add(new Edge(u,t));
			}
			
			boolean [] isBank=new boolean[N];
			int [] banks=new int [B];
			for (int b=0;b<B;b++) {
				banks[b]=sc.nextInt();
				isBank[banks[b]]=true;
			}

			int [] polices=new int [N];
			for (int p=0;p<P;p++) polices[p]=sc.nextInt();

			int [] minDistToP=new int [N];
			Arrays.fill(minDistToP,MAX_DIST);
			PriorityQueue<Edge> q=new PriorityQueue<>();
			for (int p=0;p<P;p++) q.offer(new Edge(polices[p],0));
			int banksLeft=B;
			while (!q.isEmpty()) {
				Edge edge=q.poll();
				if (edge.time>minDistToP[edge.to]) continue;
				if (isBank[edge.to]) {
					banksLeft--;
					if (banksLeft==0) break;
				}
				for (int i=0;i<adjList[edge.to].size();i++) {
					Edge nEdge=adjList[edge.to].get(i);
					int nTime=edge.time+nEdge.time;
					if (nTime>=minDistToP[nEdge.to]) continue;
					minDistToP[nEdge.to]=nTime;
					q.offer(new Edge(nEdge.to,nTime));
				}
			}

			int maxIdx=-1;
			int maxCount=1;
			for (int n=0;n<N;n++) if (isBank[n]) {
				if (maxIdx==-1 || minDistToP[n]>minDistToP[maxIdx]) {
					maxIdx=n;
					maxCount=1;
				} else if (minDistToP[n]==minDistToP[maxIdx]) maxCount++;
			}

			StringBuilder sb=new StringBuilder();
			sb.append(maxCount);
			sb.append(' ');
			sb.append(minDistToP[maxIdx]==MAX_DIST?"*":minDistToP[maxIdx]);
			sb.append('\n');
			for (int n=0;n<N;n++) if (isBank[n] && minDistToP[n]==minDistToP[maxIdx]) {
				sb.append(n);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}