import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String line;
		while (!(line=br.readLine()).equals("0 0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(line);
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			double s=Double.parseDouble(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int n=Integer.parseInt(st.nextToken());

			double x=a*m; // Complete horizontal distance to make a vertical bounce.
			double y=b*n; // Complete vertical distance to make a horizontal bounce.
			double angle=Math.toDegrees(Math.atan(y/x));
			double v=Math.sqrt(x*x+y*y)/s;
			System.out.printf("%.2f %.2f\n",angle,v);
		}
 	}
}