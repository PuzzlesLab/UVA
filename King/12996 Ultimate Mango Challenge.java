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
			int L=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] mangoNum=new int [N];
			for (int n=0;n<N;n++) mangoNum[n]=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] mangoLimit=new int[N];
			for (int n=0;n<N;n++) mangoLimit[n]=Integer.parseInt(st.nextToken());
			
			int total=0;
			for (int n=0;n<N;n++) total+=mangoNum[n];
			boolean flag=total<=L;
			for (int n=0;n<N;n++) flag &= (mangoNum[n]<=mangoLimit[n]);
			
			System.out.printf("Case %d: %s\n", testCase, flag ? "Yes" : "No");
		}
	}
}