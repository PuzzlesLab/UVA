import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double x=Integer.parseInt(st.nextToken());
			double y=Integer.parseInt(st.nextToken());
			double r=Integer.parseInt(st.nextToken());
			
			double dist=Math.sqrt(x*x+y*y);
			double dMin=r-dist;
			double dMax=r+dist;
			System.out.printf("%.2f %.2f\n",dMin,dMax);
		}
 	}

}
