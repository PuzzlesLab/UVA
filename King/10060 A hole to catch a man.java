import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {
	
	private static class Point implements Comparable<Point> {
		double x, y;
		
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public double cross(Point p) {
			return this.x*p.y-this.y*p.x;
		}

		public int compareTo(Point p) {
			if (this.y!=p.y) return Double.compare(this.y,p.y);
			return Double.compare(this.x,p.x);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;

			double steelVol=0;
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				double T=Double.parseDouble(st.nextToken());

				ArrayList<Point> points=new ArrayList<>();
				while (st.hasMoreTokens()) points.add(new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
				double currVol=0;
				for (int i=0;i<points.size()-1;i++) currVol+=points.get(i).cross(points.get(i+1));
				currVol=Math.abs(currVol*0.5*T); // Area of polygon * thickness
				steelVol+=currVol;
			}

			StringTokenizer st=new StringTokenizer(br.readLine());
			// PI * r * r * t
			double coverVol=Math.PI*Math.pow(Double.parseDouble(st.nextToken()),2)*Double.parseDouble(st.nextToken());
			System.out.println((int)(steelVol/coverVol));
		}
 	}

}