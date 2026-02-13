import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static double dist(double x1, double y1, double x2, double y2) {
		double dx=x2-x1;
		double dy=y2-y1;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			double gopX=Double.parseDouble(st.nextToken());
			double gopY=Double.parseDouble(st.nextToken());
			double dogX=Double.parseDouble(st.nextToken());
			double dogY=Double.parseDouble(st.nextToken());
			
			StringBuilder sb=new StringBuilder();
			double [] holeX=new double [N];
			double [] holeY=new double[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				holeX[n]=Double.parseDouble(st.nextToken());
				holeY[n]=Double.parseDouble(st.nextToken());
			}
			
			int ansIdx=-1;
			for (int n=0;n<N;n++) {
				double distGH=dist(gopX,gopY,holeX[n],holeY[n]);
				double distDH=dist(dogX,dogY,holeX[n],holeY[n])/2;
				if (distGH<=distDH) {
					ansIdx=n;
					break;
				}
			}
			if (ansIdx!=-1) {
				sb.append("The gopher can escape through the hole at ");
				sb.append(String.format("(%.3f,%.3f).",holeX[ansIdx],holeY[ansIdx]));
			} else sb.append("The gopher cannot escape.");

			System.out.println(sb);
			br.readLine(); // Empty.
		}
	}

}