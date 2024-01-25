import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

class Main {

	public static void main(String[] args) throws Exception {
		BigInteger [] Perm=new BigInteger [22];
		Perm[0]=BigInteger.ONE;
		for (int i=1;i<Perm.length;i++) Perm[i]=Perm[i-1].multiply(BigInteger.valueOf(i));

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			char [] ch=br.readLine().toCharArray();
			Arrays.sort(ch);
			ArrayList<Character> cList=new ArrayList<>();
			for (int i=0;i<ch.length;i++) cList.add(ch[i]);

			StringBuilder sb=new StringBuilder();
			BigInteger N=new BigInteger(br.readLine());
			for (int i=ch.length-1;i>=0;i--) {
				int div=N.divide(Perm[i]).intValue();
				sb.append(cList.remove(div));
				N=N.mod(Perm[i]);
			}

			System.out.println(sb);
		}
	}

}
