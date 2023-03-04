import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		String name;
		HashSet<Node> parent;
		HashSet<Node> children;
		HashMap<Node,Integer> parentDist;

		public Node(String name) {
			this.name=name;
			this.parent=new HashSet<>();
			this.children=new HashSet<>();
		}
		
		private void computeParentDistHelper(HashSet<Node> pn, int level) {
			for (Node p: pn) if (!this.parentDist.containsKey(p)) {
				this.parentDist.put(p,level);
				computeParentDistHelper(p.parent,level+1);
			}
		}

		private void computeParent() {
			this.parentDist=new HashMap<>();
			this.parentDist.put(this,0);
			computeParentDistHelper(this.parent,1);
		}

		public String toString() {
			return this.name;
		}
	}

	private static Node findLCA(Node np, Node nq) {
		Node lca=null;
		int lowest=Integer.MAX_VALUE;
		for (Node n: np.parentDist.keySet()) if (nq.parentDist.containsKey(n)) {
			int dp=np.parentDist.get(n);
			int dq=nq.parentDist.get(n);
			int dm=Math.min(dp,dq);
			if (dm<lowest) {
				lca=n;
				lowest=dm;
			}
		}
		return lca;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		HashMap<String,Node> nodeMap=new HashMap<>();
		while (true) {
			String s=br.readLine();
			if (s.equals("no.child no.parent")) break;

			StringTokenizer st=new StringTokenizer(s);
			String cName=st.nextToken();
			if (!nodeMap.containsKey(cName)) nodeMap.put(cName,new Node(cName));
			String pName=st.nextToken();
			if (!nodeMap.containsKey(pName)) nodeMap.put(pName,new Node(pName));
			
			Node np=nodeMap.get(pName);
			Node nq=nodeMap.get(cName);
			nq.parent.add(np);
			np.children.add(nq);
		}
		
		for (Node n: nodeMap.values()) n.computeParent();

		while (true) {
			String s=br.readLine();
			if (s==null) break;
			
			StringTokenizer st=new StringTokenizer(s);
			Node np=nodeMap.get(st.nextToken());
			Node nq=nodeMap.get(st.nextToken());
			if (np==null || nq==null) {
				System.out.println("no relation");
				continue;
			}
			
			Node lca=findLCA(np,nq);
			if (lca==null) {
				System.out.println("no relation");
				continue;
			}
			int pDiff=np.parentDist.get(lca);
			int qDiff=nq.parentDist.get(lca);
			if (pDiff==qDiff && (pDiff==1 || np==nq)) {
				System.out.println("sibling");
				continue;
			}
			if (lca==np) {
				StringBuilder sb=new StringBuilder();
				int diff=nq.parentDist.get(lca);
				for (int i=2;i<diff;i++) sb.append("great ");
				if (diff>1) sb.append("grand ");
				sb.append("parent");
				System.out.println(sb);
				continue;
			}
			if (lca==nq) {
				StringBuilder sb=new StringBuilder();
				int diff=np.parentDist.get(lca);
				for (int i=2;i<diff;i++) sb.append("great ");
				if (diff>1) sb.append("grand ");
				sb.append("child");
				System.out.println(sb);
				continue;
			}
			
			pDiff--;
			qDiff--;
			if (pDiff==qDiff) System.out.printf("%d cousin\n",pDiff);
			else System.out.printf("%d cousin removed %d\n",Math.min(pDiff,qDiff),Math.abs(pDiff-qDiff));
		}
	}

}
