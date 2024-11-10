import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double R=Integer.parseInt(st.nextToken());
			double N=Integer.parseInt(st.nextToken());

			if (N>1) {
				double angle=2*Math.PI/N;
				double alpha=(Math.PI-angle)/2;
				double r=R/(1+1/Math.cos(alpha));
				double innerPolygon=N*r*Math.sqrt((R-r)*(R-r)-r*r);
				double sector=N*r*r*alpha;
				double I=innerPolygon-sector;
				double smallCircles=Math.PI*r*r*N;
				double bigCircle=Math.PI*R*R;

				System.out.printf("%.10f %.10f %.10f\n",r,I,bigCircle-smallCircles-I);
			} else {
				System.out.printf("%.10f %.10f %.10f\n",R,0.,0.);
			}

		}
 	}
}