import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {

	private static final double EPS=1e-6;
	private static final double MAX_SIDE=100;

	private static class Triangle implements Comparable<Triangle> {
		double [] sides;

		public Triangle(double a, double b, double c) {
			this.sides=new double [] {a,b,c};
			Arrays.sort(this.sides);
			double f=MAX_SIDE/this.sides[2];
			this.sides[0]*=f;
			this.sides[1]*=f;
			this.sides[2]*=f;
		}

		private double getCosAngle(int sideAI, int sideBI, int sideCI) {
			double d1=this.sides[sideBI]*this.sides[sideBI] + this.sides[sideCI]*this.sides[sideCI] - this.sides[sideAI]*this.sides[sideAI];
			double d2=2*this.sides[sideBI]*this.sides[sideCI];
			return d1/d2;
		}

		private double getLen(double a, double b, double cosAng) {
			return Math.sqrt(a*a+b*b-2*a*b*cosAng);
		}

		public Triangle[] split() {
			double cosAng=this.getCosAngle(0,1,2);
			double newLen=this.getLen(this.sides[1],this.sides[2]/2,cosAng);
			return new Triangle[] {
				new Triangle(this.sides[0],this.sides[2]/2,newLen),
				new Triangle(this.sides[1],this.sides[2]/2,newLen),
			};
		}
		
		public int compareTo(Triangle t) {
			for (int i=0;i<this.sides.length;i++) if (Math.abs(this.sides[i]-t.sides[i])>EPS) {
				return Double.compare(this.sides[i],t.sides[i]);
			}
			return 0;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			LinkedList<Triangle> q=new LinkedList<>();
			q.addLast(new Triangle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
			TreeSet<Triangle> visited=new TreeSet<>();
			int count=0;
			while (!q.isEmpty()) {
				Triangle curr=q.removeFirst();
				count++;

				Triangle [] splitted=curr.split();
				for (int i=0;i<splitted.length;i++) {
					Triangle t=splitted[i];
					if (!visited.contains(t)) {
						visited.add(t);
						q.addLast(t);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Triangle ");
			sb.append(n);
			sb.append(": ");
			sb.append(count-1);
			System.out.println(sb.toString());
		}
	}

}