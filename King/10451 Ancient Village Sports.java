import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		double PI=2*Math.acos(0);
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			if (N<3) break;

			double A=Double.parseDouble(st.nextToken());
			double angle=2*PI/N;
			// A = n*r*r*0.5*sin(single angle)
			double r=Math.sqrt((2*A)/(N*Math.sin(angle)));

			double spectator=PI*r*r-A;
			double r2=r*Math.cos(angle/2);
			double official=A-PI*r2*r2;
			System.out.printf("Case %d: %.5f %.5f\n",tc++,spectator,official);
		}
 	}
}
