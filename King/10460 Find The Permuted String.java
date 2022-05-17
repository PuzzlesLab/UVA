import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger [] fac=new BigInteger [28];
		fac[0]=BigInteger.ONE;
		for (int i=1;i<fac.length;i++) fac[i]=fac[i-1].multiply(BigInteger.valueOf(i));

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [] ch=br.readLine().toCharArray();
			BigInteger I=new BigInteger(br.readLine()).subtract(BigInteger.ONE);
			ArrayList<Character> ans=new ArrayList<>();
			
			BigInteger choices=fac[ch.length];
			for (int i=0;i<ch.length;i++) {
				choices=choices.divide(BigInteger.valueOf(i+1)); // Num of feasible position = N+1
				ans.add(I.divide(choices).intValue(),ch[i]);
				I=I.mod(choices);
			}

			StringBuilder sb=new StringBuilder();
			for (char c : ans) sb.append(c);
			System.out.println(sb.toString());
		}
	}

}
