import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double A=Double.parseDouble(st.nextToken());
			double B=Double.parseDouble(st.nextToken());
			double S=Double.parseDouble(st.nextToken());

			/*
			 *  Figure : https://imgur.com/ysEwoJN
			 *  
			 *  Area u = B * S/B = S
			 *  Area v = Integrate S/B to A, for S/x dx
			 *         = S * [ln A - ln (S/B)]
			 *         = S * ln [(A*B)/S];
			 *  
			 *  Find ratio of area not in u & v.
			 */
			double ans=0.0;
			if (S==0) ans=1.0;
			else if (S<A*B) {
				double area=A*B;
				double uv=S+S*Math.log(area/S);
				double exceed=area-uv;
				ans=exceed/area;
			}
			System.out.printf("%.6f%%\n",ans*100);
		}
	}

}
