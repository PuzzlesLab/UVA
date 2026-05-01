import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static class Ticket {
		int id;
		int [] cities;
		int cost;
		
		public Ticket(int id, int c) {
			this.id=id;
			this.cost=c;
		}
	}

	private static HashMap<Integer,ArrayList<Ticket>> AdjList;

	private static class State implements Comparable<State> {
		int seqIdx,n,cost;
		int ticket;
		State parent;
		
		public State(int si, int n, int cost, int ticket, State p) {
			this.seqIdx=si;
			this.n=n;
			this.cost=cost;
			this.ticket=ticket;
			this.parent=p;
		}

		private int minCostKey() {
			return this.seqIdx*100000+this.n;
		}

		public int compareTo(State s) {
			return this.cost-s.cost;
		}
	}

	private static State compute(int [] seq) {
		if (seq.length==0) return new State(0,-1,0,-1,null);

		HashMap<Integer,Integer> minCost=new HashMap<>();

		PriorityQueue<State> q=new PriorityQueue<>();
		State init=new State(0,seq[0],0,-1,null);
		q.offer(init);
		minCost.put(init.minCostKey(),0);
		while (!q.isEmpty()) {
			State curr=q.poll();
			if (curr.seqIdx==seq.length-1) return curr;

			if (!AdjList.containsKey(curr.n)) continue;
			ArrayList<Ticket> tickets=AdjList.get(curr.n);

			for (int i=0;i<tickets.size();i++) {
				Ticket t=tickets.get(i);

				int nextSeqIdx=curr.seqIdx;
				for (int n=1;n<t.cities.length;n++) {
					if (nextSeqIdx+1<seq.length && t.cities[n]==seq[nextSeqIdx+1]) nextSeqIdx++;
					State nE=new State(nextSeqIdx,t.cities[n],curr.cost+t.cost,t.id,curr);

					if (minCost.getOrDefault(nE.minCostKey(),Integer.MAX_VALUE)>nE.cost) {
						minCost.put(nE.minCostKey(),nE.cost);
						q.offer(nE);
					}
				}
			}
		}
		
		return init;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int NT=Integer.parseInt(s);

			Ticket [] tickets=new Ticket [NT];
			for (int i=0;i<NT;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Ticket t=new Ticket(i+1,Integer.parseInt(st.nextToken()));
				t.cities=new int [Integer.parseInt(st.nextToken())];
				for (int n=0;n<t.cities.length;n++) {
					t.cities[n]=Integer.parseInt(st.nextToken());
				}
				tickets[i]=t;
			}
			
			AdjList=new HashMap<>();
			for (int i=0;i<tickets.length;i++) {
				int startId=tickets[i].cities[0];
				if (!AdjList.containsKey(startId)) AdjList.put(startId,new ArrayList<>());
				AdjList.get(startId).add(tickets[i]);
			}

			int NI=Integer.parseInt(br.readLine());
			for (int i=1;i<=NI;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int N=Integer.parseInt(st.nextToken());
				int [] seq=new int [N];
				for (int n=0;n<N;n++) seq[n]=Integer.parseInt(st.nextToken());

				State ans=compute(seq);

				StringBuilder sb=new StringBuilder();
				sb.append("Case ");
				sb.append(tc);
				sb.append(", Trip ");
				sb.append(i);
				sb.append(": Cost = ");
				sb.append(ans.cost);
				sb.append('\n');
				sb.append("  Tickets used:");
				Stack<Integer> stk=new Stack<>();
				while (ans.ticket!=-1) {
					stk.push(ans.ticket);
					ans=ans.parent;
				}
				while (!stk.isEmpty()) {
					sb.append(' ');
					sb.append(stk.pop());
				}
				System.out.println(sb);
			}
			tc++;
		}
	}

}