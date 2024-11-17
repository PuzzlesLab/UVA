import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		double x, y, rSq;
		
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
		
		public double distSq(Tuple t) {
			double dx=this.x-t.x;
			double dy=this.y-t.y;
			return dx*dx+dy*dy;
		}
	}
	
	private static boolean inRange(double x, double y, Tuple [] routers) {
		for (int i=0;i<routers.length;i++) {
			double distSq=routers[i].distSq(new Tuple(x,y));
			if (distSq<=routers[i].rSq) return true;
		}
		return false;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int L=Integer.parseInt(st.nextToken());
			
			Tuple [] routers=new Tuple[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				routers[n]=new Tuple(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				routers[n].rSq=Double.parseDouble(st.nextToken());
				routers[n].rSq*=routers[n].rSq;
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(":\n");
			
			for (int l=0;l<L;l++) {
				st=new StringTokenizer(br.readLine());
				double x=Double.parseDouble(st.nextToken());
				double y=Double.parseDouble(st.nextToken());
				sb.append(inRange(x,y,routers)?"Yes\n":"No\n");
			}
			System.out.print(sb);
		}
 	}
}
