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
			
			double sp=0.5*(a+b+c);
			double area=Math.sqrt(sp*(sp-a)*(sp-b)*(sp-c));
			System.out.printf("The radius of the round table is: %.3f\n",sp!=0?area/sp:0);
		}
 	}

}
