import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double x=Double.parseDouble(st.nextToken());
			double y=Double.parseDouble(st.nextToken());
			double c=Double.parseDouble(st.nextToken());
			
			double min=0;
			double max=Math.min(x,y);
			double mid=0.0;
			/*
			 *       hx hy
			 * c = -----------
			 *      hx + hy
			 */
			while (max-min>=Math.abs(1e-5)) {
				mid=(min+max)/2;
				double hx=Math.sqrt(x*x-mid*mid);
				double hy=Math.sqrt(y*y-mid*mid);
				double tc=(hx*hy)/(hx+hy);
				if (tc>c) min=mid;
				else max=mid;
			}
			
			System.out.printf("%.3f\n", mid);
		}
	}

}
