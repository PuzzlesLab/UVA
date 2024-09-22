import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Player {
		int x,y,v;
		
		public Player(StringTokenizer st) {
			this.x=Integer.parseInt(st.nextToken());
			this.y=Integer.parseInt(st.nextToken());
			this.v=Integer.parseInt(st.nextToken());
		}
	}
	
	private static class Ball {
		int a,b,c, d,e,f,g;

		public Ball(StringTokenizer st) {
			this.a=Integer.parseInt(st.nextToken());
			this.b=Integer.parseInt(st.nextToken());
			this.c=Integer.parseInt(st.nextToken());
			this.d=Integer.parseInt(st.nextToken());
			this.e=Integer.parseInt(st.nextToken());
			this.f=Integer.parseInt(st.nextToken());
			this.g=Integer.parseInt(st.nextToken());
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int P=Integer.parseInt(s.substring("PLAYERS=".length()));
			Player [] players=new Player[P];
			for (int p=0;p<P;p++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				players[p]=new Player(st);
			}
			
			int B=Integer.parseInt(br.readLine().substring("BALLS=".length()));
			Ball [] balls=new Ball[B];
			for (int b=0;b<B;b++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				balls[b]=new Ball(st);
			}
			
			StringBuilder sb=new StringBuilder();
			for (int b=0;b<B;b++) {
				// 0=at^2+bt+c, find t.
				int discri=balls[b].b*balls[b].b-4*balls[b].a*balls[b].c;
				int t=(int)Math.ceil((-balls[b].b-Math.sqrt(discri))/(2.0*balls[b].a));
				int bx=balls[b].d*t+balls[b].e;
				int by=balls[b].f*t+balls[b].g;

				boolean caught=false;
				for (int p=0;p<P && !caught;p++) {
					int dx=bx-players[p].x;
					int dy=by-players[p].y;
					double dist=Math.sqrt(dx*dx+dy*dy);
					double timeToCatch=dist/players[p].v;
					caught|=timeToCatch<=t;
				}
				
				sb.append("Ball ");
				sb.append(b+1);
				sb.append(" was ");
				if (bx<0 || by<0) sb.append("foul");
				else if (caught) sb.append("caught");
				else sb.append("safe");
				sb.append(" at (");
				sb.append(bx);
				sb.append(',');
				sb.append(by);
				sb.append(")\n");
			}
			System.out.print(sb.toString());
		}
 	}

}
