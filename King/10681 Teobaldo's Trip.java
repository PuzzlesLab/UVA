import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static int C;
	private static int L;
	private static boolean [][] AdjMat;
	private static int E;
	private static int D;
	private static int [][] Dp;
	
	private static boolean find(int at, int day) {
		if (day==D) return at==E;

		if (Dp[at][day]==0) {
			boolean ans=false;
			for (int next=0;next<C;next++) if (at!=next && AdjMat[at][next]) {
				ans|=find(next,day+1);
			}
			Dp[at][day]=ans?1:2;
		}
		return Dp[at][day]==1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			C=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			AdjMat=new boolean [C][C];
			for (int l=0;l<L;l++) {
				st=new StringTokenizer(br.readLine());
				int A=Integer.parseInt(st.nextToken())-1;
				int B=Integer.parseInt(st.nextToken())-1;
				AdjMat[A][B]=true;
				AdjMat[B][A]=true;
			}
			
			st=new StringTokenizer(br.readLine());
			int S=Integer.parseInt(st.nextToken())-1;
			E=Integer.parseInt(st.nextToken())-1;
			D=Integer.parseInt(st.nextToken());
			
			Dp=new int[C][D+1];
			boolean ans=find(S,0);
			System.out.println(ans?"Yes, Teobaldo can travel.":"No, Teobaldo can not travel.");
			
			br.readLine(); //empty.
		}
	}

}
