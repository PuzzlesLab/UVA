import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger [] fac=new BigInteger [21];
		fac[0]=BigInteger.ONE;
		for (int i=1;i<fac.length;i++) fac[i]=fac[i-1].multiply(BigInteger.valueOf(i));

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			char [] input=st.nextToken().toCharArray();
			ArrayList<Character> ch=new ArrayList<>();
			for (int i=0;i<input.length;i++) ch.add(input[i]);
			Collections.sort(ch);

			BigInteger n=new BigInteger(st.nextToken());

			char [] ans=new char [input.length];
			ArrayList<Character> temp=new ArrayList<>(ch);
			for (int i=0;i<input.length;i++) {
				int pos=0;
				while (n.compareTo(fac[input.length-i-1])>0) {
					n=n.subtract(fac[input.length-i-1]);
					pos++;
				}
				ans[i]=temp.remove(pos);
			}

			StringBuilder sb=new StringBuilder("Case ");
			sb.append(tc);
			sb.append(": ");
			for (int i=0;i<input.length;i++) for (int i2=0;i2<input.length;i2++) {
				if (ans[i2]==ch.get(i)) sb.append(input[i2]);
			}
			System.out.println(sb);
		}
	}

}