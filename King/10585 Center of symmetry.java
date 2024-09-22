import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Point {
		double x, y;
		
		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			Point [] points=new Point [N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			double mx=0.0;
			double my=0.0;
			for (int n=0;n<N;n++) {
				mx+=points[n].x;
				my+=points[n].y;
			}
			mx/=N;
			my/=N;
			
			HashMap<Double,Integer> distCount=new HashMap<>();
			for (int n=0;n<N;n++) {
				double dx=mx-points[n].x;
				double dy=my-points[n].y;
				double dist=Math.sqrt(dx*dx+dy*dy);
				if (dist==0.0) continue; // Ignore if current point is the estimated symmetry point.
				distCount.put(dist,distCount.getOrDefault(dist,0)+1);
			}
			
			boolean flag=true;
			for (int value: distCount.values()) flag&=(value&1)==0;
			System.out.println(flag?"yes":"no");
		}
 	}

}