import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Node {
		int id;
		Node prev;
		Node next;
		boolean out;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			Node [] nodes=new Node[N];
			for (int i=1;i<=N;i++) {
				nodes[i-1]=new Node();
				nodes[i-1].id=i;
			}
			for (int i=0;i<N;i++) {
				nodes[i].prev=nodes[Math.floorMod(i-1,N)];
				nodes[i].next=nodes[(i+1)%N];
			}
			
			StringBuilder sb=new StringBuilder();
			int size=N;
			Node curr1 = nodes[0];
			Node curr2 = nodes[N-1];
			while (size>0) {
				for (int k=1;k<K;k++) curr1=curr1.next;
				for (int m=1;m<M;m++) curr2=curr2.prev;
				
				if (curr1!=curr2) {
					sb.append(String.format("%3d",curr1.id));
					sb.append(String.format("%3d",curr2.id));
					curr1.prev.next=curr1.next;
					curr1.next.prev=curr1.prev;
					curr1.out = true;

					curr2.prev.next=curr2.next;
					curr2.next.prev=curr2.prev;
					curr2.out = true;
					
					size-=2;
					if (size==0) break;

				} else {
					sb.append(String.format("%3d",curr1.id));
					curr1.prev.next=curr1.next;
					curr1.next.prev=curr1.prev;
					curr1.out = true;
					size--;
				}
				if (size==0) break;
				while (curr1.out) curr1 = curr1.next;
				while (curr2.out) curr2 = curr2.prev;

				sb.append(',');
			}
			System.out.println(sb.toString());
		}
	}

}