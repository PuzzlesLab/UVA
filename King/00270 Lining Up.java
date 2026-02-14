import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		int x, y;
		String key;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
			
			StringBuilder sb=new StringBuilder();
			sb.append(this.x);
			sb.append(',');
			sb.append(this.y);
			this.key=sb.toString();
		}
		
		public int compareTo(Tuple t) {
			return (this.x!=t.x) ? this.x-t.x : this.y-t.y;
		}
	}

	private static int gcd(int a, int b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		br.readLine(); // Empty
		for (int tc=0;tc<TC;tc++) {
			ArrayList<Tuple> points=new ArrayList<>();
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				StringTokenizer st=new StringTokenizer(s);
				points.add(new Tuple(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			}

			if (points.size()<=2) {
				System.out.println(points.size());
				continue;
			}

			HashMap<Integer,Integer> xValues=new HashMap<>();
			HashMap<Integer,Integer> yValues=new HashMap<>();
			HashSet<String> existsPoint=new HashSet<>();
			for (int i=0;i<points.size();i++) {
				Tuple p=points.get(i);
				existsPoint.add(p.key);
				xValues.put(p.x,xValues.getOrDefault(p.x,0)+1);
				yValues.put(p.y,yValues.getOrDefault(p.y,0)+1);
			}
			
			int ans=0;
			for (int i=0;i<points.size();i++) {
				Tuple p=points.get(i);
				ans=Math.max(ans,xValues.get(p.x));
				ans=Math.max(ans,yValues.get(p.y));
			}

			Collections.sort(points);
			for (int i=0;i<points.size();i++) for (int i2=i+1;i2<points.size();i2++) {
				Tuple start=points.get(i);
				Tuple end=points.get(i2);
				if (start.x==end.x || start.y==end.y) continue; // Already covered by xValues / yValues.

				int dx=end.x-start.x;
				int dy=end.y-start.y;
				int f=gcd(Math.abs(dx),Math.abs(dy));
				dx/=f;
				dy/=f;

				int count=0;
				int nx=start.x;
				int ny=start.y;
				while (true) {
					Tuple np=new Tuple(nx,ny);
					if (existsPoint.contains(np.key)) {
						count++;
						if (np.x==end.x && np.y==end.y) break;
					}
					nx+=dx;
					ny+=dy;
				}
				ans=Math.max(ans,count);
			}
			if (tc>0) System.out.println();
			System.out.println(ans);
		}
	}

}