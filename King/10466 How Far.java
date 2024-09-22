import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Planet {
		double radius, interval, x, y;
		
		public Planet(double r, double i) {
			this.radius=r;
			this.interval=i;			
			this.x=0.0;
			this.y=0.0;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			Planet [] planets=new Planet [N+1];
			planets[0]=new Planet(0.0,0.0);
			for (int n=1;n<=N;n++) {
				st=new StringTokenizer(br.readLine());
				planets[n]=new Planet(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				
				double rm=(T/planets[n].interval)*2*Math.PI;
				planets[n].x=planets[n-1].x+planets[n].radius*Math.cos(rm);
				planets[n].y=planets[n-1].y+planets[n].radius*Math.sin(rm);
			}

			StringBuilder sb=new StringBuilder();
			for (int n=1;n<=N;n++) sb.append(String.format("%.4f ",Math.hypot(planets[n].x,planets[n].y)));
			if (sb.length()>0) sb.setLength(sb.length()-1);
			System.out.println(sb);
		}
 	}

}
