import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			String stdout=br.readLine();
			String correct=br.readLine();

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<stdout.length();i++) if (stdout.charAt(i)!=' ') sb.append(stdout.charAt(i));
			String stdoutNoSpace=sb.toString();
			
			if (stdout.equals(correct)) System.out.printf("Case %d: Yes\n", testCase);
			else if (stdoutNoSpace.equals(correct)) System.out.printf("Case %d: Output Format Error\n", testCase);
			else System.out.printf("Case %d: Wrong Answer\n", testCase);
		}
	}

}
