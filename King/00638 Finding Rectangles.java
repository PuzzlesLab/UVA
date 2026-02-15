import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	private static class Point implements Comparable<Point> {
		char c;
		int x, y;
		int xlI, ylI;
		String key;
		
		public Point(char c, int x, int y) {
			this.c=c;
			this.x=x;
			this.y=y;
			
			StringBuilder sb=new StringBuilder();
			sb.append(this.x);
			sb.append(',');
			sb.append(this.y);
			this.key=sb.toString();
		}
		
		public int compareTo(Point p) {
			return (this.x!=p.x) ? this.x-p.x : this.y-p.y;
		}
		
		public String toString () {
			return this.key;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Point [] points=new Point[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Point(st.nextToken().charAt(0),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			Arrays.sort(points);
			
			HashMap<Integer,ArrayList<Point>> pointsOnX=new HashMap<>();
			HashMap<Integer,ArrayList<Point>> pointsOnY=new HashMap<>();
			HashMap<String,Point> pointMap=new HashMap<>();
			for (int n=0;n<N;n++) {
				Point p=points[n];
				if (!pointsOnX.containsKey(p.x)) pointsOnX.put(p.x,new ArrayList<>());
				pointsOnX.get(p.x).add(p);
				p.xlI=pointsOnX.get(p.x).size()-1;
				
				if (!pointsOnY.containsKey(p.y)) pointsOnY.put(p.y,new ArrayList<>());
				pointsOnY.get(p.y).add(p);
				p.ylI=pointsOnY.get(p.y).size()-1;
				
				pointMap.put(p.key,p);
			}
			
			ArrayList<Point []> solList=new ArrayList<>();
			for (int n=0;n<N;n++) {
				Point p1=points[n];
				ArrayList<Point> hPoints=pointsOnY.get(p1.y);
				ArrayList<Point> vPoints=pointsOnX.get(p1.x);
				for (int n2=p1.ylI+1;n2<hPoints.size();n2++) {
					Point p2=hPoints.get(n2);
					for (int n3=p1.xlI+1;n3<vPoints.size();n3++) {
						Point p3=vPoints.get(n3);
						
						Point p4=new Point('!',p2.x,p3.y);
						if (pointMap.containsKey(p4.key)) {
							solList.add(new Point [] {p3,pointMap.get(p4.key),p2,p1});
							continue;
						}
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Point set ");
			sb.append(tc++);
			if (solList.isEmpty()) sb.append(": No rectangles");
			else {
				sb.append(":\n");
				
				String [] solStrList=new String [solList.size()];
				for (int i=0;i<solList.size();i++) {
					Point [] rectangle=solList.get(i);
					StringBuilder curr=new StringBuilder();
					for (int i2=0;i2<rectangle.length;i2++) curr.append(rectangle[i2].c);
					solStrList[i]=curr.toString();
				}
				Arrays.sort(solStrList);

				for (int i=0;i<solStrList.length;i++) {
					if (i>0 && i%10==0) sb.append('\n');
					sb.append(' ');
					sb.append(solStrList[i]);
				}
			}
			System.out.println(sb);
		}
	}

}