import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static final double R=6378;

	private static class Point {
		double la, lo;
		
		public Point(double la, double lo) {
			this.la=la;
			this.lo=lo;
		}
	}

	private static double gcDist(Point a, Point b) {
		// https://en.wikipedia.org/wiki/Haversine_formula (R=1)
		double f=Math.PI/180;
		double aLa=a.la*f;
		double aLo=a.lo*f;
		double bLa=b.la*f;
		double bLo=b.lo*f;

		double va=Math.cos(aLa)*Math.cos(aLo)*Math.cos(bLa)*Math.cos(bLo);
		double vb=Math.cos(aLa)*Math.sin(aLo)*Math.cos(bLa)*Math.sin(bLo);
		double vc=Math.sin(aLa)*Math.sin(bLa);
		return R*Math.acos(va+vb+vc);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		ArrayList<Point> points=new ArrayList<>();
		HashMap<String,Integer> pointMap=new HashMap<>();
		while (!(s=br.readLine()).equals("#")) {
			StringTokenizer st=new StringTokenizer(s);
			String name=st.nextToken();
			Point p=new Point(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			points.add(p);
			pointMap.put(name,points.size()-1);
		}
		boolean [][] dpF=new boolean[points.size()][points.size()];
		int [][] dp=new int [points.size()][points.size()];
		while (!(s=br.readLine()).equals("# #")) {
			StringTokenizer st=new StringTokenizer(s);
			String from=st.nextToken();
			String to=st.nextToken();

			StringBuilder sb=new StringBuilder();
			sb.append(from);
			sb.append(" - ");
			sb.append(to);
			sb.append('\n');
			if (pointMap.containsKey(from) && pointMap.containsKey(to)) {
				int idx1=pointMap.get(from);
				int idx2=pointMap.get(to);
				if (!dpF[idx1][idx2]) {
					dpF[idx1][idx2]=true;
					dp[idx1][idx2]=(int)(gcDist(points.get(idx1),points.get(idx2))+0.5);
					dpF[idx2][idx1]=true;
					dp[idx2][idx1]=dp[idx1][idx2];
				}
				sb.append(dp[idx1][idx2]);
				sb.append(" km");
			} else sb.append("Unknown");
			System.out.println(sb);
		}
	}

}
