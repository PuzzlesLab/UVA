import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	private static class Tuple {
		double x, y;
		
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
	}
	
	private static Tuple intersect;

	private static double dot(Tuple t1, Tuple t2) {
		return t1.x*t2.x+t1.y*t2.y;
	}
	
	private static double square(Tuple t) {
		return t.x*t.x+t.y*t.y;
	}
	
	private static double distToLine(Tuple p, Tuple start, Tuple end) {
		Tuple sp=new Tuple(start,p);
		Tuple se=new Tuple(start,end);
		double u=dot(sp,se)/square(se);
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
		double u=dot(sp,se)/square(se);
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
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			Tuple m=new Tuple(Double.parseDouble(s),Double.parseDouble(br.readLine()));
			int N=Integer.parseInt(br.readLine());
			Tuple [] stations=new Tuple[N+1];
			for (int n=0;n<stations.length;n++) {
				stations[n]=new Tuple(Double.parseDouble(br.readLine()),Double.parseDouble(br.readLine()));
			}
			
			Tuple ans=null;
			if (stations.length==1) ans=stations[0];
			else {
				double currMin=Double.MAX_VALUE;
				for (int n=1;n<stations.length;n++) {
					double dist=distToLineSeg(m,stations[n-1],stations[n]);
					if (dist<currMin) {
						currMin=dist;
						ans=intersect;
					}
				}
			}

			System.out.printf("%.4f\n%.4f\n",ans.x,ans.y);
		}
 	}

}
