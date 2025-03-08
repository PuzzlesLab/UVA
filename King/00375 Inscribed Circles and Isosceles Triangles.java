import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static double area(double base, double height) {
		return 0.5*base*height;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int n=0;n<N;n++) {
			br.readLine(); //Empty line.
			StringTokenizer st=new StringTokenizer(br.readLine());
			double B=Double.parseDouble(st.nextToken());
			double H=Double.parseDouble(st.nextToken());
			double tanSideAngle=2*H/B;

			double C=0.0;
			while (true) {
				double S=Math.sqrt(H*H+(B/2)*(B/2));
				double r=area(B,H)/(0.5*(B+S+S));
				if (r<0.000001) break;

				C+=2*Math.PI*r;
				H=H-r-r;
				B=2*H/tanSideAngle;
			}
			if (n>0) sb.append('\n');
			sb.append(String.format("%13.6f\n", C));
		}
		System.out.print(sb);
 	}

}
