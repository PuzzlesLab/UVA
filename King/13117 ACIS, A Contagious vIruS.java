import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Tuple {
		double x, y;
		
		public Tuple(String s) {
			StringTokenizer st=new StringTokenizer(s);
			this.x=Double.parseDouble(st.nextToken());
			this.y=Double.parseDouble(st.nextToken());
		}

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
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("*")) {
			int N=Integer.parseInt(s);
			Tuple redis=new Tuple(br.readLine());
			Tuple [] vertices=new Tuple [N];
			for (int n=0;n<N;n++) vertices[n]=new Tuple(br.readLine());
			
			double ans=Double.MAX_VALUE;
			for (int n=0;n<N;n++) ans=Math.min(ans,distToLineSeg(redis,vertices[n],vertices[(n+1)%N]));
			ans*=10000;
			int lastDigit=(int)(ans%10);
			if (lastDigit<5) ans-=lastDigit;
			else if (lastDigit>5) ans+=5;
			ans/=10000;
			System.out.printf("%.3f\n",ans);
		}
 	}

}