import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			BigInteger A=new BigInteger(st.nextToken());
			BigInteger B=new BigInteger(st.nextToken());
			BigInteger N=new BigInteger(st.nextToken());

			HashMap<Integer,BigInteger> Q=new HashMap<>();
			Q.put(0,A);
			Q.put(1,B);
			Q.put(2,B.add(BigInteger.ONE).divide(A));
			Q.put(3,A.add(B).add(BigInteger.ONE).divide(A).divide(B));
			Q.put(4,A.add(BigInteger.ONE).divide(B));

			int idx=N.mod(BigInteger.valueOf(Q.size())).intValue();
			System.out.println(Q.get(idx));
		}
	}

}
