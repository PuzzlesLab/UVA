import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static class Node {
		int n;
		boolean cycle;
		ArrayList<Road> adjList;
		
		public Node (int n, boolean c) {
			this.n=n;
			this.cycle=c;
			this.adjList=new ArrayList<>();
		}
		public String toString() {
			return this.n+" "+(this.cycle?"CYCLE":"WALK");
		}
	}
	
	public static class Road implements Comparable<Road> {
		Node next;
		int dist;
		public Road (Node n, int d) {
			this.next=n;
			this.dist=d;
		}
		public int compareTo(Road r) { return this.dist-r.dist; }
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			
			Node [][] nodes=new Node [N][2];
			for (int n=0;n<N;n++) {
				nodes[n][0]=new Node(n,false);
				nodes[n][1]=new Node(n,true);
			}
			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				int dist=Integer.parseInt(st.nextToken());
				
				nodes[n1][0].adjList.add(new Road(nodes[n2][1],dist));
				nodes[n1][1].adjList.add(new Road(nodes[n2][0],dist));
				
				nodes[n2][0].adjList.add(new Road(nodes[n1][1],dist));
				nodes[n2][1].adjList.add(new Road(nodes[n1][0],dist));
			}
			
			HashMap<Node, Integer> maxDist=new HashMap<>();
			PriorityQueue<Road> q=new PriorityQueue<>();
			q.offer(new Road(nodes[0][1],0));
			maxDist.put(nodes[0][1],0);
			while (!q.isEmpty()) {
				Road curr=q.poll();
				for (Road r : curr.next.adjList) {
					int newDist=curr.dist+r.dist;
					if (newDist<maxDist.getOrDefault(r.next,Integer.MAX_VALUE)) {
						q.offer(new Road(r.next,newDist));
						maxDist.put(r.next,newDist);
					}
				}
			}
			
			int ans=maxDist.getOrDefault(nodes[N-1][1],-1);
			System.out.printf("Set #%d\n%s\n", testCase++, ans!=-1 ? ans : "?");
		}
	}

}