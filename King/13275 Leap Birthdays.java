import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static boolean isLeapYear(int year) {
		if (year%400==0) return true;
		else if (year%100 == 0) return false;
		else if (year%4 == 0) return true;
		return false;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int D=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int YS=Integer.parseInt(st.nextToken());
			int YE=Integer.parseInt(st.nextToken());
			
			int ans=0;
			if (D==29 && M==2) {
				for (int i=YS+1;i<=YE;i++) if (isLeapYear(i)) ans++;
			} else ans=(YE-YS);
			System.out.printf("Case %d: %d\n", testCase, ans);
		}
		
	}

}