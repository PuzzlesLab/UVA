import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static boolean isPalindrome(String text, int s, int e) {
		int mid=(e+s)/2;
		for (int i=s;i<=mid;i++) if (text.charAt(i)!=text.charAt(e-1-(i-s))) return false;
		return true;
	}

	private static boolean isAlindrome(String text) {
		for (int i=1;i<text.length();i++) if (isPalindrome(text,0,i) && isPalindrome(text,i,text.length())) return true;
		return false;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			String s=br.readLine();
			
			if (isAlindrome(s)) System.out.println("alindrome");
			else if (isPalindrome(s,0,s.length())) System.out.println("palindrome");
			else System.out.println("simple");
		}
	}
}