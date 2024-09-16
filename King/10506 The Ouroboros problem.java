import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Node> Nodes;
	private static int M;
	private static int N;
	
	private static class Node {
		public String s;
		public ArrayList<Node> edges;
		
		public Node(String s) {
			this.s=s;
			this.edges=new ArrayList<>();
		}
		
		public String toString() {
			return this.s;
		}
	}

	public static void makeNodes(StringBuilder curr) {
		if (curr.length()==M-1) {
			Nodes.add(new Node(curr.toString()));
			return;
		}
		for (int i=0;i<N;i++) {
			curr.append((char)('0'+i));
			makeNodes(curr);
			curr.setLength(curr.length()-1);
		}
	}
	
	public static String heirholzer() {
		ArrayList<Node> solution=new ArrayList<>();
		Stack<Node> stk=new Stack<>();
		stk.push(Nodes.get(0));
		while (stk.size()!=0) {
			Node curr=stk.peek();
			if (!curr.edges.isEmpty()) {
				stk.push(curr.edges.remove(curr.edges.size()-1));
			} else {
				solution.add(curr);
				stk.pop();
			}
		}
		Collections.reverse(solution);

		StringBuilder sb=new StringBuilder();
		sb.append(solution.get(0).s);
		for (int i=1;i<solution.size();i++) {
			String s=solution.get(i).s;
			sb.append(s.charAt(s.length()-1));
		}

		return sb.toString();
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			Nodes=new ArrayList<>();
			makeNodes(new StringBuilder());
			
			// Make edges;
			for (int i=0;i<Nodes.size();i++) for (int i2=0;i2<Nodes.size();i2++) {
				String s1=Nodes.get(i).s;
				String s2=Nodes.get(i2).s;
				if (s2.startsWith(s1.substring(1))) Nodes.get(i).edges.add(Nodes.get(i2));
			}
			
			String ans=heirholzer();
			ans=ans.substring(0,ans.length()-(M-1));
			System.out.println(ans);
		}
 	}

}
