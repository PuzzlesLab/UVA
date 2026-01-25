import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Building {
		double h;
		double start;
		double end;

		public Building(double h, double s, double e) {
			this.h=h;
			this.start=s;
			this.end=e;
		}
	}

	private static final double EPS=1e-6;
	private static final double G=9.8;
	private static Building [] Buildings;
	
	private static double heightAt(double v, double ang, double distX) {
		ang=Math.toRadians(ang);
		double vx=v*Math.cos(ang);
		double vy=v*Math.sin(ang);
		double t=distX/vx;
		return vy*t-0.5*G*t*t;
	}

	private static boolean heightOK(double v, double ang) {
		for (int i=0;i<Buildings.length;i++) {
			double h=heightAt(v,ang,Buildings[i].start);
			if (h<Buildings[i].h-EPS) return false;
			h=heightAt(v,ang,Buildings[i].end);
			if (h<Buildings[i].h-EPS) return false;
		}
		return true;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			Buildings=new Building[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				double h=Double.parseDouble(st.nextToken());
				double d=Double.parseDouble(st.nextToken());
				Buildings[n]=new Building(h,0,0);
				if (n>0) {
					Buildings[n].start=Buildings[n-1].end;
					Buildings[n].end=Buildings[n].start+d;
				} else {
					Buildings[n].start=0;
					Buildings[n].end=d;
				}
			}

			double minAng=0.001;
			double maxAng=90.0;
			double midAng=0.0;
			double v=0.0;
			while (maxAng-minAng>EPS) {
				midAng=(minAng+maxAng)/2;
				v=Math.sqrt((Buildings[Buildings.length-1].end*G)/Math.sin(2*Math.toRadians(midAng)));
				if (heightOK(v,midAng)) maxAng=midAng;
				else minAng=midAng;
			}

			System.out.printf("%.2f %.2f\n",midAng,v);
		}
	}

}
