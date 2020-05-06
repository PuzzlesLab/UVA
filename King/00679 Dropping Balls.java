import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int testCaseCount=Integer.parseInt(br.readLine());
			if (testCaseCount==-1) break;
			
			for (int testCase=0;testCase<testCaseCount;testCase++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int D=Integer.parseInt(st.nextToken());
				int I=Integer.parseInt(st.nextToken())-1;
				
				int ans=1;
				int factor=2;
				for (int d=1;d<D;d++) {
					ans = ans << 1;
					if (I%factor>=factor>>1) ans+=1;
					factor=factor << 1;
				}
				System.out.println(ans);
			}
		}

	}

}