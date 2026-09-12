import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int MAX_CD_TIME;
	private static int M;
	private static int [] Tracks;
	private static int [][][] Dp;
	private static boolean [][][] DpFlag;

	public static int compute(int cd, int cdRemT, int track) {
		if (cd==M || track==Tracks.length) return 0;
		
		if (!DpFlag[cd][cdRemT][track]) {
			int ans=compute(cd,cdRemT,track+1); // Ignore song
			if (cdRemT>=Tracks[track]) { // Include song in current CD
				ans=Math.max(ans,1+compute(cd,cdRemT-Tracks[track],track+1));
			}
			ans=Math.max(ans,compute(cd+1,MAX_CD_TIME,track)); // Give track to next CD.
			DpFlag[cd][cdRemT][track]=true;
			Dp[cd][cdRemT][track]=ans;
		}
		return Dp[cd][cdRemT][track];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	br.readLine();
        	
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	int N=Integer.parseInt(st.nextToken());
        	MAX_CD_TIME=Integer.parseInt(st.nextToken());
        	M=Integer.parseInt(st.nextToken());
        	
        	Tracks=new int [N];
        	st=new StringTokenizer(br.readLine());
        	for (int n=0;n<N;n++) {
        		String s=st.nextToken();
        		if (n<N-1) s=s.substring(0,s.length()-1);
        		Tracks[n]=Integer.parseInt(s);
        	}

        	if (tc>0) System.out.println();
        	DpFlag=new boolean [M][MAX_CD_TIME+1][N];
        	Dp=new int [M][MAX_CD_TIME+1][N];
        	System.out.println(compute(0,MAX_CD_TIME,0));
        }
	}

}