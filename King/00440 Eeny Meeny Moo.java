import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static class Node {
		int id;
		Node prev;
		Node next;
	}

	private static boolean possible(int N, int M) {
		Node [] nodes=new Node[N];
		for (int n=0;n<N;n++) {
			nodes[n]=new Node();
			nodes[n].id=n+1;
		}
		for (int n=0;n<N;n++) {
			nodes[n].prev=nodes[Math.floorMod(n-1,N)];
			nodes[n].next=nodes[(n+1)%N];
		}
		Node curr=nodes[0];
		int size=N;
		while (size>0) {
			if (size!=N) for (int m=0;m<M;m++) curr=curr.next;
			curr.prev.next=curr.next;
			curr.next.prev=curr.prev;
			size--;
		}
		return curr.id==2; //Last city = 2.
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);

			int M=1;
			while (true) {
				if (possible(N,M)) break;
				M++;
			}
			System.out.println(M);
		}
	}

}