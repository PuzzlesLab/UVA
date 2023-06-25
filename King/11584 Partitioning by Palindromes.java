import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static int [] Dp;

	private static boolean isPalindrome(char [] ch, int start, int end) {
		while (start<end) {
			if (ch[start]!=ch[end]) return false;
			start++;
			end--;
		}
		return true;
	}

	private static int find(char [] ch, int start) {
		if (start>=ch.length) return 0;
		if (Dp[start]!=-1) return Dp[start];

		int value=ch.length;
		for (int end=ch.length-1;end>=start;end--) {
			boolean flag=isPalindrome(ch,start,end);
			if (flag) value=Math.min(value,1+find(ch,end+1));
		}
		Dp[start]=value;
		return value;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			char [] ch=br.readLine().toCharArray();
			
			Dp=new int [ch.length];
			Arrays.fill(Dp,-1);
			int count=find(ch,0);
			System.out.println(count);
		}
	}

}
