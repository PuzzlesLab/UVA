import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); // empty line
			int N=Integer.parseInt(br.readLine());
			
			int [] ans=new int [N/2];
			int ansCount=0;

			int sum=0;
			for (int i=2;sum<N;i++) {
				if (sum+i>N) break;
				sum+=i;
				ans[ansCount++]=i;
			}
			
			int needed=N-sum;
			while (needed>0) {
				for (int i=ansCount-1;i>=0 && needed>0;i--) {
					ans[i]++;
					needed--;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			if (testCase>0) sb.append('\n');
			for (int i=0;i<ansCount;i++) {
				sb.append(ans[i]);
				sb.append(' ');
			}
			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb);
		}
	}

}
