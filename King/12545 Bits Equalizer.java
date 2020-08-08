import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static int noOfChars(char [] ch, char find) {
		int count=0;
		for (char c : ch) if (c==find) count++;
		return count;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			char [] S=br.readLine().toCharArray();
			int S0=noOfChars(S,'0'), S1=noOfChars(S,'1'), SQ=noOfChars(S,'?');

			char [] T=br.readLine().toCharArray();
			int T0=noOfChars(T,'0'), T1=noOfChars(T,'1');

			int moves=0;
			if (T1>S1) {
				int changeTo1=Math.min(SQ,T1-S1);
				SQ-=changeTo1;
				S1+=changeTo1;
				moves+=changeTo1;
				for (int i=0;i<S.length && changeTo1>0;i++) if (S[i]=='?' && T[i]=='1') {
					S[i]='1';
					changeTo1--;
				}
				for (int i=0;i<S.length && changeTo1>0;i++) if (S[i]=='?') {
					S[i]='1';
					changeTo1--;
				}
			}
			for (int i=0;i<S.length;i++) if (S[i]=='?') {
				S[i]='0';
				S0++;
				moves++;
			}
			if (T1>S1) {
				for (int i=0;i<S.length && T1>S1;i++) if (S[i]=='0' && T[i]=='1') {
					S[i]='1';
					S0--;
					S1++;
					moves++;
				}
			}
			if (T0==S0 && T1==S1) {
				int diff=0;
				for (int i=0;i<S.length;i++) if (S[i]!=T[i]) diff++;
				moves+=diff/2;
			} else moves=-1;
			
			System.out.printf("Case %d: %d\n", testCase, moves);
		}

	}

}