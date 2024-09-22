import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static class Light implements Comparable<Light> {
		int x, y, h;
		double m;
		
		public Light(int x, int y, int h) {
			this.x=x;
			this.y=y;
			this.h=h;
			if (this.x==0) this.m=(this.y<0? -1 : 1) * 999999.9; // Hope there is no test case uses this lol.
			else if (this.y==0) this.m=(this.x<0? -1 : 1) * 999999.8; // Hope there is no test case uses this lol.
			else this.m=(this.y+0.0)/this.x;
		}
		
		public int compareTo(Light l) {
			if (this.m!=l.m) return Double.compare(this.m,l.m);
			if (Math.abs(this.x)!=Math.abs(l.x)) return Math.abs(this.x)-Math.abs(l.x);
			if (Math.abs(this.y)!=Math.abs(l.y)) return Math.abs(this.y)-Math.abs(l.y);
			return Double.compare(this.h,l.h);
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Light [] lights=new Light[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				lights[n]=new Light(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(lights);
			
			ArrayList<Light> hidden=new ArrayList<>();
			double lastM=-999999.0;
			int lastH=-1;
			for (int i=0;i<lights.length;i++) {
				if (lights[i].m!=lastM) {
					lastM=lights[i].m;
					lastH=lights[i].h;
				} else if (lights[i].h>lastH) lastH=lights[i].h;
				else hidden.add(lights[i]);
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Data set ");
			sb.append(tc++);
			sb.append(":\n");
			if (hidden.isEmpty()) sb.append("All the lights are visible.");
			else {
				sb.append("Some lights are not visible:\n");
				Collections.sort(hidden, (a, b) -> {
					if (a.x!=b.x) return a.x-b.x;
					return a.y-b.y;
				});
				for (int i=0;i<hidden.size();i++) {
					Light l=hidden.get(i);
					sb.append("x = ");
					sb.append(l.x);
					sb.append(", y = ");
					sb.append(l.y);
					sb.append(";\n");
				}
				sb.setLength(sb.length()-2);
				sb.append('.');
			}
			System.out.println(sb.toString());
		}
 	}

}