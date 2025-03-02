import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double r=Double.parseDouble(st.nextToken());
			double d=Double.parseDouble(st.nextToken());
			double h1=Double.parseDouble(st.nextToken());
			
			// Solution: https://imgur.com/Rn4avpb
			double eb=Math.sqrt(r*r-((r-d)*(r-d)));
			double ab=eb*2;
			double angle=Math.asin((r-h1)/r)-Math.asin((r-d)/r);
			double hx=ab*Math.sin(angle);
			System.out.printf("Case %d: %.4f\n",n,h1+hx);
		}
 	}

}
