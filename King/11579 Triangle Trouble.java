import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static boolean ok(double d1, double d2, double d3) {
		return (d1+d2>d3 && d1+d3>d2 && d2+d3>d1);
	}

	private static double area(double d1, double d2, double d3) {
		double s=0.5*(d1+d2+d3);
		return Math.sqrt(s*(s-d1)*(s-d2)*(s-d3));
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			double [] sides=new double[N];
			for (int n=0;n<N;n++) sides[n]=Double.parseDouble(st.nextToken());
			Arrays.sort(sides);

			double max=0.0;
			for (int i=0;i<sides.length-2;i++) {
				if (!ok(sides[i],sides[i+1],sides[i+2])) continue;
				double a=area(sides[i],sides[i+1],sides[i+2]);
				if (a>max) max=Math.max(max,a);
			}
			System.out.printf("%.2f\n",max);
		}
 	}

}
