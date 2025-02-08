import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double l=Double.parseDouble(st.nextToken());
			double w=Double.parseDouble(st.nextToken());
			double h=Double.parseDouble(st.nextToken());
			double deg=Math.toRadians(Double.parseDouble(st.nextToken()));
			double tanDeg=Math.tan(deg);

			if (tanDeg>=h/l) {
				System.out.printf("%.3f mL\n",0.5*w*(h/tanDeg)*h);
			} else {
				double outV=0.5*l*tanDeg*l*w;
				System.out.printf("%.3f mL\n",l*w*h-outV);
			}
		}
 	}
}