import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Worker {
		int age;
		Node node;

		public Worker(int age) {
			this.age=age;
		}
	}
	
	private static class Node {
		int id;
		ArrayList<Node> from;
		Worker worker;
		
		public Node(int id, Worker worker) {
			this.id=id;
			this.from=new ArrayList<>();
			this.worker=worker;
		}
	}

	private static int [] Dp;

	private static int find(Node curr) {
		if (Dp[curr.id]==-1) { // TLE without DP :/
			int min=curr.worker.age;
			for (Node n: curr.from) min=Math.min(min,find(n));
			Dp[curr.id]=min;
		}
		return Dp[curr.id];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int I=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			Worker [] workers=new Worker [N];
			Node [] nodes=new Node[N];
			for (int n=0;n<N;n++) {
				workers[n]=new Worker(Integer.parseInt(st.nextToken()));
				nodes[n]=new Node(n,workers[n]);
				workers[n].node=nodes[n];
			}
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken())-1;
				int y=Integer.parseInt(st.nextToken())-1;
				workers[y].node.from.add(workers[x].node);
			}

			Dp=new int [N];
			Arrays.fill(Dp,-1);
			for (int i=0;i<I;i++) {
				st=new StringTokenizer(br.readLine());
				char op=st.nextToken().charAt(0);
				if (op=='P') {
					int E=Integer.parseInt(st.nextToken())-1;
					Node node=workers[E].node;
					if (node.from.isEmpty()) System.out.println('*');
					else {
						int min=Integer.MAX_VALUE;
						for (Node n: node.from) min=Math.min(min,find(n));
						System.out.println(min);
					}
				} else if (op=='T') {
					int A=Integer.parseInt(st.nextToken())-1;
					int B=Integer.parseInt(st.nextToken())-1;
					Node nodeA=workers[A].node;
					Node nodeB=workers[B].node;
					
					nodeA.worker=workers[B];
					nodeB.worker=workers[A];
					workers[A].node=nodeB;
					workers[B].node=nodeA;
					
					Arrays.fill(Dp,-1); // Reset Dp
				}
			}
		}
	}

}
