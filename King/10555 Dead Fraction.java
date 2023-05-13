import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}
	
	private static int pow10(int pow) {
		int ans=1;
		for (int i=2;i<pow;i++) ans*=10;
		return ans;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			String numsStr=s.substring(2,s.length()-3);
			int nums=Integer.parseInt(numsStr);
			int numsLen=numsStr.length();

			int ansN=0;
			int ansD=nums>0 ? 1000000000 : 1;
			for (int digit=0;digit<numsLen;digit++) {
				// Base way : https://www.greenemath.com/Algebra2/5/RepeatingDecimaltoFractionLesson.html
				// We repeat the process for 9..9x, 9..90x, 9..900x.
				int currN=nums-nums/pow10(numsLen-digit);
				int currD=((pow10(numsLen)-1)/pow10(digit))*pow10(digit);

				int f=gcd(currN,currD);
				int tN=currN/f;
				int tD=currD/f;
				if (tD<ansD) {
					ansN=tN;
					ansD=tD;
				}
			}
			System.out.printf("%d/%d\n",ansN,ansD);
		}
	}

}
