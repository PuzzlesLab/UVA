import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static class City {
		String name;
		ArrayList<Departure> dep;
		public City(String n) {
			this.name=n;
			this.dep=new ArrayList<>();
		}
	}
	public static class Departure{
		int startHour, endHour;
		City dest;
		
		public Departure(int h, int e, City d) {
			this.startHour=h;
			this.endHour=e;
			this.dest=d;
		}
	}
	public static class Edge implements Comparable<Edge> {
		Departure dp;
		int bloodReq;
		public Edge (Departure dp, int b) {
			this.dp=dp;
			this.bloodReq=b;
		}
		public int compareTo(Edge e) {
			return this.bloodReq-e.bloodReq;
		}
	}
	
	private static int convertH(int h) { return (h+24-18)%24; }
	private static boolean hourInRange(int h) { return h>=0 && h<=12; }
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int R=Integer.parseInt(br.readLine());
			
			HashMap<String, City> cityMap=new HashMap<>();
			for (int r=0;r<R;r++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String c1s=st.nextToken();
				if (!cityMap.containsKey(c1s)) cityMap.put(c1s, new City(c1s));
				String c2s=st.nextToken();
				if (!cityMap.containsKey(c2s)) cityMap.put(c2s, new City(c2s));
				int startH=convertH(Integer.parseInt(st.nextToken()));
				int dur=Integer.parseInt(st.nextToken());
				int endH=(startH+dur)%24;
				if (dur<=12 && hourInRange(startH) && hourInRange(endH)) cityMap.get(c1s).dep.add(new Departure(startH, endH, cityMap.get(c2s)));
			}
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			City src=cityMap.get(st.nextToken());
			City dest=cityMap.get(st.nextToken());
			
			HashMap<City, Integer> sp=new HashMap<>();
			if (src!=null && dest!=null) {
				sp.put(src, 0);
				PriorityQueue<Edge> q=new PriorityQueue<>();
				q.add(new Edge(new Departure(0,0,src),0));
				while (!q.isEmpty()) {
					Edge e=q.poll();
					if (e.dp.dest.equals(dest)) break;
					for (Departure d : e.dp.dest.dep) if (e.bloodReq<sp.getOrDefault(d.dest, Integer.MAX_VALUE)) {
						int day=(e.dp.endHour<=d.startHour) ? 0 : 1;
						sp.put(d.dest, e.bloodReq+day);
						q.add(new Edge(d, e.bloodReq+day));
					}
				}
			}

			int ans=sp.getOrDefault(dest, -1);
			if (ans==-1) System.out.printf("Test Case %d.\nThere is no route Vladimir can take.\n", t);
			else System.out.printf("Test Case %d.\nVladimir needs %d litre(s) of blood.\n", t, ans);
		}
	}

}