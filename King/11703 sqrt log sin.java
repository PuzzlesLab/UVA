import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static int [] Dp;

	private static int compute(int i) {
		if (i==0) return 1;

		if (Dp[i]==Integer.MAX_VALUE) {
			int idx1=(int)(i-Math.sqrt(i));
			int idx2=(int)Math.log(i);
			int idx3=(int)(i*Math.sin(i)*Math.sin(i));
			Dp[i]=(compute(idx1)+compute(idx2)+compute(idx3))%1000000;
		}
		return Dp[i];
	}

	public static void main(String[] args) throws Exception {
		Dp=new int [1000001];
		Arrays.fill(Dp,Integer.MAX_VALUE);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int I=Integer.parseInt(br.readLine());
			if (I==-1) return;
			
			System.out.println(compute(I));
		}
	}

}