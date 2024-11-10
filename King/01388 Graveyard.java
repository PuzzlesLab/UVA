import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		final double C=10000;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int init=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken())+init;
			double dist1=C/init;
			double dist2=C/end;
			
			double ans=0.0;
			for (int n=0;n<init;n++) {
				int endIdx=(int)((end+0.0)/init*n); // Scale to approx resulting index
				double newPos1=dist2*endIdx;
				double newPos2=dist2*(endIdx+1);
				double oldPos=dist1*n;
				ans+=Math.min(Math.abs(oldPos-newPos1),Math.abs(oldPos-newPos2));
			}

			System.out.printf("%.4f\n",ans);
		}
 	}
}
