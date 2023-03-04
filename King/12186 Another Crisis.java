import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Node {
		ArrayList<Node> so;
		
		public Node() {
			this.so=new ArrayList<>();
		}

		public int countMeetT(double T) {
			if (so.size()==0) return 1;

			PriorityQueue<Integer> pq=new PriorityQueue<>();
			for (int i=0;i<so.size();i++) pq.offer(so.get(i).countMeetT(T));
			int min=(int)Math.ceil(T*so.size());
			int sum=0;
			for (int i=0;i<min;i++) sum+=pq.poll();
			return sum;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			double T=Integer.parseInt(st.nextToken())/100.0;

			Node [] nodes=new Node[N+1];
			for (int n=0;n<=N;n++) nodes[n]=new Node();

			st=new StringTokenizer(br.readLine());
			for (int n=1;n<=N;n++) nodes[Integer.parseInt(st.nextToken())].so.add(nodes[n]);

			System.out.println(nodes[0].countMeetT(T));
		}
	}

}
