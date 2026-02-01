import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			double [] loc=new double [N];
			for (int n=0;n<N;n++) loc[n]=Double.parseDouble(br.readLine());
			Arrays.sort(loc);
			double D=Double.parseDouble(br.readLine());

			// All particles starts moving at t=0, first particle is heated.
			// The goal is to get all right particles move left to previous particle at distance D to get heated.
			double ans=0;
			for (int n=1;n<N;n++) {
				double dist=loc[n]-loc[n-1];
				if (dist<=D) loc[n]=Math.min(loc[n]+ans, loc[n-1]+D);
				else { // To far from heat
					double toHeat=dist-ans;
					if (toHeat>D) { // After t time (ans) still too far.
						double move=(toHeat-D)/2;
						ans+=move; // Give it time to move.
						loc[n]-=ans;
					} else loc[n]=loc[n-1]+D; // Movable within t time.
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(": ");
			sb.append(String.format("%.3f",ans));
			System.out.println(sb.toString());
		}
	}

}