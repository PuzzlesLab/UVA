import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class PowerSource implements Comparable<PowerSource> {
		int count=0;
		long rating=0;
		
		public PowerSource(int c, int r) {
			this.count=c;
			this.rating=r;
		}

		public int compareTo(PowerSource ps) {
			if (this.rating>ps.rating) return 1;
			else if (this.rating==ps.rating) return 0;
			return -1;
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			PowerSource [] ps=new PowerSource [N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				ps[n]=new PowerSource(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(ps);
			
			int [] sourceAboveCount=new int [N]; //DP
			for (int i=0;i<N;i++) {
				if (i>0) sourceAboveCount[i]=sourceAboveCount[i-1];
				sourceAboveCount[i]+=ps[i].count;
			}
			
			long maxPower=Long.MIN_VALUE;
			for (int i=0;i<N;i++) {
				ps[i].count+=sourceAboveCount[N-1]-sourceAboveCount[i];
				maxPower=Math.max(maxPower, ps[i].count*ps[i].rating);
			}
			System.out.printf("Case %d: %d\n", testCase, maxPower);
		}
	}
}