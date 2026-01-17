import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int F=Integer.parseInt(st.nextToken())+1;
			double [] a=new double [N];
			st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) {
				a[n]=Integer.parseInt(st.nextToken());
				a[n]=Math.PI*a[n]*a[n];
			}

			double minA=0;
			double maxA=0;
			for (int n=0;n<N;n++) maxA=Math.max(maxA,a[n]);
			int loop=50;
			while (maxA>minA+1e-6 && loop-->0) {
				double midA=(minA+maxA)/2;
				int pieces=0;
				for (int n=0;n<N;n++) pieces+=(int)(a[n]/midA);
				if (pieces>=F) minA=midA;
				else maxA=midA;
			}
			System.out.printf("%.4f\n",maxA);
		}
	}

}
