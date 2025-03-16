import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Point implements Comparable<Point> {
		int x, y;
		
		public Point(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Point p) {
			if (p.x==this.x) return this.y-p.y;
			return this.x-p.x;
		}

		public Point diff(Point p) {
			return new Point(this.x-p.x,this.y-p.y);
		}

		public double length() {
			return Math.abs(this.x)+Math.abs(this.y);
		}
		
		private Point invert() {
			return new Point(-this.x,-this.y);
		}
		
		public double dist(Point p) {
			int dx=this.x-p.x;
			int dy=this.y-p.y;
			return Math.sqrt(dx*dx+dy*dy);
		}

		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static final int MAX=32767;
	private static Point [] NumPoint;

	private static void makeTable() {
		int maxWidth=1;
		int currWidth=1;
		int count=1;
		int level=1;
		while (count<=MAX) {
			count+=currWidth;
			currWidth++;
			maxWidth+=2;
			level++;
		}

		NumPoint=new Point [MAX+200];
		int num=1;
		level=1;
		int numPerLevel=1;
		int startIdx=maxWidth>>1;
		while (num<=MAX) {
			int currIdx=startIdx;
			for (int i=0;i<numPerLevel;i++) {
				NumPoint[num]=new Point(level,currIdx);
				currIdx+=2;
				num++;
			}
			count+=numPerLevel;
			numPerLevel++;
			level++;
			startIdx-=1;
		}

	}
	
	private static boolean isTri(Point [] p) {
		Point a=p[0].diff(p[1]);
		Point b=p[1].diff(p[2]);
		Point c=p[2].diff(p[0]);
		if (a.length()==0 || b.length()==0 || c.length()==0) return false;
		
		if (p[0].x==p[1].x) {
			int d=p[1].y-p[0].y;
			return p[2].x==p[0].x+d/2 && p[2].y==(p[0].y+p[1].y)/2;
		}
		if (p[1].x==p[2].x) {
			int d=p[2].y-p[1].y;
			return p[0].x==p[1].x-d/2 && p[0].y==(p[1].y+p[2].y)/2;
		}
		return false;
	}

	private static boolean isPara(Point [] p) {
		Point a=p[0].diff(p[1]);
		Point b=p[3].diff(p[2]);
		Point c=p[0].diff(p[2]);
		Point d=p[3].diff(p[1]);
		if (a.length()==0 || b.length()==0 || c.length()==0 || d.length()==0) return false;

		if (p[0].dist(p[1])==p[1].dist(p[2]) && p[1].dist(p[2])==p[2].dist(p[3]) && p[2].dist(p[3])==p[3].dist(p[0])) return true;
		if (p[0].dist(p[1])==p[1].dist(p[3]) && p[1].dist(p[3])==p[2].dist(p[3]) && p[2].dist(p[3])==p[2].dist(p[0])) return p[0].y==p[3].y;
		if (p[0].x!=p[1].x) return false;
		if (p[2].x!=p[3].x) return false;
		if (c.compareTo(d.invert())!=0) return false;

		int hDif1=p[1].y-p[0].y;
		int hDif2=p[3].y-p[2].y;
		if (hDif1!=hDif2) return false;
		if (c.length()!=d.length()) return false;
		return hDif1==c.length();
	}

	private static boolean isHex(Point [] p) {
		if (p[0].x!=p[1].x) return false;
		if (p[0].y==p[1].y) return false;
		int d=(p[1].y-p[0].y)/2;
		Point p2e=new Point(p[0].x+d,p[0].y-d);
		Point p3e=new Point(p[1].x+d,p[1].y+d);
		Point p4e=new Point(p[2].x+d,p[0].y);
		Point p5e=new Point(p[3].x+d,p[1].y);
		return p[2].compareTo(p2e)==0 && p[3].compareTo(p3e)==0 && p[4].compareTo(p4e)==0 && p[5].compareTo(p5e)==0;
	}

	/*
	 * Bonus tests:
	 * 8 5 13
	 * 4 14 18 3
	 * 8 4 11 3
	 */

	public static void main(String [] args) throws Exception {
		makeTable();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		// All same-side-length triangle, parallelogram, hexagon
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int [] nums=new int [st.countTokens()];
			Point [] points=new Point [nums.length];
			for (int i=0;i<nums.length;i++) {
				nums[i]=Integer.parseInt(st.nextToken());
				points[i]=NumPoint[nums[i]];
			}
			Arrays.sort(points);

			StringBuilder sb=new StringBuilder();
			for (int i=0;i<nums.length;i++) {
				sb.append(nums[i]);
				sb.append(' ');
			}
			if (points.length==3 && isTri(points)) sb.append("are the vertices of a triangle");
			else if (points.length==4 && isPara(points)) sb.append("are the vertices of a parallelogram");
			else if (points.length==6 && isHex(points)) sb.append("are the vertices of a hexagon");
			else sb.append("are not the vertices of an acceptable figure");
			System.out.println(sb);
		}
 	}

}
