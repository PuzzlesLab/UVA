import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static class Bouy implements Comparable<Bouy> {
		public static Bouy Ref;
		int x, y, z, idx;
		
		public Bouy(int x, int y, int z, int idx) {
			this.x=x;
			this.y=y;
			this.z=z;
			this.idx=idx;
		}

		public Bouy(StringTokenizer st, int idx) {
			this.x=Integer.parseInt(st.nextToken());
			this.y=Integer.parseInt(st.nextToken());
			this.z=Integer.parseInt(st.nextToken());
			this.idx=idx;
		}

		public double dist(Bouy b) {
			int dx=this.x-b.x;
			int dy=this.y-b.y;
			int dz=this.z-b.z;
			return Math.sqrt(dx*dx+dy*dy+dz*dz);
		}

		public int compareTo(Bouy b) {
			int d=Double.compare(Ref.dist(this), Ref.dist(b));
			if (d!=0) return d;
			return this.idx-b.idx;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double fuel=Integer.parseInt(st.nextToken());
			double fuelRate=Integer.parseInt(st.nextToken());
			double speed=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			if (fuel==0 && fuelRate==0 && speed==0 && N==0) break;
			
			HashSet<Bouy> bouys=new HashSet<>();
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				bouys.add(new Bouy(st,n));
			}
			
			Bouy.Ref=new Bouy(0,0,1,0);
			double traveled=0.0;
			double timeUsed=0.0;
			double fromHome=0.0;
			boolean flag=true;
			while (!bouys.isEmpty()) {
				Bouy nearest=null;
				for (Bouy b: bouys) {
					if (nearest==null) {
						nearest=b;
						continue;
					}
					
					if (nearest.compareTo(b)>0) nearest=b;
				}

				double nearestDist=Bouy.Ref.dist(nearest);
				double time=nearestDist/speed;
				double fuelUsage=fuelRate*time;
				if (flag) {
					if (fuelUsage>fuel) {
						double remTime=fuel/fuelRate;
						double remDist=speed*remTime;
						traveled+=remDist;
						timeUsed+=remTime;
						fromHome+=(nearestDist-remDist);
						flag=false;
						fuel=-99999.0;
					} else {
						traveled+=nearestDist;
						timeUsed+=time;
						fuel-=fuelUsage;
					}
				} else {
					fromHome+=nearestDist;
				}

				bouys.remove(nearest);
				Bouy.Ref=nearest;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Mission ");
			sb.append(tc++);
			sb.append(": ");
			if (flag) {
				sb.append("SUCCESS!!");
				sb.append(String.format(" Time: %.2f ", timeUsed));
				sb.append(String.format(" Traveled: %.2f ", traveled));
				sb.append(String.format(" Fuel Left: %.2f", fuel));
			} else {
				sb.append("FAILURE!!");
				sb.append(String.format(" Traveled: %.2f ", traveled));
				sb.append(String.format(" From Home: %.2f", fromHome));
			}
			System.out.println(sb);
		}
 	}

}
