import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static double calcD4(double S) {
		double a=5/3.0;
		double b=S*(Math.sqrt(3)*7.0)/3.0;
		double c=-S*S*7.0/4;
		return (-b+Math.sqrt(b*b-4*a*c))/(2*a);
	}

	public static void main(String [] args) throws Exception {
		final double sides=6;
		final double angle=(2*Math.PI)/sides;
		final double angle2=((sides-2)*Math.PI)/sides;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			double S=Double.parseDouble(s);
			double r=S/(2*Math.sin(angle*0.5));
			
			double d1=r*Math.cos(angle/2);
			double d2=S/(1+Math.sqrt(12)/3);
			double d3=(r*Math.sin(angle2)/Math.sin(angle/2))/4;
			double d4=calcD4(S);
			
			System.out.printf("%.10f %.10f %.10f %.10f\n",d1,d2,d3,d4);
		}
 	}
}
