import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Nums;
	private static int [][] Dp;

	private static class FasterScanner {
		private BufferedReader br;
		private StringTokenizer st;
		
		public FasterScanner() {
			this.br=new BufferedReader(new InputStreamReader(System.in));
			this.st=new StringTokenizer("");
		}

		private String next() throws IOException {
			if (!this.st.hasMoreTokens()) {
				while (true) {
					String s=this.br.readLine();
					if (s==null) return s;
					this.st=new StringTokenizer(s);
					if (this.st.countTokens()>0) break;
				}
			}
			return this.st.nextToken();
		}
	}

	private static int play(int p, int stones) {
		if (stones==1) return p;
		
		if (Dp[p][stones]==-1) {
			int p2=p^1;
			int winner=p2;
			for (int m=0;m<Nums.length;m++) if (Nums[m]<=stones) {
				if (play(p2,stones-Nums[m])==p) {
					winner=p;
					break;
				}
			}
			Dp[p][stones]=winner;
		}
		return Dp[p][stones];
	}

	public static void main(String[] args) throws Exception {
		FasterScanner sc=new FasterScanner(); // Up to 40% faster.
		while (true) {
			String s=sc.next();
			if (s==null) break;

			int N=Integer.parseInt(s);
			int M=Integer.parseInt(sc.next());
			Nums=new int[M];
			for (int m=0;m<M;m++) Nums[m]=Integer.parseInt(sc.next());

			Dp=new int [2][N+1];
			Arrays.fill(Dp[0],-1);
			Arrays.fill(Dp[1],-1);
			for (int i=0;i<=N;i++) {
				play(0,i);
				play(1,i);
			}

			int winner=play(0,N);
			System.out.println(winner==0 ? "Stan wins" : "Ollie wins");
		}
	}

}
