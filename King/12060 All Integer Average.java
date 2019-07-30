import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCaseCount=1;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int denominator=Integer.parseInt(st.nextToken());
			int numerator=0;
			for (int i=0;i<denominator;i++) numerator+=Integer.parseInt(st.nextToken());
			
			int gcd=BigInteger.valueOf(denominator).gcd(BigInteger.valueOf(numerator)).intValue();
			numerator/=gcd;
			denominator/=gcd;
			
			int front=numerator/denominator;
			numerator%=denominator;
			
			boolean negative=front<0 || numerator<0;
			front=Math.abs(front);
			numerator=Math.abs(numerator);
			
			StringBuilder sb=new StringBuilder("Case ");
			sb.append(testCaseCount++);
			sb.append(":\n");
			
			if (numerator>0) {
				int frontBlankSpaceCount=0;
				if (negative) frontBlankSpaceCount+=2;
				if (front!=0) {
					int digits=(int)Math.log10(front)+1;
					frontBlankSpaceCount+=digits;
				}

				int numeratorLength=(int)Math.log10(numerator)+1;
				int denominatorLength=(int)Math.log10(denominator)+1;
				
				for (int i=0;i<frontBlankSpaceCount;i++) sb.append(' ');
				for (int i=numeratorLength;i<denominatorLength;i++) sb.append(' ');
				sb.append(numerator);
				sb.append('\n');
				
				if (negative) sb.append("- ");
				if (front!=0) sb.append(front);
				for (int i=0;i<Math.max(numeratorLength, denominatorLength); i++) sb.append('-');
				sb.append('\n');
				
				for (int i=0;i<frontBlankSpaceCount;i++) sb.append(' ');
				for (int i=denominatorLength;i<numeratorLength;i++) sb.append(' ');
				sb.append(denominator);
				
			} else {
				if (negative) sb.append("- ");
				sb.append(front);
			}
			
			System.out.println(sb.toString());
		}
	}

}