import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y;

		public Tuple() {}

		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}

		public Tuple(Tuple t, Tuple t2) {
			this.x=t2.x-t.x;
			this.y=t2.y-t.y;
		}
		
		public double dist(Tuple t) {
			return Math.hypot(this.x-t.x,this.y-t.y);
		}
		
		public double square() {
			return this.x*this.x+this.y*this.y;
		}
		
		public double dot(Tuple t) {
			return this.x*t.x+this.y*t.y;
		}
	}

	private static class Line {
		Tuple p1, p2;
		double a, b, c;

		public Line(StringTokenizer st) {
			this.p1=new Tuple(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			this.p2=new Tuple(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));

			if (this.p1.x==this.p2.x) {
				this.a=1;
				this.b=0.0;
				this.c=-this.p1.x;
			} else {
				this.a=-(this.p1.y-this.p2.y)/(this.p1.x-this.p2.x);
				this.b=1.0;
				this.c=-(this.a*this.p1.x)-this.p1.y;
			}
		}

		public boolean isParallel(Line l) {
			return this.a==l.a && this.b==l.b;
		}
		
		public Tuple intersect(Line l) {
			if (this.isParallel(l)) return null;
			Tuple p=new Tuple();
			p.x=(l.b*this.c-this.b*l.c)/(l.a*this.b-this.a*l.b);
			p.y=this.b>0 ? -(this.a*p.x+this.c) : -(l.a*p.x+l.c);
			return p;
		}
	}

	private static Tuple intersect;

	private static double distToLine(Tuple p, Tuple start, Tuple end) {
		Tuple sp=new Tuple(start,p);
		Tuple se=new Tuple(start,end);
		double u=sp.dot(se)/se.square();
		intersect=new Tuple(se.x*u,se.y*u);
		intersect.x+=start.x;
		intersect.y+=start.y;
		return p.dist(intersect);
	}

	private static double distToLineSeg(Tuple p, Tuple start, Tuple end) {
		if (start.x==end.x && start.y==end.y) {
			intersect=start;
			return p.dist(start);
		}
		Tuple sp=new Tuple(start,p);
		Tuple se=new Tuple(start,end);
		double u=sp.dot(se)/se.square();
		if (u<0) {
			intersect=start;
			return p.dist(start);
		}
		if (u>1.0) {
			intersect=end;
			return p.dist(end);
		}
		return distToLine(p,start,end);
	}

	public static void main(String [] args) throws Exception {
		final double EPS=0.000001;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Line [] sticks=new Line [N];
			for (int n=0;n<N;n++) sticks[n]=new Line(new StringTokenizer(br.readLine()));

			ArrayList<Integer> ans=new ArrayList<>();
			for (int n=0;n<N;n++) {
				boolean flag=false;
				for (int n2=n+1;n2<N;n2++) {
					Tuple p=sticks[n].intersect(sticks[n2]);
					if (p!=null && distToLineSeg(p,sticks[n].p1,sticks[n].p2)<EPS && distToLineSeg(p,sticks[n2].p1,sticks[n2].p2)<EPS) {
						flag=true;
						break;
					}
				}
				if (!flag) ans.add(n+1);
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Top sticks:");
			for (int i=0;i<ans.size();i++) {
				sb.append(' ');
				sb.append(ans.get(i));
				sb.append(',');
			}
			if (sb.charAt(sb.length()-1)==',') {
				sb.setLength(sb.length()-1);
				sb.append('.');
			}
			System.out.println(sb);
		}
 	}

}
