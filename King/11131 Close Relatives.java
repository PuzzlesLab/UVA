import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static StringBuilder PostOrderAns;

	private static class Node {
		String name;
		ArrayList<Node> parents;
		int inDeg;
		
		public Node(String name) {
			this.name=name;
			this.parents=new ArrayList<>();
		}
	}

	private static Node getNode(HashMap<String,Node> nodeMap, String name) {
		if (nodeMap.containsKey(name)) return nodeMap.get(name);
		
		Node n=new Node(name);
		nodeMap.put(name,n);
		return n;
	}

	private static void postOrder1(Node n) {
		for (int i=0;i<n.parents.size();i++) postOrder1(n.parents.get(i));
		PostOrderAns.append(n.name);
		PostOrderAns.append('\n');
	}
	
	private static void postOrder2(Node n) {
		for (int i=n.parents.size()-1;i>=0;i--) postOrder2(n.parents.get(i));
		PostOrderAns.append(n.name);
		PostOrderAns.append('\n');
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		HashMap<String,Node> nodeMap=new HashMap<>();
		String s;
		while ((s=br.readLine())!=null) {
			if (s.isEmpty()) break;
			
			StringTokenizer st=new StringTokenizer(s,",");
			Node c=getNode(nodeMap,st.nextToken());
			while (st.hasMoreTokens()) {
				Node p=getNode(nodeMap,st.nextToken());
				p.inDeg++;
				c.parents.add(p);
			}
		}
		
		Node root=null;
		for (Node n: nodeMap.values()) if (n.inDeg==0) {
			root=n;
			break;
		}

		PostOrderAns=new StringBuilder();
		postOrder1(root);
		String post1=PostOrderAns.toString();

		PostOrderAns=new StringBuilder();
		postOrder2(root);
		String post2=PostOrderAns.toString();

		StringBuilder ans=new StringBuilder();
		if (post1.equals(post2)) {
			ans.append("1\n\n");
			ans.append(post1);
		} else {
			ans.append("2\n\n");
			ans.append(post1);
			ans.append('\n');
			ans.append(post2);
		}
		System.out.print(ans);
	}

}
