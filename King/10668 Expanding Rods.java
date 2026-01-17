import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double L=Double.parseDouble(st.nextToken());
			double n=Double.parseDouble(st.nextToken());
			double C=Double.parseDouble(st.nextToken());
			if (L<0 && n<0 && C<0) break;
			
			double Lp=(1+n*C)*L;
			double low=0.0;
			double high=Math.PI/2;
			double halfAng=0.0;
			double r=0.0;
			for (int i=0;i<100;i++) {
				halfAng=(low+high)/2;
				r=L/(2*Math.sin(halfAng));
				if (2*r*halfAng>=Lp) high=halfAng;
				else low=halfAng;
			}

			System.out.printf("%.3f\n",r-r*Math.cos(halfAng));
			// chord = 2x sqrt(r^2 - d^2)
		}
	}

}
