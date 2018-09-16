import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static boolean containsSameDigit(long v) {
		boolean [] flag=new boolean [10];
		while (v>0) {
			int digit=(int)(v%10);
			if (flag[digit]) return true;
			flag[digit]=true;
			v/=10;
		}
		return false;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); //empty!
			long N=Long.parseLong(br.readLine().trim());
			StringBuilder sb=new StringBuilder();
			for (int i=1;true;i++) {
				long result=N*i;
				if (Math.log10(result)+1>11) break;
				if (!containsSameDigit(i) && !containsSameDigit(result)) {
					sb.append(result);
					sb.append(" / ");
					sb.append(i);
					sb.append(" = ");
					sb.append(N);
					sb.append('\n');
				}
			}
			if (testCase<testCaseCount-1) sb.append('\n');
			System.out.print(sb.toString());
		}
	}

}