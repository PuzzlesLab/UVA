import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int count=Integer.parseInt(br.readLine());
			int [] prices=new int [count];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int i=0;i<prices.length;i++) prices[i]=Integer.parseInt(st.nextToken());
			
			Arrays.sort(prices);
			int ans=0;
			for (int i=prices.length-3;i>=0;i-=3)ans+=prices[i];
			System.out.println(ans);
		}
	}

}
