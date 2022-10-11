import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		int id;
		ArrayList<Node> adj;
		int num, low;
		Node parent;
		
		public Node(int id) {
			this.id=id;
			this.adj=new ArrayList<>();
			this.num=-1;
			this.low=-1;
		}
	}
	
	private static class Link implements Comparable<Link> {
		int min, max;
		
		public Link(int f, int t) {
			this.min=Math.min(f,t);
			this.max=Math.max(f,t);
		}
		
		public int compareTo(Link l) {
			if (this.min!=l.min) return this.min-l.min;
			return this.max-l.max;
		}
	}

	private static ArrayList<Link> WeakLinks;
	private static int DfsNumMax;

	private static void markBridge(Node curr) {
		curr.num=DfsNumMax++;
		curr.low=curr.num;

		for (int i=0;i<curr.adj.size();i++) {
			Node next=curr.adj.get(i);
			if (next.num==-1) {
				next.parent=curr;
				markBridge(next);
				if (next.low>curr.num) WeakLinks.add(new Link(curr.id,next.id));
				curr.low=Math.min(curr.low,next.low);
			} else if (curr.parent!=next) {
				curr.low=Math.min(curr.low,next.num);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;
			
			Node [] nodes=new Node[N];
			for (int n=0;n<N;n++) nodes[n]=new Node(n);
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int i=Integer.parseInt(st.nextToken());
				int j=Integer.parseInt(st.nextToken());
				nodes[i].adj.add(nodes[j]);
				nodes[j].adj.add(nodes[i]);
			}
			
			WeakLinks=new ArrayList<>();
			DfsNumMax=0;
			for (int n=0;n<N;n++) if (nodes[n].num==-1) markBridge(nodes[n]);
			Collections.sort(WeakLinks);
			
			StringBuilder sb=new StringBuilder();
			sb.append(WeakLinks.size());
			sb.append(' ');
			for (int i=0;i<WeakLinks.size();i++) {
				Link l=WeakLinks.get(i);
				sb.append(l.min);
				sb.append(' ');
				sb.append(l.max);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}
