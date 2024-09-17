import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringTokenizer;

class Main {

	private static class Point {
		double x, y;

		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public boolean equals(Object o) {
			Point p=(Point)o;
			return this.x==p.x && this.y==p.y;
		}

	    @Override
	    public int hashCode() {
	      return Objects.hash(this.x,this.y);
	    }
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			Point [] points=new Point[N];
			HashMap<Point,Integer> pointsMap=new HashMap<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				pointsMap.put(points[n],pointsMap.getOrDefault(points[n],0)+1);
			}

			double minX=Integer.MAX_VALUE;
			double maxX=Integer.MIN_VALUE;
			for (int n=0;n<N;n++) {
				minX=Math.min(minX,points[n].x);
				maxX=Math.max(maxX,points[n].x);
			}
			double midX=(minX+maxX)/2;

			boolean ans=true;
			for (int n=0;n<N && ans;n++) {
				if (points[n].x==midX) continue;
				if (points[n].x<midX) {
					double dx=midX-points[n].x;
					Point symPoint=new Point(points[n].x+dx+dx,points[n].y);
					if (pointsMap.containsKey(symPoint)) {
						int count=pointsMap.get(symPoint)-1;
						if (count>0) pointsMap.put(symPoint,count);
						else pointsMap.remove(symPoint);
					} else ans=false;
				} else { // > midX
					double dx=points[n].x-midX;
					Point symPoint=new Point(points[n].x-dx-dx,points[n].y);
					if (pointsMap.containsKey(symPoint)) {
						int count=pointsMap.get(symPoint)-1;
						if (count>0) pointsMap.put(symPoint,count);
						else pointsMap.remove(symPoint);
					} else ans=false;
				}
			}
			System.out.println(ans?"YES":"NO");
		}
 	}

}
