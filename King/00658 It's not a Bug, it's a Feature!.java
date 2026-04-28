import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	private static class Patch {
		int time,bugPresent,bugAbsent,bugFix,newBug;
		
		public Patch(int t) {
			this.time=t;
			this.bugPresent=0;
			this.bugAbsent=0;
			this.bugFix=0;
			this.newBug=0;
		}
	}

	private static class State implements Comparable<State> {
		int time,bug;

		public State(int t, int b) {
			this.time=t;
			this.bug=b;
		}
		
		public int compareTo(State s) {
			return this.time-s.time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			Patch [] patches=new Patch[M];

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				patches[m]=new Patch(Integer.parseInt(st.nextToken()));
				
				s=st.nextToken();
				for (int i=0;i<s.length();i++) {
					char c=s.charAt(i);
					if (c=='+') patches[m].bugPresent|=1<<i;
					else if (c=='-') patches[m].bugAbsent|=1<<i;
				}
				
				s=st.nextToken();
				for (int i=0;i<s.length();i++) {
					char c=s.charAt(i);
					if (c=='+') patches[m].newBug|=1<<i;
					else if (c=='-') patches[m].bugFix|=1<<i;
				}
			}
			
			final int NULL_SP=100000000;
			int [] sp=new int [1<<N];
			Arrays.fill(sp,NULL_SP);
			State init=new State(0,(1<<N)-1);
			PriorityQueue<State> q=new PriorityQueue<>();
			q.offer(init);
			while (!q.isEmpty()) {
				State curr=q.poll();
				if (curr.bug==0) break;
				
				for (int m=0;m<M;m++) {
					if ((curr.bug&patches[m].bugPresent)!=patches[m].bugPresent) continue;
					if ((~curr.bug&patches[m].bugAbsent)!=patches[m].bugAbsent) continue;
					
					int nextBug=curr.bug;
					nextBug&=~patches[m].bugFix;
					nextBug|=patches[m].newBug;
					
					int nT=curr.time+patches[m].time;
					if (sp[nextBug]>nT) {
						sp[nextBug]=nT;
						q.offer(new State(nT,nextBug));
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Product ");
			sb.append(tc++);
			sb.append('\n');
			if (sp[0]!=NULL_SP) {
				sb.append("Fastest sequence takes ");
				sb.append(sp[0]);
				sb.append(" seconds");
			} else sb.append("Bugs cannot be fixed");
			sb.append(".\n");
			System.out.println(sb);
		}
	}

}