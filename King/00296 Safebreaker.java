import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static class Guess {
		char [] nums;
		int correctDigits, correctDigitsWrong;
		int [] digitsCount;
		
		public Guess (String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.nums=st.nextToken().toCharArray();
			String [] d=st.nextToken().split("/");
			this.correctDigits=Integer.parseInt(d[0]);
			this.correctDigitsWrong=Integer.parseInt(d[1]);
			
			this.digitsCount=chCount(this.nums);
		}
		
		public boolean match (char [] ch) {
			int [] dc=Arrays.copyOf(this.digitsCount, this.digitsCount.length);
			
			int cd=0, cdw=0;
			for (int i=0;i<4;i++) if (this.nums[i]==ch[i]) {
				cd++;
				dc[ch[i]]--;
			}
			
			for (int i=0;i<4;i++) if (this.nums[i]!=ch[i] && dc[ch[i]]>0) {
				cdw++;
				dc[ch[i]]--;
			}
			
			return cd==this.correctDigits && cdw==this.correctDigitsWrong;
		}
	}
	
	public static char [] numToCh(int n) {
		char [] ch= {'0','0','0','0'};
		for (int i=3;i>=0 && n>0;i--) {
			ch[i]=(char)('0'+n%10);
			n/=10;
		}
		return ch;
	}
	
	public static int [] chCount(char [] ch) {
		int [] dc=new int [128];
		for (char c : ch) dc[c]++;
		return dc;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			Guess [] guesses=new Guess[Integer.parseInt(br.readLine())];
			for (int i=0;i<guesses.length;i++) guesses[i]=new Guess(br.readLine());
			
			ArrayList<String> matchNums=new ArrayList<>();
			for (int i=0;i<10000;i++) {
				char [] ch=numToCh(i);
				boolean allMatches=true;
				for (Guess guess : guesses) allMatches&=guess.match(ch);
				if (allMatches) matchNums.add(new String(ch));
				if (matchNums.size()>1) break;
			}
			
			int size=matchNums.size();
			if (size==0) System.out.println("impossible");
			else if (size==1) System.out.println(matchNums.get(0));
			else System.out.println("indeterminate");
		}
	}

}