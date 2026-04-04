import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y, r;

		public Tuple(int x, int y, int r) {
			this.x=x;
			this.y=y;
			this.r=r;
		}

		public double dist(Tuple t) {
			double dx=this.x-t.x;
			double dy=this.y-t.y;
			return Math.sqrt(dx*dx+dy*dy);
		}

	}

	private static int MAX_L=1000;
	private static int N;
	private static Tuple[] Points;

	private static int getParent(int[] parent, int i) {
		if (parent[i]==i) return i;
		return parent[i]=getParent(parent, parent[i]);
	}

	private static void setParent(int[] parent, int i, int i2) {
		int p1=getParent(parent,i);
		int p2=getParent(parent,i2);
		if (p1>p2) parent[p1]=p2;
		else parent[p2]=p1;
	}

	private static boolean reachable(double robotR) {
		int[] parent=new int[Points.length];
		for (int i=0;i<parent.length;i++) parent[i]=i;

		double robotD=robotR+robotR;
		for (int i=0;i<N;i++) {
			for (int i2=i+1;i2<N;i2++) {
				double dist=Points[i].dist(Points[i2]);
				if (dist-Points[i].r-Points[i2].r<robotD) setParent(parent,i,i2);
			}

			if (Points[i].x-Points[i].r<robotD) setParent(parent,i,N);
			if (MAX_L-Points[i].x-Points[i].r<robotD) setParent(parent,i,N+1);
			if (MAX_L-Points[i].y-Points[i].r<robotD) setParent(parent,i,N+2);
			if (Points[i].y-Points[i].r<robotD) setParent(parent,i,N+3);
		}

		for (int n=N;n<Points.length;n++) for (int n2=n+1;n2<Points.length;n2++) {
			if (getParent(parent,n)==getParent(parent,n2)) return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			N=Integer.parseInt(br.readLine());
			Points=new Tuple[N + 4];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Points[n]=new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}

			double minR=0.0;
			double maxR=500.0;
			int loop=0;
			while (Math.abs(maxR-minR)>10e-8 && loop++<50) {
				double midR=(minR+maxR)/2;
				if (reachable(midR)) maxR=midR;
				else minR=midR;
			}
			System.out.printf("%.3f\n",maxR);
		}
	}

}