import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static final int INIT=100000;
	private static final double LOSS=0.97;
	private static double [] Rates;
	private static int [][] Dp;

	private static int find(boolean cd, int d) {
		if (d==-1) return cd?INIT:0;

		//System.out.println("D="+d+", money="+(cd?"CAN":"USD"));
		int mt=cd?1:0;
		if (Dp[mt][d]==-1) {
			int max=find(cd,d-1); // Don't convert.
			// Try convert.
			double conv=cd?(find(!cd,d-1)*Rates[d]):(find(!cd,d-1)/Rates[d]);
			conv*=LOSS;
			//System.out.println(conv);
			max=Math.max(max,(int)conv);
			
			Dp[mt][d]=max;
		}

		return Dp[mt][d];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0")) {
        	int T=Integer.parseInt(s);
        	Rates=new double [T];
        	for (int t=0;t<T;t++) Rates[t]=Double.parseDouble(br.readLine());
        	
        	Dp=new int [2][Rates.length];
        	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
        	double ans=find(true,T-1)/100.0;
        	System.out.printf("%.2f\n",ans);
		}

	}

}