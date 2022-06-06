import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Dimsum {
		int price;
		int favour;
	}

	private static final double UNDEFINED=-20000.0;
	private static Dimsum [] Dimsums;
	private static double [][][] Dp;

	private static double find(int currIdx, int remPick, int remMoney) {
		if (currIdx==Dimsums.length) return 0.0;
		if (remMoney==0) return 0.0;

		if (Dp[currIdx][remPick][remMoney]==UNDEFINED) {
			// Don't choose.
			Dp[currIdx][remPick][remMoney]=find(currIdx+1,remPick,remMoney);
			// Choose.
			Dimsum currDimsum=Dimsums[currIdx];
			if (remPick>0 && remMoney>=currDimsum.price) {
				Dp[currIdx][remPick][remMoney]=Math.max(
					Dp[currIdx][remPick][remMoney],
					currDimsum.favour+find(currIdx+1,remPick-1,remMoney-currDimsum.price)
				);
			}
		}

		return Dp[currIdx][remPick][remMoney];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken())+1;
			int X=Integer.parseInt(st.nextToken())*N;
			int T=Integer.parseInt(st.nextToken())*N;
			int K=Integer.parseInt(st.nextToken());
			
			Dimsums=new Dimsum[K*2];
			for (int k=0;k<Dimsums.length;k+=2) {
				Dimsums[k]=new Dimsum();
				st=new StringTokenizer(br.readLine());
				Dimsums[k].price=Integer.parseInt(st.nextToken());
				for (int n=0;n<N;n++) Dimsums[k].favour+=Integer.parseInt(st.nextToken());
				Dimsums[k+1]=Dimsums[k];
			}

			int money=(int)Math.floor((X/1.1)-T+1e-6); // Add 1e-6 to calibrate floating error, WA if not added :/
			Dp=new double [Dimsums.length][Math.min(Dimsums.length,2*N)+1][money+1];
			for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++) Arrays.fill(Dp[i][i2],UNDEFINED);
			
			System.out.printf("%.2f\n",find(0,Dp[0].length-1,money)/N);
		}
	}

}
