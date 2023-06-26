import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static double [] Prob;
	private static double [] ExistsProb;
	private static double TotalProb;
	
	private static void count(int pos, int remBuy, int mask, double mul) {
		if (pos==Prob.length) {
			for (int i=0;i<Prob.length;i++) if ((mask&(1<<i))!=0) ExistsProb[i]+=mul;
			TotalProb+=mul;
			return;
		}
		if (remBuy>0) count(pos+1,remBuy-1,mask|(1<<(pos)),mul*Prob[pos]);
		if (Prob.length-pos>remBuy) count(pos+1,remBuy,mask,mul*(1-Prob[pos]));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=1;
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());

			Prob=new double [N];
			for (int n=0;n<N;n++) Prob[n]=Double.parseDouble(br.readLine());

			ExistsProb=new double [N];
			TotalProb=0.0;
			count(0,R,0,1.0);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(TC++);
			sb.append(":\n");
			for (int n=0;n<N;n++) sb.append(String.format("%.6f\n",ExistsProb[n]/TotalProb));

			System.out.print(sb.toString());
		}
	}

}
