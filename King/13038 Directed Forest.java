import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		boolean isRoot;
		ArrayList<Node> children;
		int maxDepth;

		public Node() {
			this.isRoot=true;
			this.children=new ArrayList<>();
		}
	}

	private static class Context {
		Node n;
		int depth;
		
		public Context(Node n, int depth) {
			this.n=n;
			this.depth=depth;
		}
	}

	private static int findDepth(Node curr) { // Too deep to do DFS (Stackoverflow error) :/
		int max=1;
		Stack<Context> stk=new Stack<>();
		stk.push(new Context(curr,1));
		while (!stk.isEmpty()) {
			Context context=stk.pop();
			max=Math.max(max,context.depth);
			
			int nextDepth=context.depth+1;
			for (int i=0;i<context.n.children.size();i++) {
				Node next=context.n.children.get(i);
				if (next.maxDepth<nextDepth) {
					next.maxDepth=nextDepth;
					stk.push(new Context(next,nextDepth));
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			br.readLine();

			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			
			Node [] nodes=new Node[N];
			for (int n=0;n<nodes.length;n++) nodes[n]=new Node();
			
			for (int e=0;e<E;e++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				nodes[n2].isRoot=false;
				nodes[n1].children.add(nodes[n2]);
			}

			int max=0;
			for (int n=0;n<nodes.length;n++) if (nodes[n].isRoot) max=Math.max(max,findDepth(nodes[n]));
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(max);
			System.out.println(sb.toString());
		}
	}

}
