import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

class Main {
	
	private static int match(String a, String b) {
		for (int i=0;i<Math.min(a.length(),b.length());i++) if (a.charAt(i)!=b.charAt(i)) return i;
		return Math.min(a.length(), b.length());
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			LinkedList<String> str=new LinkedList<>();
			for (int n=0;n<N;n++) str.add(br.readLine());

			int ans=0;
			StringBuilder sb=new StringBuilder();
			String curr=str.removeFirst();
			sb.append(curr);
			sb.append('\n');
			ans+=curr.length();
			while (!str.isEmpty()) {
				final String test=curr;
				Collections.sort(str, (String a, String b) -> match(test,a)-match(test,b));
				curr=str.removeLast();
				ans+=curr.length()-match(test,curr);
				sb.append(curr);
				sb.append('\n');
			}
			System.out.println(ans);
			System.out.print(sb.toString());
		}
	}

}