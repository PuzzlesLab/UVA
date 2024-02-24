import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static String fill(int len, char c) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<len;i++) sb.append(c);
		return sb.toString();
	}

	private static String pad(int left, String s) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<left;i++) sb.append(' ');
		sb.append(s);
		return sb.toString();
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			String xStr=st.nextToken();
			String yStr=st.nextToken();
			BigInteger x=new BigInteger(xStr);
			BigInteger y=new BigInteger(yStr);

			int xLen=xStr.length();
			int yLen=yStr.length();
			ArrayList<BigInteger> digits=new ArrayList<>();
			BigInteger tempY=y;
			while (!tempY.equals(BigInteger.ZERO)) {
				digits.add(tempY.remainder(BigInteger.TEN));
				tempY=tempY.divide(BigInteger.TEN);
			}
			if (digits.isEmpty()) digits.add(BigInteger.ZERO);

			ArrayList<String> lines=new ArrayList<>();
			for (int i=0;i<digits.size();i++) {
				BigInteger d=digits.get(i);
				if (d.equals(BigInteger.ZERO)) lines.add(fill(xLen,'0'));
				else lines.add(String.valueOf(d.multiply(x)));
			}

			String ans=new BigInteger(xStr,2).multiply(new BigInteger(yStr,2)).toString(2);
			int width=ans.length();
			
			StringBuilder sb=new StringBuilder();
			if (testCase++>1) sb.append('\n');
			sb.append(pad(width-xLen,xStr));
			sb.append('\n');
			sb.append(pad(width-yLen,yStr));
			sb.append('\n');
			int l1w=Math.max(xLen,yLen);
			sb.append(pad(width-l1w,fill(l1w,'-')));
			sb.append('\n');
			for (int i=0;i<lines.size();i++) {
				String line=lines.get(i);
				sb.append(pad(width-line.length()-i,line));
				sb.append('\n');
			}
			sb.append(fill(width,'-'));
			sb.append('\n');
			sb.append(ans);

			System.out.println(sb);
		}
	}
}
