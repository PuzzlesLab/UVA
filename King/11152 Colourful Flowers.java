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
			double violetArea=Math.sqrt(sp*(sp-a)*(sp-b)*(sp-c));
			double roseArea=Math.PI*Math.pow(violetArea/sp,2);
			double sfArea=Math.PI*Math.pow((a*b*c)/(4*violetArea),2);

			System.out.printf("%.4f %.4f %.4f\n",sfArea-violetArea,violetArea-roseArea,roseArea);
		}
 	}

}
