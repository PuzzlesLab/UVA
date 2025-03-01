import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double x1=Double.parseDouble(st.nextToken());
			double y1=Double.parseDouble(st.nextToken());
			double x2=Double.parseDouble(st.nextToken());
			double y2=Double.parseDouble(st.nextToken());
			double cmf=Math.toRadians(Double.parseDouble(st.nextToken()));
			double enf=Math.toRadians(Double.parseDouble(st.nextToken()));
			
			/*
			 * Constraint: N, X(point on AB), M must be on straight line.
			 * 
			 *             A
			 *           . | .
			 *         .   |   .
			 *       .     |     .
			 *     .      _|_      .
			 * N  .______|_|_|______. M
			 *            B/X
			 * 
			 * Find length of NXM.
			 * 
			 */
			
			double dx=x1-x2;
			double dy=y1-y2;
			double ab=Math.sqrt(dx*dx+dy*dy);
			System.out.printf("%.3f\n",ab/Math.tan(cmf)+ab/Math.tan(enf));
		}
 	}

}
