import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double W=Double.parseDouble(st.nextToken());
			double H=Double.parseDouble(st.nextToken());
			double x=Double.parseDouble(st.nextToken());
			double xe=Double.parseDouble(st.nextToken());
			double ye=Double.parseDouble(st.nextToken());
			double u=Double.parseDouble(st.nextToken());
			
			double eyeAng=Math.atan((xe-W)/(ye-H));
			double liqAng=Math.asin(Math.sin(eyeAng)/u);
			double h=(x-W+H*Math.tan(eyeAng))/(Math.tan(eyeAng)-Math.tan(liqAng));
			if (h<=0) h=0;
			
			if (h>H) sb.append("Impossible\n");
			else sb.append(String.format("%.4f\n",h));
		}
		System.out.print(sb.toString());
 	}

}
