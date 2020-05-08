import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			long ln=0, ld=1, rn=1, rd=0, mn=ln+rn, md=ld+rd;
			char [] ch=br.readLine().toCharArray();
			for (char c : ch) {
				if (c=='L') {
					rn=mn;
					rd=md;
				} else if (c=='R') {
					ln=mn;
					ld=md;
				}
				mn=ln+rn;
				md=ld+rd;
			}
			System.out.printf("%d/%d\n", mn, md);
		}
	}

}