import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static class Node {
		public Node parent;
		public Node [] childNodes;
		public int childNodesCount;
		public int level;
		public char color;
		
		public Node(Node parent) {
			this.parent=parent;
			this.childNodes=new Node [4];
			this.childNodesCount=0;
			this.level=5;
		}
		
		public Node(Node parent, char color) {
			this.parent=parent;
			this.color=color;
			this.level=5;
		}
		
		public Node addChild(Node n) {
			this.childNodes[this.childNodesCount++]=n;
			n.level=this.level-1;
			return n;
		}
		
		public void merge(Node n) {
			if (n.color=='f') {
				this.color='f';
				this.childNodes=null;
				this.childNodesCount=0;
			} else if (this.childNodesCount>0 && n.childNodesCount>0) {
				for (int i=0;i<childNodesCount;i++) this.childNodes[i].merge(n.childNodes[i]);
			} else if (this.color=='e' && n.childNodesCount>0) {
				this.childNodes=n.childNodes;
				this.childNodesCount=n.childNodesCount;
			}
		}
		
		public int blackPixelCount() {
			if (this.color=='f') return (int)Math.pow(2, this.level+this.level);
			else if (this.childNodesCount>0) {
				int sum=0;
				for (int i=0;i<childNodesCount;i++) sum+=this.childNodes[i].blackPixelCount();
				return sum;
			}
			return 0;
		}
	}
	
	private static Node constructTree(char [] ary) {
		Node rootNode=null;
		Node currNode;
		
		currNode=null;
		for (char c : ary) {
			if (c=='p') {
				if (currNode==null) {
					currNode=new Node(null);
					rootNode=currNode;
				} else {
					while (currNode.childNodesCount==4) currNode=currNode.parent;
					currNode=currNode.addChild(new Node(currNode));
				}
			} else {
				if (currNode==null) {
					currNode=new Node(null, c);
					rootNode=currNode;
				} else {
					while (currNode.childNodesCount==4) currNode=currNode.parent;
					currNode.addChild(new Node(currNode, c));
				}
			}
		}
		
		return rootNode;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			Node node1=constructTree(br.readLine().toCharArray());
			Node node2=constructTree(br.readLine().toCharArray());
			node1.merge(node2);
			
			System.out.println("There are "+node1.blackPixelCount()+" black pixels.");
		}
	}

}
