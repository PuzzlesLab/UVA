import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Trip {
		int time;
		int sphere;
	}

	private static Trip [] Trips;
	private static int [][] Dp;

	private static int compute(int n, int s) {
		if (n==Trips.length) return 0;
		
		if (Dp[n][s]==-1) {
			Dp[n][s]=compute(n+1,Math.min(s+Trips[n].sphere,Trips.length))+Trips[n].time;
			if (s>0) Dp[n][s]=Math.min(Dp[n][s],compute(n+1,Math.min(s-1+Trips[n].sphere,Trips.length))+Trips[n].time/2);
		}

		return Dp[n][s];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Trips=new Trip[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Trips[n]=new Trip();
				Trips[n].time=Integer.parseInt(st.nextToken());
				Trips[n].sphere=Integer.parseInt(st.nextToken());
			}
			
			Dp=new int [N+1][N+1];
			for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
			System.out.println(compute(0,0));
		}
	}

}
