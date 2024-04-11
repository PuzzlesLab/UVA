import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static class Node {
		Node [] children;
		int count;

		public Node() {
			this.children=new Node[26];
		}
		
		public Node add(int c) {
			if (this.children[c]==null) this.children[c]=new Node();
			return this.children[c];
		}
	}

	private static int dfs(Node root, int level) {
		if (root.count==1) return level;

		int count=0;
		for (int i=0;i<root.children.length;i++) if (root.children[i]!=null) {
			count+=dfs(root.children[i],level+1);
		}
		return count;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int N=Integer.parseInt(br.readLine());
			Node root=new Node();
			
			for (int n=0;n<N;n++) {
				String s=br.readLine();
				Node curr=root;
				for (int i=0;i<s.length();i++) {
					curr=curr.add(s.charAt(i)-'a');
					curr.count++;
				}
			}

			System.out.println(dfs(root,0));
		}
	}

}