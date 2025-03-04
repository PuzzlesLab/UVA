import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double a=Double.parseDouble(st.nextToken());
			double b=Double.parseDouble(st.nextToken());
			double c=Double.parseDouble(st.nextToken());
			
			if ((a+b)>c && (a+c)>b && (b+c)>a) {
				double sp=0.5*(a+b+c);
				double area=4.0/3*Math.sqrt(sp*(sp-a)*(sp-b)*(sp-c));
				System.out.printf("%.3f\n",area);
			} else System.out.println("-1.000");
		}
 	}

}
