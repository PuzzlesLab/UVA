import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Main {

	private static ArrayList<Integer> Nums;
	private static int NumIdx;
	private static StringBuilder Ans;
	
	private static class Node {
		Node left, right;
		int n;
		
		public Node(int n) {
			this.n=n;
		}
	}

	private static Node construct(int min, int max) {
		if (NumIdx==Nums.size()) return null;
		int num=Nums.get(NumIdx);
		if (num<min || num>max) return null;
		NumIdx++;

		Node curr=new Node(num);
		curr.left=construct(min,curr.n-1);
		curr.right=construct(curr.n+1,max);

		return curr;
	}
	
	private static void postOrder(Node n) {
		if (n.left!=null) postOrder(n.left);
		if (n.right!=null) postOrder(n.right);
		Ans.append(n.n);
		Ans.append('\n');
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;

		Nums=new ArrayList<>();
		while ((s=br.readLine())!=null) {
			if (s.isEmpty()) break;
			Nums.add(Integer.parseInt(s));
		}
		
		NumIdx=0;
		Node root=construct(0,Integer.MAX_VALUE);

		Ans=new StringBuilder();
		postOrder(root);
		System.out.print(Ans);
	}

}
