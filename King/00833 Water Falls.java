import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static class Line {
		int x1, y1, x2, y2;
		double a,b,c;
		boolean enabled;
		
		public Line(StringTokenizer st) {
			this.x1=Integer.parseInt(st.nextToken());
			this.y1=Integer.parseInt(st.nextToken());
			this.x2=Integer.parseInt(st.nextToken());
			this.y2=Integer.parseInt(st.nextToken());
			
			if (this.x1>this.x2) {
				int tx=this.x1;
				int ty=this.y1;
				this.x1=this.x2;
				this.y1=this.y2;
				this.x2=tx;
				this.y2=ty;
			}
			
			if (this.x1==this.x2) {
				this.a=1.0;
				this.b=0.0;
				this.c=-this.x1;
			} else {
				this.a=-(this.y1-this.y2)/(this.x1-this.x2+0.0);
				this.b=1.0;
				this.c=-(this.a*this.x1)-this.y1;
			}
		}
		
		public double countY(double x) {
			return (this.a*x+this.c)/-this.b;
		}
	}

	private static class Particle {
		int x, y;

		public Particle(StringTokenizer st) {
			this.x=Integer.parseInt(st.nextToken());
			this.y=Integer.parseInt(st.nextToken());
		}
	}
	
	private static void simulate(Line [] lines, Particle water) {
		for (int i=0;i<lines.length;i++) lines[i].enabled=true;

		while (true) {
			Line dest=null;
			double destY=-100000.0;
			for (int i=0;i<lines.length;i++) {
				if (!lines[i].enabled) continue;
				if (water.x<lines[i].x1 || water.x>lines[i].x2) continue;
				double currY=lines[i].countY(water.x);
				
				if (currY>water.y) continue;
				if (dest==null || currY>destY) {
					destY=currY;
					dest=lines[i];
				}
			}
			if (dest==null) break;
			
			dest.enabled=false;
			if (dest.a<0) {
				water.x=dest.x1;
				water.y=dest.y1;
			} else {
				water.x=dest.x2;
				water.y=dest.y2;
			}
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine(); // Empty line.
			int L=Integer.parseInt(br.readLine());
			Line [] lines=new Line [L];
			for (int l=0;l<L;l++) lines[l]=new Line(new StringTokenizer(br.readLine()));
			
			int P=Integer.parseInt(br.readLine());
			Particle [] waters=new Particle[P];
			for (int p=0;p<P;p++) waters[p]=new Particle(new StringTokenizer(br.readLine()));
			
			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			for (int p=0;p<P;p++) {
				simulate(lines,waters[p]);
				sb.append(waters[p].x);
				sb.append('\n');
			}
			System.out.print(sb);
		}
 	}

}
