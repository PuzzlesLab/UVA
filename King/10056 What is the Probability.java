import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			double p=Double.parseDouble(st.nextToken());
			double q=1-p;
			int I=Integer.parseInt(st.nextToken());
			
			double ans=p*(1/(1-Math.pow(q, N)))*Math.pow(q, I-1);
			if (p==0) ans=0.0;
			System.out.printf("%.4f\n", ans);
		}
	}

}