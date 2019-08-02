import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int I=Integer.parseInt(st.nextToken()); //deactivate time
			int K=Integer.parseInt(st.nextToken()); //activation time
			
			long lastOpTime=0;
			int active=0, ignored=0;
			for (int n=0;n<N;n++) {
				long x=Long.parseLong(br.readLine());
				if (x<lastOpTime) ignored++;
				else if (x>=lastOpTime+I) {
					lastOpTime=x+K;
					active++;
				} else lastOpTime = x;
			}
			System.out.printf("Case %d: %d %d\n", testCase, active, ignored);
		}
	}

}