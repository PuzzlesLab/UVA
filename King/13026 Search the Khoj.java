package uva;

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
			
			String [] phoneS=new String[N];
			char [][] phone=new char[N][];
			for (int n=0;n<N;n++) {
				phoneS[n]=br.readLine();
				phone[n]=phoneS[n].toCharArray();
			}
			char [] recall=br.readLine().toCharArray();
			for (int n=0;n<N;n++) {
				int diff=0;
				if (phone[n].length == recall.length) {
					for (int i=0;i<recall.length;i++) if (phone[n][i]!=recall[i]) diff++;
					if (diff<=1) {
						sb.append(phoneS[n]);
						sb.append("\n");
					}
				}
			}
			System.out.print(sb.toString());
		}
	}
}