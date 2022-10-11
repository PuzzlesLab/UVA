import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		ArrayList<Node> adj;
		int num, low;
		boolean visited;

		public Node() {
			this.adj=new ArrayList<>();
		}
	}

	private static int SeqNumMax;
	private static int SCCCount;
	
	private static void scc(Node curr, Stack<Node> stk) {
		curr.num=SeqNumMax++;
		curr.low=curr.num;
		curr.visited=true;
		stk.push(curr);

		for (int i=0;i<curr.adj.size();i++) {
			Node next=curr.adj.get(i);
			if (next.num==0) scc(next, stk);
			if (next.visited) curr.low=Math.min(curr.low,next.low);
		}
		
		if (curr.num==curr.low) {
			SCCCount++;
			while (true) {
				Node temp=stk.pop();
				temp.visited=false;
				if (temp==curr) break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;
			
			Node [] nodes=new Node[N+1];
			for (int n=1;n<=N;n++) nodes[n]=new Node();
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int V=Integer.parseInt(st.nextToken());
				int W=Integer.parseInt(st.nextToken());
				int P=Integer.parseInt(st.nextToken());
				nodes[V].adj.add(nodes[W]);
				if (P==2) nodes[W].adj.add(nodes[V]);
			}
			
			SCCCount=0;
			SeqNumMax=1;
			for (int n=1;n<=N;n++) if (nodes[n].num==0) scc(nodes[n], new Stack<Node>());

			System.out.println(SCCCount==1?1:0);
		}
	}

}