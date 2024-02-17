import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static int getNumW(BigInteger n) {
		return n.toString().length();
	}
	
	private static void fillNum(StringBuilder sb, BigInteger n, int rhs, int w) {
		int nw=getNumW(n);
		for (int i=nw+rhs;i<w;i++) sb.append(' ');
		sb.append(n);
		sb.append('\n');
	}
	
	private static void fillLine(StringBuilder sb, int n, int w) {
		for (int i=0;i<w-n;i++) sb.append(' ');
		for (int i=w-n;i<w;i++) sb.append('-');
		sb.append('\n');
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			if (st.countTokens()==1) break;
			
			BigInteger n1=new BigInteger(st.nextToken());
			BigInteger n2=new BigInteger(st.nextToken());
			
			ArrayList<BigInteger> nums=new ArrayList<>();
			BigInteger tempN=n2;
			while (tempN.compareTo(BigInteger.ZERO)>0) {
				BigInteger prod=tempN.remainder(BigInteger.TEN).multiply(n1);
				nums.add(prod);
				tempN=tempN.divide(BigInteger.TEN);
			}
			BigInteger result=n1.multiply(n2);

			int W=Math.max(getNumW(n1),Math.max(getNumW(n2),getNumW(result)));

			StringBuilder sb=new StringBuilder();
			fillNum(sb,n1,0,W);
			fillNum(sb,n2,0,W);
			fillLine(sb,Math.max(getNumW(n1),getNumW(n2)),W);
			int non0Count=0;
			for (int i=0;i<nums.size();i++) if (!nums.get(i).equals(BigInteger.ZERO)) non0Count++;
			if (non0Count>1) {
				for (int i=0;i<nums.size();i++) {
					BigInteger n=nums.get(i);
					if (n.equals(BigInteger.ZERO)) continue;
					fillNum(sb,nums.get(i),i,W);
				}
				fillLine(sb,W,W);
			}
			fillNum(sb,result,0,W);
			System.out.println(sb.toString());
		}
	}

}
