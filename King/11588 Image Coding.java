import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int R=Integer.parseInt(st.nextToken()), C=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken()), N=Integer.parseInt(st.nextToken());
			
			int max=Integer.MIN_VALUE;
			int [] count=new int [26];
			for (int r=0;r<R;r++) for (char c : br.readLine().toCharArray()) max=Math.max(++count[c-'A'], max);

			int ans=0;
			for (int i=0;i<count.length;i++) ans+=(count[i]==max) ? count[i]*M : count[i]*N;
			
			System.out.printf("Case %d: %d\n", testCase, ans);
		}
	}

}
