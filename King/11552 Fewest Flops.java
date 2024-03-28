import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static String Line;
	private static int K;
	private static final int NULL=100000;
	private static int [][] Dp;
	private static HashMap<Character,Integer> [] ChCount;

	private static int find(int start, char lastC) {
		if (start==Line.length()) return 0;
		
		if (Dp[start][lastC]==NULL) {
			int ans=NULL-1;

			if (ChCount[start]==null) {
				ChCount[start]=new HashMap<>();
				for (int k=0;k<K;k++) {
					char c=Line.charAt(start+k);
					ChCount[start].put(c,ChCount[start].getOrDefault(c,0)+1);
				}
			}

			for (char startC: ChCount[start].keySet()) {
				int curr=ChCount[start].size()-(lastC==startC?1:0);
				for (char endC: ChCount[start].keySet()) {
					int chunks=curr;
					if (K>1 && startC==endC && ChCount[start].size()>1) chunks++; // Handles A...A case (+ 1 chunk)
					ans=Math.min(ans,chunks+find(start+K,endC));
				}
			}
			
			Dp[start][lastC]=ans;
		}
		return Dp[start][lastC];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			K=Integer.parseInt(st.nextToken());
			Line=st.nextToken();
			
			Dp=new int [Line.length()][128];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],NULL);
			ChCount=new HashMap [Line.length()];
			System.out.println(find(0,(char)0));
		}
	}
}