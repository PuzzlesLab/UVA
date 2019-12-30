import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static boolean all1s(String s) {
		for (char c : s.toCharArray()) if (c!='1') return false;
		return true;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			String A=st.nextToken(); st.nextToken();
			String B=st.nextToken(); st.nextToken();
			String C=st.nextToken();
			
			
			int ans=0;
			if (all1s(A) && all1s(B) && (A+B).equals(C)) ans=1;
			else {
				for (int i=2;i<=18;i++) {
					try {
						BigInteger biA=new BigInteger(A,i);
						BigInteger biB=new BigInteger(B,i);
						BigInteger biC=new BigInteger(C,i);
						if (biA.add(biB).equals(biC)) {
							ans=i;
							break;
						}
					} catch (Exception e) {}
				}
			}
			System.out.println(ans);
		}
	}

}