import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static int reverse(int n, int padding) {
		int reverse=0;
		while (padding-->0) {
			reverse = (reverse << 1) + (n & 1);
			n>>=1;
		}
		return reverse;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		while (!(s=br.readLine()).equals("0")) {
			int num=Integer.parseInt(s), ans1=0, ans2=0, temp=0, pad=0;

			while (num!=0) {
				ans1 = ans1 << 1;
				ans2 = ans2 << 1;
				
				if ((num & 1) == 1) {
					if ((temp & 1) ==1) ans2+=1;
					else ans1+=1;
					temp++;
				}
				num>>= 1;
				pad++;
			}
			System.out.println(reverse(ans1, pad)+" "+reverse(ans2, pad));
		}
	}

}
