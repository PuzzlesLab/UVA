import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static int findFactorLargerThan(int num, int large) {
		for (int i=large+1;true;i++) if (num%i==0) return i;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int num=Integer.parseInt(br.readLine());
			int f1=findFactorLargerThan(num,1);
			int f2=findFactorLargerThan(num,f1);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase);
			sb.append(": ");
			sb.append(num);
			sb.append(" = ");
			sb.append(f1);
			sb.append(" * ");
			sb.append(num/f1);
			sb.append(" = ");
			sb.append(f2);
			sb.append(" * ");
			sb.append(num/f2);
			
			System.out.println(sb.toString());
			
		}
	}

}