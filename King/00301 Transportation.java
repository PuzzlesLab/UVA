import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {
	
	private static class Order implements Comparable<Order> {
		int start, end, count, cost;
		public Order(int s, int e, int c) {
			this.start=s;
			this.end=e;
			this.count=c;
			this.cost=(e-s)*c;
		}
		public int compareTo(Order o) {
			int [] i1= {this.start, this.end, this.cost};
			int [] i2= {o.start, o.end, o.cost};
			for (int idx=0;idx<3;idx++) if (i1[idx]!=i2[idx]) return i1[idx]-i2[idx];
			return 0;
		}
	}
	
	private static int Ans=0;
	public static void recurse(Order [] orders, int currOrder, LinkedList<Order> ordersOnboard, int currStation, int earned, int remCount) {
		if (currOrder==orders.length) Ans=Math.max(earned,Ans);
		else {
			currStation=Math.max(currStation, orders[currOrder].start);
			
			Iterator<Order> it=ordersOnboard.iterator();
			while (it.hasNext()) {
				Order o=it.next();
				if (o.end<=currStation) {
					remCount+=o.count;
					it.remove();
				}
			}

			if (remCount >= orders[currOrder].count) {
				LinkedList<Order> dup=new LinkedList<>();
				dup.addAll(ordersOnboard);
				dup.add(orders[currOrder]);
				recurse(orders, currOrder+1, dup, currStation, earned+orders[currOrder].cost, remCount-orders[currOrder].count);
			}
			recurse(orders, currOrder+1, ordersOnboard, currStation, earned, remCount);
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			Order [] orders=new Order[T];
			for (int t=0;t<T;t++) {
				st=new StringTokenizer(br.readLine());
				Order o=new Order(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				orders[t]=o;
			}
			Arrays.sort(orders);
			
			Ans=0;
			recurse(orders, 0, new LinkedList<>(), 0, 0, N);
			System.out.println(Ans);
		}
	}

}