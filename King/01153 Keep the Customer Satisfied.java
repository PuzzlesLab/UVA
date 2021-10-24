import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	private static class Order implements Comparable<Order> {
		public int q, d;
		
		public Order(int q, int d) {
			this.q = q;
			this.d = d;
		}
		
		public int compareTo(Order ord) {
			if (this.d != ord.d) return this.d-ord.d;
			return this.q-ord.q;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine();
			int N=Integer.parseInt(br.readLine());
			Order [] orders=new Order[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				orders[n]=new Order(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(orders);
			
			PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
			int completionTime=0;
			for (int n=0;n<N;n++) {
				Order curr=orders[n];
				pq.offer(curr.q);
				completionTime+=curr.q;
				if (completionTime>curr.d) {
					completionTime-=pq.poll();
				}
			}
			
			if (testCase>0) System.out.println();
			System.out.println(pq.size());
		}
	}

}
