import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Call {
		int start, dur, end;
		
		public Call(int start, int dur) {
			this.start=start;
			this.dur=dur;
			this.end=this.start+dur;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		StringBuilder sb=new StringBuilder();
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ArrayList<Call> calls=new ArrayList<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				Integer.parseInt(st.nextToken()); //useless
				Integer.parseInt(st.nextToken()); //useless
				Call c=new Call(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
				if (c.dur>0) calls.add(c);
			}
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int start=Integer.parseInt(st.nextToken());
				int end=start+Integer.parseInt(st.nextToken());
				
				int count=0;
				if (end-start>0) {
					for (Call call : calls) {
						if ((call.start>=start && call.end<=end) || 
								(call.start<start && call.end>start) || 
								(call.start<end && call.end>end)) count++;
					}
				}
				
				sb.append(count);
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}
