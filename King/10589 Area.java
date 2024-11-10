import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y;
		
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
	}

	private static boolean inRange(Tuple [] points, Tuple c, double r) {
		for (int i=0;i<points.length;i++) {
			double dx=points[i].x-c.x;
			double dy=points[i].y-c.y;
			double distSq=dx*dx+dy*dy;
			if (distSq>r*r) return false;
		}
		return true;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			double a=Double.parseDouble(st.nextToken());
			Tuple [] corners=new Tuple[4];
			corners[0]=new Tuple(0,0);
			corners[1]=new Tuple(0,a);
			corners[2]=new Tuple(a,0);
			corners[3]=new Tuple(a,a);
			
			double m=0;
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				Tuple t=new Tuple(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				if (inRange(corners,t,a)) m++;
			}
			System.out.printf("%.5f\n",m/N*a*a);
		}
 	}
}
