import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int id;
		String name;
		double lat, lon;
		
		public Tuple(int id, String name, double lat, double lon) {
			this.id=id;
			this.name=name;
			this.lat=lat/180*3.141592653589793;
			this.lon=lon/180*3.141592653589793;
		}
		
		public int dist(Tuple t) {
			final double R=6378;
			double ans=R*Math.acos(Math.cos(this.lat)*Math.cos(this.lon)*Math.cos(t.lat)*Math.cos(t.lon)+Math.cos(this.lat)*Math.sin(this.lon)*Math.cos(t.lat)*Math.sin(t.lon)+Math.sin(this.lat)*Math.sin(t.lat));
			return (int)(ans+0.5);
		}
	}

	public static void main(String [] args) throws Exception {
		final int NULL_DIST=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			
			int [][] dist=new int [N][N];
			for (int n=0;n<N;n++) Arrays.fill(dist[n],NULL_DIST);
			
			Tuple [] cities=new Tuple[N];
			HashMap<String,Tuple> cityMap=new HashMap<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				cities[n]=new Tuple(n,st.nextToken(),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				cityMap.put(cities[n].name,cities[n]);
			}

			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				Tuple c1=cityMap.get(st.nextToken());
				Tuple c2=cityMap.get(st.nextToken());
				dist[c1.id][c2.id]=c1.dist(c2);
			}
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) dist[i][j]=Math.min(dist[i][j],dist[i][k]+dist[k][j]);
			
			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append('\n');
			sb.append("Case #");
			sb.append(tc++);
			sb.append('\n');
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int c1=cityMap.get(st.nextToken()).id;
				int c2=cityMap.get(st.nextToken()).id;
				if (dist[c1][c2]==NULL_DIST) sb.append("no route exists");
				else {
					sb.append(dist[c1][c2]);
					sb.append(" km");
				}
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}