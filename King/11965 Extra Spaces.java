import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(":\n");
			
			for (int n=0;n<N;n++) {
				for (char c : br.readLine().toCharArray()) if (c!=' ' || sb.charAt(sb.length()-1)!=' ') sb.append(c);
				sb.append('\n');
			}
			
			if (testCase>1) System.out.println();
			System.out.print(sb.toString());
		}
	}
}