import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static class Drug implements Comparable<Drug> {
		String name;
		int freq;
		int moment;
		int priority;
		
		public Drug(StringTokenizer st, int p) {
			this.name=st.nextToken();
			this.freq=Integer.parseInt(st.nextToken());
			this.priority=p;
			this.moment=this.freq;
		}
		
		public int compareTo(Drug d) {
			if (this.moment!=d.moment) return this.moment-d.moment;
			return this.priority-d.priority;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			PriorityQueue<Drug> q=new PriorityQueue<>();
			for (int n=0;n<N;n++) q.offer(new Drug(new StringTokenizer(br.readLine()),n));
			
			StringBuilder sb=new StringBuilder();
			for (int k=0;k<K;k++) {
				Drug d=q.poll();
				sb.append(d.moment);
				sb.append(' ');
				sb.append(d.name);
				sb.append('\n');
				d.moment+=d.freq;
				q.offer(d);
			}
			System.out.print(sb.toString());
		}
	}
}
