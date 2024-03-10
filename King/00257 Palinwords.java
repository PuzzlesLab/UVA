import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static boolean isPalindrome(String text, int s, int e) {
		int mid=(e+s)/2;
		for (int i=s;i<=mid;i++) if (text.charAt(i)!=text.charAt(e-1-(i-s))) return false;
		return true;
	}

	private static boolean check(String text) {
		HashSet<String> found=new HashSet<>();
		for (int s=0;s<text.length();s++) {
			for (int e=s+3;e<=s+4 && e<=text.length();e++) {
				if (isPalindrome(text,s,e)) {
					found.add(text.substring(s,e));
					break;
				}
			}
			if (found.size()>=2) return true;
		}
		return false;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			while (st.hasMoreTokens()) {
				String text=st.nextToken();
				if (check(text)) {
					System.out.println(text);
				}
			}
		}
	}
}