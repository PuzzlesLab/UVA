import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

class Main {

	private static class Node {
		Node left, right;
		char c;
	}

	private static char [] Post;
	private static int PostIdx;
	
	private static Node construct() {
		if (PostIdx<0) return null;

		Node node=new Node();
		node.c=Post[PostIdx--];
		if (Character.isUpperCase(node.c)) {
			node.left=construct();
			node.right=construct();
		}
		return node;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			Post=br.readLine().toCharArray();
			PostIdx=Post.length-1;
			Node root=construct();
			
			StringBuilder sb=new StringBuilder();
			LinkedList<Node> q=new LinkedList<>();
			q.add(root);
			while (!q.isEmpty()) {
				Node n=q.removeFirst();
				sb.append(n.c);
				if (n.right!=null) q.addLast(n.right);
				if (n.left!=null) q.addLast(n.left);
			}
			System.out.println(sb.reverse());
		}
	}

}
