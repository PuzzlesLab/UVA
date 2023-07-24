import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static double [] p=new double [100002];
	
	private static double calcP(int n) {
		if (n==2) return 1.0;

		if (p[n]==0) p[n]=((n-3.0)/(n-2.0))*calcP(n-2);
		return p[n];
	}

	public static void main(String[] args) throws Exception {
		for (int i=2;i<p.length;i+=2) calcP(i);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			System.out.printf("%.4f\n",1-calcP(N));
		}
	}

}
