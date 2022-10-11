import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		String name;
		ArrayList<Node> adjList;
		int num, low;
		boolean visited;
		
		public Node(String name) {
			this.name=name;
			this.adjList=new ArrayList<>();
		}
	}

	private static int SeqNumMax;
	private static ArrayList<String> Groups;
	
	private static void scc(Node curr, Stack<Node> stk) {
		curr.num=SeqNumMax++;
		curr.low=curr.num;
		curr.visited=true;
		stk.push(curr);

		for (int i=0;i<curr.adjList.size();i++) {
			Node next=curr.adjList.get(i);
			if (next.num==0) scc(next,stk);
			if (next.visited) curr.low=Math.min(curr.low, next.low);
		}

		if (curr.num==curr.low) {
			StringBuilder sb=new StringBuilder();
			while (true) {
				Node temp=stk.pop();
				temp.visited=false;
				sb.append(temp.name);
				sb.append(", ");
				if (temp==curr) break;
			}
			sb.setLength(sb.length()-2);
			Groups.add(sb.toString());
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
			
			HashMap<String, Node> nodes=new HashMap<>();
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				String p1=st.nextToken();
				String p2=st.nextToken();
				if (!nodes.containsKey(p1)) nodes.put(p1,new Node(p1));
				if (!nodes.containsKey(p2)) nodes.put(p2,new Node(p2));
				nodes.get(p1).adjList.add(nodes.get(p2));
			}
			
			SeqNumMax=1;
			Groups=new ArrayList<>();
			for (Node n: nodes.values()) if (n.num==0) scc(n, new Stack<>());

			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Calling circles for data set ");
			sb.append(tc++);
			sb.append(":\n");
			for (int i=0;i<Groups.size();i++) {
				sb.append(Groups.get(i));
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}