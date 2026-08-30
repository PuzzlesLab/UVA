import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static class LongLong {
		public static final LongLong ZERO=new LongLong(0L);
		public static final LongLong ONE=new LongLong(1L);
		private long l, r;
		
		public LongLong(long init) {
			this.l=0;
			this.r=init;
		}
		
		public LongLong shiftLeft(int n) {
			LongLong nll=LongLong.ZERO;
			nll.l=(this.l<<n)|this.r>>(64-n);
			nll.r=this.r<<n;
			return nll;
		}
		
		public LongLong or(LongLong ll) {
			LongLong nll=LongLong.ZERO;
			nll.l=this.l|ll.l;
			nll.r=this.r|ll.r;
			return nll;
		}
		
		public LongLong and(LongLong ll) {
			LongLong nll=LongLong.ZERO;
			nll.l=this.l&ll.l;
			nll.r=this.r&ll.r;
			return nll;
		}

		public boolean hasBit() {
			return this.l!=0 || this.r!=0;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine();
			int N=Integer.parseInt(br.readLine());
			int [] weights=new int [N];
			for (int n=0;n<N;n++) weights[n]=Integer.parseInt(br.readLine());

			int total=0;
			for (int n=0;n<N;n++) total+=weights[n];
			int halfW=total>>1;
			LongLong [] dp=new LongLong [halfW+1];
			for (int i=0;i<dp.length;i++) dp[i]=LongLong.ZERO;
			dp[0]=LongLong.ONE;

			int halfN=N>>1;
			// DP = 2^X value, where X bit = exists X people combi to form the given value.
			for (int n=0;n<N;n++) for (int w=halfW;w>=weights[n];w--) dp[w]=dp[w].or(dp[w-weights[n]].shiftLeft(1));
			int ans=-1;
			for (int w=halfW;w>=0&&ans==-1;w--) {
				if (dp[w].and(LongLong.ONE.shiftLeft(halfN)).hasBit()) ans=w;
				else if ((N&1)==1 && dp[w].and(LongLong.ONE.shiftLeft(halfN+1)).hasBit()) ans=w;
			}

			if (tc>0) System.out.println();
			System.out.printf("%d %d\n",ans,total-ans);
		}
	}

}