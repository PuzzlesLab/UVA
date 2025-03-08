import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static double calcSector(double r, double angle) {
		return r*r*angle*0.5;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int K=Integer.parseInt(br.readLine());
		for (int k=0;k<K;k++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double R1=Double.parseDouble(st.nextToken());
			double R2=Double.parseDouble(st.nextToken());
			double R3=Double.parseDouble(st.nextToken());
			
			double a=R1+R2;
			double b=R2+R3;
			double c=R1+R3;

			double sp=0.5*(a+b+c);
			double triangle=Math.sqrt(sp*(sp-a)*(sp-b)*(sp-c));
			
			double angleX=Math.acos((b*b-a*a-c*c)/(-2*a*c)); // Angle in C1
			double angleY=Math.acos((c*c-a*a-b*b)/(-2*a*b)); // Angle in C2
			double angleZ=Math.acos((a*a-b*b-c*c)/(-2*b*c)); // Angle in C3

			double sectors=calcSector(R1,angleX)+calcSector(R2,angleY)+calcSector(R3,angleZ);
			// Something wrong with the sample output (with 4 decimal places only!)
			System.out.printf("%.6f\n",triangle-sectors);
		}
 	}

}