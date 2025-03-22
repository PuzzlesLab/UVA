import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static double calcDist(double x1, double y1, double x2, double y2) {
		double dx=x2-x1;
		double dy=y2-y1;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double ax=Double.parseDouble(st.nextToken());
			double ay=Double.parseDouble(st.nextToken());
			double bx=Double.parseDouble(st.nextToken());
			double by=Double.parseDouble(st.nextToken());
			System.out.printf("%.3f\n",calcDist(ax,ay,bx,by)+calcDist(-bx,by,ax,-ay));
		}
 	}

}
