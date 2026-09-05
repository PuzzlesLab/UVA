import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] Checks;
	private static int R;
	private static double V;
	private static double E;
	private static double F;
	private static double B;
	private static double [][] Dp;
	
	// Possible to do DP but will run into TLE for judge's test cases.
	// (10000x10000 is slow)
	private static double computeTimeBetween(int start, int end) {
		int delta=end-start;
		double t=0.0;
		for (int d=0;d<delta;d++) {
			int currDist=start+d;
			if (currDist>=R) t+=1.0/(V-E*(currDist-R));
			else t+=1.0/(V-F*(R-currDist));
		}
		return t;
	}

	private static double compute(int n, int dist) {
		if (n==Checks.length-1) return 0.0;

		if (Dp[n][dist]==0.0) {
			double time=10000000.0;
			int delta=Checks[n+1]-Checks[n];
			// Keep tires
			time=Math.min(time,computeTimeBetween(dist,dist+delta)+compute(n+1,dist+delta));
			// Change tires
			time=Math.min(time,B+computeTimeBetween(0,delta)+compute(n+1,delta));
			Dp[n][dist]=time;
		}

		return Dp[n][dist];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0")) {
        	Checks=new int [Integer.parseInt(s)+1];
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	for (int i=1;i<Checks.length;i++) Checks[i]=Integer.parseInt(st.nextToken());
        	B=Double.parseDouble(br.readLine());
        	st=new StringTokenizer(br.readLine());
        	R=Integer.parseInt(st.nextToken());
        	V=Double.parseDouble(st.nextToken());
        	E=Double.parseDouble(st.nextToken());
        	F=Double.parseDouble(st.nextToken());

        	Dp=new double [Checks.length][Checks[Checks.length-1]+1];
        	System.out.printf("%.4f\n",compute(0,0));
        }
	}

}