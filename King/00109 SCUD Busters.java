import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	private static class Point implements Comparable<Point> {
		double x, y;

		public Point(StringTokenizer st) {
			this.x=Double.parseDouble(st.nextToken());
			this.y=Double.parseDouble(st.nextToken());
		}

		public Point(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public double cross(Point p) {
			return this.x*p.y-this.y*p.x;
		}
		
		public int compareTo(Point p) {
			if (this.x!=p.x) return Double.compare(this.x,p.x);
			return Double.compare(this.y,p.y);
		}
		
		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static boolean ccw(Point o, Point a, Point b) {
		Point vOA=new Point(a.x-o.x,a.y-o.y);
		Point vOB=new Point(b.x-o.x,b.y-o.y);
		return vOA.cross(vOB)>0;
	}

	private static class Kingdom {
		ArrayList<Point> points=new ArrayList<>();
		boolean destroyed;
		
		public void convexHull() {
			Collections.sort(this.points);
			ArrayList<Point> result=new ArrayList<>();
			
			for (int i=0;i<this.points.size();i++) {
				Point p=this.points.get(i);
				while (result.size()>=2 && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) {
					result.remove(result.size()-1);
				}
				result.add(p);
			}

			int base=result.size()+1;
			for (int i=this.points.size()-1;i>=0;i--) {
				Point p=this.points.get(i);
				while (result.size()>=base && !ccw(result.get(result.size()-2),result.get(result.size()-1),p)) {
					result.remove(result.size()-1);
				}
				result.add(p);
			}
			this.points=result;
		}

		public boolean contains(Point p) {
			int count=0;
			for (int i=0;i<points.size()-1;i++) {
				Point p1=points.get(i);
				Point p2=points.get(i+1);
				boolean f1=p1.y<=p.y && p.y<p2.y;
				boolean f2=p2.y<=p.y && p.y<p1.y;
				if ((f1 || f2) && p.x-1e-6<(p2.x-p1.x)/(p2.y-p1.y)*(p.y-p1.y)+p1.x) count++;
			}
			return (count&1)==1;
		}

		public double area() {
			double r=0.0;
			for (int i=0;i<points.size()-1;i++) r+=points.get(i).cross(points.get(i+1));
			return 0.5*Math.abs(r);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Kingdom> kingdoms=new ArrayList<>();
		while (true) {
			int N=Integer.parseInt(br.readLine().trim());
			if (N==-1) break;
			
			Kingdom kd=new Kingdom();
			for (int n=0;n<N;n++) kd.points.add(new Point(new StringTokenizer(br.readLine())));
			kd.convexHull();
			kingdoms.add(kd);
		}

		String s;
		while ((s=br.readLine())!=null) {
			if (s.isEmpty()) break;
			Point missle=new Point(new StringTokenizer(s));
			for (int i=0;i<kingdoms.size();i++) {
				Kingdom kd=kingdoms.get(i);
				kd.destroyed|=kd.contains(missle);
			}
		}

		double area=0;
		for (int i=0;i<kingdoms.size();i++) {
			Kingdom kd=kingdoms.get(i);
			if (kd.destroyed) area+=kd.area();
		}
		System.out.printf("%.2f\n",area);
 	}

}
