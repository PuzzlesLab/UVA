import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		Node left;
		Node right;
		int value;
		int inputCount;
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		Node root=new Node();
		while (sc.hasNext()) {
			String s=sc.next();
			if (s.equals("()")) {
				StringBuilder sb=new StringBuilder();
				LinkedList<Node> q=new LinkedList<>();
				q.addLast(root);
				while (!q.isEmpty()) {
					Node n=q.removeFirst();
					if (n.inputCount!=1) {
						sb=new StringBuilder("not complete ");
						break;
					}
					sb.append(n.value);
					sb.append(" ");
					if (n.left!=null) q.addLast(n.left);
					if (n.right!=null) q.addLast(n.right);
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb);
				root=new Node();
				continue;
			}
			
			s=s.substring(1,s.length()-1);
			StringTokenizer st=new StringTokenizer(s,",");
			int value=Integer.parseInt(st.nextToken());
			s=st.hasMoreTokens()? st.nextToken() : "";

			Node curr=root;
			for (int i=0;i<s.length();i++) {
				char c=s.charAt(i);
				if (c=='L') {
					if (curr.left==null) curr.left=new Node();
					curr=curr.left;
				} else if (c=='R') {
					if (curr.right==null) curr.right=new Node();
					curr=curr.right;
				}
			}
			curr.value=value;
			curr.inputCount++;
		}
	}

}