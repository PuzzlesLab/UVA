import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static boolean possible(int K, int M) {
		int twoK=2*K;
		int curr=(M-1)%twoK;
		for (int k=0;k<K;k++) {
			if (curr<K) return false;
			twoK--;
			curr=(curr+M-1)%twoK;
		}
		return true;
	}

	public static void main (String [] args) throws Exception {
		int [] dp=new int [15];
		Arrays.fill(dp,-1);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int K=Integer.parseInt(s);
			if (dp[K]==-1) {
				int M=1;
				while (true) {
					if (possible(K,M)) break;
					M++;
				}
				dp[K]=M;
			}
			System.out.println(dp[K]);
		}
	}

}
