import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y;
		
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public String toString() {
			return this.x+","+this.y;
		}
	}

	private static Tuple [] getCircle(Tuple t1, Tuple t2, double r) {
		double dx=t1.x-t2.x;
		double dy=t1.y-t2.y;
		double distSq=dx*dx+dy*dy;
		double det=(r*r)/distSq-0.25;
		if (det<0) return null;
		
		double h=Math.sqrt(det);
		Tuple [] c=new Tuple[2];
		c[0]=new Tuple((t1.x+t2.x)*0.5+(t1.y-t2.y)*h,(t1.y+t2.y)*0.5+(t2.x-t1.x)*h);
		c[1]=new Tuple((t1.x+t2.x)*0.5+(t2.y-t1.y)*h,(t1.y+t2.y)*0.5+(t1.x-t2.x)*h);
		return c;
	}
	
	private static boolean coversAll(Tuple [] points, Tuple c, double r) {
		for (int i=0;i<points.length;i++) {
			double dx=points[i].x-c.x;
			double dy=points[i].y-c.y;
			double distSq=dx*dx+dy*dy;
			if (distSq>r*r+0.0001) return false;
		}
		return true;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Tuple [] vertices=new Tuple[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				vertices[n]=new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			double R=Double.parseDouble(br.readLine());

			boolean flag=false;
			if (N==1) flag=true;
			for (int n=0;n<N && !flag;n++) for (int n2=n+1;n2<N && !flag;n2++) {
				Tuple [] curr=getCircle(vertices[n],vertices[n2],R);
				if (curr==null) continue;
				
				for (int t=0;t<curr.length;t++) {
					flag=coversAll(vertices,curr[t],R);
					if (flag) break;
				}
			}
			System.out.println(flag?"The polygon can be packed in the circle.":"There is no way of packing that polygon.");
		}
 	}
}
