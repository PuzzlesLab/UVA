import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Data implements Comparable<Data> {
		int value;
		int idx;
		
		public Data(int value, int idx) {
			this.value=value;
			this.idx=idx;
		}
		
		public int compareTo(Data d) {
			if (this.value!=d.value) return this.value-d.value;
			return this.idx-d.idx;
		}
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int K=Integer.parseInt(s);
			int [][] q=new int [K][];
			for (int k=0;k<K;k++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				q[k]=new int [K];
				for (int k2=0;k2<K;k2++) q[k][k2]=Integer.parseInt(st.nextToken());
				Arrays.sort(q[k]);
			}
			
			int [] mainQ= {0};

			for (int k=0;k<K;k++) {
				PriorityQueue<Data> newQ=new PriorityQueue<>();
				for (int n: mainQ) newQ.offer(new Data(n+q[k][0], 0));
				
				mainQ = new int [K];
				
				for (int ki=0;ki<K;ki++) {
					Data dat=newQ.poll();
					mainQ[ki]=dat.value;
					if (dat.idx<K-1) {
						dat.value=dat.value-q[k][dat.idx]+q[k][dat.idx+1];
						dat.idx++;
						newQ.offer(dat);
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			for (int n : mainQ) {
				sb.append(n);
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}
}