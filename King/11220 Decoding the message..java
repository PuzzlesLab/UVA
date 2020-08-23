import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); //empty
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase);
			sb.append(":\n");
			
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				int currIdx=0;
				StringTokenizer st=new StringTokenizer(s);
				while (st.hasMoreTokens()) {
					String word=st.nextToken();
					if (word.length()>currIdx) {
						sb.append(word.charAt(currIdx));
						currIdx++;
					}
				}
				sb.append("\n");
			}
			
			if (testCase>1) System.out.println();
			System.out.print(sb.toString());
		}
	}
}