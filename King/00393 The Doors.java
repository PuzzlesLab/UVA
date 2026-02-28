import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y;

		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public double cross(Tuple t) {
			return this.x*t.y-this.y*t.x;
		}
		
		public Tuple toVec(Tuple t) {
			return new Tuple(t.x-this.x,t.y-this.y);
		}
		
		public double dist(Tuple t) {
			double dx=this.x-t.x;
			double dy=this.y-t.y;
			return Math.sqrt(dx*dx+dy*dy);
		}

		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static class Line {
		Tuple p1, p2;

		public Line(double x1, double y1, double x2, double y2) {
			this.p1=new Tuple(x1,y1);
			this.p2=new Tuple(x2,y2);
		}
		
		public String toString() {
			return this.p1+" <-> "+this.p2;
		}

		private static boolean hasPoint(Tuple p, Tuple q, Tuple r) {
			return r.x>=Math.min(p.x,q.x) && r.x<=Math.max(p.x,q.x) && r.y>=Math.min(p.y,q.y) && r.y<=Math.max(p.y,q.y);
		}

		private int ccw(Tuple r) {
			Tuple vecPQ=this.p1.toVec(this.p2);
			Tuple vecPR=this.p1.toVec(r);
			double cp=vecPQ.cross(vecPR);
			if (Math.abs(cp)<1e-7) return 0;
			return cp>0?1:2;
		}

		public boolean intersect(Line s) {
			int v1=this.ccw(s.p1);
			int v2=this.ccw(s.p2);
			int v3=s.ccw(this.p1);
			int v4=s.ccw(this.p2);
			
			if (v1!=v2 && v3!=v4) return true;
			if (v1==0 && Line.hasPoint(this.p1,this.p2,s.p1)) return true;
			if (v2==0 && Line.hasPoint(this.p1,this.p2,s.p2)) return true;
			if (v3==0 && Line.hasPoint(s.p1,s.p2,this.p1)) return true;
			if (v4==0 && Line.hasPoint(s.p1,s.p2,this.p2)) return true;
			return false;
			
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1")) {
			int N=Integer.parseInt(s);
			
			ArrayList<Tuple> points=new ArrayList<>();
			points.add(new Tuple(0,5));
			ArrayList<Line> walls=new ArrayList<>();
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				double x=Double.parseDouble(st.nextToken());
				double lastY=0;
				while (st.hasMoreTokens()) {
					double y=Double.parseDouble(st.nextToken());
					if (lastY!=-1) {
						if (lastY!=0) points.add(new Tuple(x,lastY));
						points.add(new Tuple(x,y));
						walls.add(new Line(x,lastY,x,y));
						lastY=-1;
					} else lastY=y;
				}
				walls.add(new Line(x,lastY,x,10.0));
			}
			points.add(new Tuple(10,5));
			
			double [][] minDist=new double [points.size()][points.size()];
			for (int i=0;i<minDist.length;i++) {
				Arrays.fill(minDist[i],1000000);
				minDist[i][i]=0.0;
			}

			for (int i=0;i<points.size();i++) for (int i2=i+1;i2<points.size();i2++) {
				Tuple p1=points.get(i);
				Tuple p2=points.get(i2);
				if (p1.x>=p2.x) continue;

				Line l=new Line(p1.x,p1.y,p2.x,p2.y);
				boolean flag=true;
				for (int w=0;w<walls.size();w++) {
					Line wall=walls.get(w);
					if (wall.p1.x==l.p1.x || wall.p1.x==l.p2.x) continue;
					if (wall.intersect(l)) {
						flag=false;
						break;
					}
				}
				if (flag) minDist[i][i2]=Math.min(minDist[i][i2],p1.dist(p2));
			}

			for (int k=0;k<minDist.length;k++) {
				for (int i=0;i<minDist.length;i++) {
					for (int j=0;j<minDist.length;j++) {
						minDist[i][j]=Math.min(minDist[i][j],minDist[i][k]+minDist[k][j]);
					}
				}
			}
			
			System.out.printf("%.2f\n",minDist[0][minDist.length-1]);
		}
	}

}
