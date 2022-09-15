import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main { 

	private static int M;
	private static int N;
	private static int T;
	private static int [] Dp;
	private static int [] Next;

	public static int compute(int remT) {
		if (remT<M && remT<N) return remT;

		if (Dp[remT]==1000000) {
			int value=100000;
			if (remT>=M && remT>=N) {
				int v1=compute(remT-M);
				int v2=compute(remT-N);
				if (v1==v2) {
					value=v1;
					Next[remT]=Math.max(remT-M,remT-N); // Pick the shorter time (M,N) to maximize count
				} else if (v1<v2) {
					value=v1;
					Next[remT]=remT-M;
				} else {
					value=v2;
					Next[remT]=remT-N;
				}
			} else if (remT>=M) {
				value=compute(remT-M);
				Next[remT]=remT-M;
			} else if (remT>=N) {
				value=compute(remT-N);
				Next[remT]=remT-N;
			}
			Dp[remT]=value;
		}
		
		return Dp[remT];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			T=Integer.parseInt(st.nextToken());

			Dp=new int [T+1];
			Next=new int [T+1];
			Arrays.fill(Dp,1000000);
			Arrays.fill(Next,-1);
			int beer=compute(T);

			int burger=0;
			int temp=T;
			while (Next[temp]!=-1) {
				burger++;
				temp=Next[temp];
			}
			
			if (beer==0) System.out.println(burger);
			else System.out.printf("%d %d\n",burger,beer);
		}
	}

}
