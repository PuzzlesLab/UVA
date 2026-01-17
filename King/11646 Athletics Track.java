import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double a=Double.parseDouble(st.nextToken());
			st.nextToken();
			double b=Double.parseDouble(st.nextToken());
			
			double r=Math.sqrt((a*a+b*b)/4);
			double halfAng=Math.asin(b/(2*r));
			double c=2*halfAng*r;

			double pB=a+a+c+c;
			double f=400/pB;
			
			System.out.printf("Case %d: %.10f %.10f\n",tc,a*f,b*f);
			tc++;
		}
	}

}