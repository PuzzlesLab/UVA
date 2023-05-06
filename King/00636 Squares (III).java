import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s=br.readLine();
			if (s.equals("0")) break;
			int [] digits=new int [s.length()];
			for (int i=0;i<s.length();i++) digits[i]=s.charAt(i)-'0';
			int minBase=0;
			for (int i=0;i<digits.length;i++) minBase=Math.max(minBase,digits[i]+1);
			
			for (int base=minBase;base<100;base++) {
				long value=0;
				long multi=1;
				for (int i=digits.length-1;i>=0;i--) {
					value+=digits[i]*multi;
					multi*=base;
				}

				long sqrt=(long)Math.sqrt(value);
				if (sqrt*sqrt==value) {
					System.out.println(base);
					break;
				}
			}
		}
	}
}
