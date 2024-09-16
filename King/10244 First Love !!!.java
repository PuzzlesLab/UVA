import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static char [] Choices;
	private static ArrayList<Node> Nodes;
	private static HashMap<String,Node> NodeMap;
	private static int N;
	
	private static class Node {
		String s;
		ArrayList<Node> adjList;
		
		public Node(String s) {
			this.s=s;
			this.adjList=new ArrayList<>();
		}
		
		public String toString() {
			return this.s;
		}
	}
	
	private static String heirholzer() {
		ArrayList<Node> sol=new ArrayList<>();
		Stack<Node> stk=new Stack<>();
		stk.push(Nodes.get(0));
		while (!stk.isEmpty()) {
			Node curr=stk.peek();
			if (!curr.adjList.isEmpty()) {
				stk.push(curr.adjList.remove(curr.adjList.size()-1));
			} else {
				sol.add(curr);
				stk.pop();
			}
		}
		Collections.reverse(sol);

		StringBuilder sb=new StringBuilder();
		sb.append(sol.get(0));
		for (int i=1;i<sol.size();i++) {
			String s=sol.get(i).s;
			sb.append(s.charAt(s.length()-1));
		}
		return sb.toString();
	}

	private static void makeNodes() {
		Nodes=new ArrayList<>();
		NodeMap=new HashMap<>();
		Stack<StringBuilder> stk=new Stack<>();
		stk.push(new StringBuilder());
		
		while (!stk.isEmpty()) {
			StringBuilder curr=stk.pop();
			if (curr.length()==N-1) {
				Node n=new Node(curr.toString());
				Nodes.add(n);
				NodeMap.put(n.s,n);
			} else {
				for (int i=0;i<Choices.length;i++) {
					curr.append(Choices[i]);
					stk.push(new StringBuilder(curr.toString()));
					curr.setLength(curr.length()-1);
				}
			}
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			Choices=st.nextToken().toCharArray();
			Arrays.sort(Choices);
			N=Integer.parseInt(st.nextToken());

			BigInteger length=BigInteger.valueOf(Choices.length).pow(N).add(BigInteger.valueOf(N).subtract(BigInteger.ONE));
			StringBuilder sb=new StringBuilder();
			sb.append(length);
			sb.append('\n');
			if (length.compareTo(BigInteger.valueOf(10001))<0) {
				makeNodes();

				for (int i=0;i<Nodes.size();i++) {
					Node n=Nodes.get(i);
					StringBuilder temp=new StringBuilder(n.s.substring(1));
					for (int i2=0;i2<Choices.length;i2++) {
						temp.append(Choices[i2]);
						n.adjList.add(NodeMap.get(temp.toString()));
						temp.setLength(temp.length()-1);
					}
				}
				sb.append(heirholzer());
				
			} else sb.append("TOO LONG TO PRINT.");
			System.out.println(sb);
		}
 	}

}