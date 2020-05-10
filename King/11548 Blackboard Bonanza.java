import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static String padString(String s, int left) {
		StringBuilder sb=new StringBuilder();
		while (left-->0) sb.append(' ');
		sb.append(s);
		return sb.toString();
	}
	
	public static int matchCount(String s1, String s2) {
		int count=0;
		for (int i=0;i<Math.min(s1.length(), s2.length());i++) if (s1.charAt(i)==s2.charAt(i)) count++;
		return count;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			String [] words=new String[N];
			for (int n=0;n<N;n++) words[n]=br.readLine();
			
			int ans=0;
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) {
				ans=Math.max(ans, matchCount(words[n], words[n2]));
				for (int nStart=1;nStart<words[n2].length();nStart++) ans=Math.max(ans, matchCount(padString(words[n], nStart), words[n2]));
				for (int n2Start=1;n2Start<words[n].length();n2Start++) ans=Math.max(ans, matchCount(words[n], padString(words[n2], n2Start)));
			}
			System.out.println(ans);
		}
	}

}