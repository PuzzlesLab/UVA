import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		
		StringBuilder sb=new StringBuilder();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			int [] nums=new int [N];
			
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) nums[n]=Integer.parseInt(st.nextToken());
			
			int [] cache=new int [230]; // Lame question. Same value of a will be repeated many times.
			Arrays.fill(cache, -1);
			
			for (int q=0;q<Q;q++) {
				int a=Integer.parseInt(br.readLine());
				if (cache[a]==-1) {
					int max=0;
					for (int n=0;n<N;n++) max=Math.max(max, a & nums[n]);
					cache[a]=max;
				}
				sb.append(cache[a]);
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}
}