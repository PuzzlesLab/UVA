import java.util.Scanner;

class Main {

	public static void main(String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (sc.hasNext()) {
			double s=Double.parseDouble(sc.next())+6440;
			double a=Double.parseDouble(sc.next());
			String unit=sc.next();
			double multi=unit.equals("min")?21600:360;
			// 1 circle = 21600 minutes.

			a%=multi;
			if (a>=multi/2) a=multi-a; // If there is nearer complement angle.
			a/=multi;

			double c=2*Math.PI*s;
			System.out.printf("%.6f %.6f\n",c*a,Math.sqrt(2*s*s*(1-Math.cos(a*2*Math.PI))));
		}
 	}
}