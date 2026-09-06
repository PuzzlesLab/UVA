import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		int tv, conn;
		
		public Tuple(int tv, int conn) {
			this.tv=tv;
			this.conn=conn;
		}
		
		public Tuple(Tuple t) {
			this.tv=t.tv;
			this.conn=t.conn;
		}
		
		public int compareTo(Tuple t) {
			if (this.tv!=t.tv) return this.tv-t.tv;
			return t.conn-this.conn;
		}
		
		public static Tuple max(Tuple t1, Tuple t2) {
			return t1.compareTo(t2)>0?t1:t2;
		}
	}

	private static int [] OSL;
	private static int [] TVL;
	private static int [] OSR;
	private static int [] TVR;
	private static Tuple [][] Dp;

	private static Tuple compute(int l, int r) {
		if (l>=OSL.length || r>=OSR.length) return new Tuple(0,0);
		
		if (Dp[l][r]==null) {
			Tuple ans=new Tuple(0,0);
			ans=Tuple.max(ans,compute(l+1,r));
			ans=Tuple.max(ans,compute(l,r+1));
			if (OSL[l]==OSR[r]) {
				Tuple t=compute(l+1,r+1);
				t.tv+=TVL[l]+TVR[r];
				t.conn++;
				ans=Tuple.max(ans,t);
			}
			Dp[l][r]=ans;
		}

		return new Tuple(Dp[l][r]);
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	HashMap<String,Integer> cityIdxMap=new HashMap<>();
        	int cityIdxMax=0;
        	
        	int N=Integer.parseInt(br.readLine());
        	OSL=new int [N];
        	TVL=new int [N];
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		st.nextToken(); // Useless
        		String s=st.nextToken();
        		if (!cityIdxMap.containsKey(s)) cityIdxMap.put(s,cityIdxMax++);
        		OSL[n]=cityIdxMap.get(s);
        		TVL[n]=Integer.parseInt(st.nextToken());
        	}

        	N=Integer.parseInt(br.readLine());
        	OSR=new int [N];
        	TVR=new int [N];
        	for (int n=0;n<N;n++) {
        		StringTokenizer st=new StringTokenizer(br.readLine());
        		st.nextToken(); // Useless
        		String s=st.nextToken();
        		if (!cityIdxMap.containsKey(s)) cityIdxMap.put(s,cityIdxMax++);
        		OSR[n]=cityIdxMap.get(s);
        		TVR[n]=Integer.parseInt(st.nextToken());
        	}

        	Dp=new Tuple[OSL.length][OSR.length];
        	Tuple ans=compute(0,0);
        	System.out.printf("%d %d\n",ans.tv,ans.conn);
        }
	}

}