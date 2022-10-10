import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		int id;
		ArrayList<Node> adj;
		int seqLow, seqNum;
		Node parent;

		public Node(int id) {
			this.id=id;
			this.adj=new ArrayList<>();
		}
	}

	private static boolean [][] Edge;
	private static int SeqMax;

	private static void mark(Node curr) {
		curr.seqNum=SeqMax++;
		curr.seqLow=curr.seqNum;
		for (int i=0;i<curr.adj.size();i++) {
			Node next=curr.adj.get(i);
			if (next.seqNum==0) { // Unvisited.
				next.parent=curr;
				mark(next);
				if (next.seqLow>curr.seqNum) { // Bridge
					Edge[curr.id][next.id]=true;
					Edge[next.id][curr.id]=true;
				} else if (!Edge[next.id][curr.id]) Edge[curr.id][next.id]=true; // Not bridge, single direction is ok.
				curr.seqLow=Math.min(curr.seqLow,next.seqLow);
			} else if (next!=curr.parent) {
				curr.seqLow=Math.min(curr.seqLow,next.seqNum);
				if (!Edge[next.id][curr.id]) Edge[curr.id][next.id]=true;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;

			Node [] node=new Node [N+1];
			for (int n=1;n<=N;n++) node[n]=new Node(n);
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				node[n1].adj.add(node[n2]);
				node[n2].adj.add(node[n1]);
			}

			Edge=new boolean [N+1][N+1];
			SeqMax=1;
			for (int n=1;n<=N;n++) if (node[n].seqNum==0) mark(node[n]);
			
			StringBuilder sb=new StringBuilder();
			sb.append(tc++);
			sb.append("\n\n");
			for (int n=1;n<=N;n++) for (int n2=1;n2<=N;n2++) if (Edge[n][n2]) {
				sb.append(n);
				sb.append(' ');
				sb.append(n2);
				sb.append('\n');
			}
			sb.append('#');
			System.out.println(sb.toString());
		}
	}

}
