import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
		
		public int compareTo(Tuple t) {
			return (this.x!=t.x) ? this.x-t.x : this.y-t.y;
		}
	}

	private static boolean isLess(Tuple sol11, Tuple sol12, Tuple sol21, Tuple sol22) {
		int d1=sol11.compareTo(sol21);
		if (d1<0) return true;
		if (d1>0) return false;
		int d2=sol12.compareTo(sol22);
		return d2<0;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int C=Integer.parseInt(br.readLine());
		for (int c=0;c<C;c++) {
			int N=Integer.parseInt(br.readLine());
			Tuple [] points=new Tuple [N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new Tuple(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			double a=0.0;
			for (int n=0;n<N;n++) a+=points[n].x*points[(n+1)%N].y-points[(n+1)%N].x*points[n].y;
			a=Math.abs(a)/2;
			
			Tuple pAns1=null;
			Tuple pAns2=null;
			double ans=1000000;
			// Horizontal - Vertical.
			for (int n1=0;n1<N;n1++) for (int d2=1;d2<N;d2++) {
				int n2=(n1+d2)%N;
				if (n1==n2 || points[n1].y!=points[n2].y) continue;

				for (int d3=1;d3<N;d3++) {
					int n3=(n2+d3)%N;
					if (n1==n3 || n2==n3 || points[n2].x!=points[n3].x) continue;

					Tuple p4=new Tuple(points[n1].x,points[n3].y);
					int dx=Math.abs(p4.x-points[n3].x);
					int dy=Math.abs(p4.y-points[n1].y);
					double diff=Math.abs(a-dx*dy);
					if (diff<=ans) {
						Tuple [] recPoints=new Tuple[] {points[n1],points[n2],points[n3],p4};
						Arrays.sort(recPoints);
						if (diff<ans || isLess(recPoints[0],recPoints[3],pAns1,pAns2)) {
							ans=diff;
							pAns1=recPoints[0];
							pAns2=recPoints[3];
						}
					}
				}
			}
			// Vertical - Horizontal.
			for (int n1=0;n1<N;n1++) for (int d2=1;d2<N;d2++) {
				int n2=(n1+d2)%N;
				if (n1==n2 || points[n1].x!=points[n2].x) continue;
				
				for (int d3=1;d3<N;d3++) {
					int n3=(n2+d3)%N;
					if (n1==n3 || n2==n3 || points[n2].y!=points[n3].y) continue;

					Tuple p4=new Tuple(points[n3].x,points[n1].y);
					int dx=Math.abs(p4.x-points[n1].x);
					int dy=Math.abs(p4.y-points[n3].y);
					double diff=Math.abs(a-dx*dy);
					if (diff<=ans) {
						Tuple [] recPoints=new Tuple[] {points[n1],points[n2],points[n3],p4};
						Arrays.sort(recPoints);
						if (diff<ans || isLess(recPoints[0],recPoints[3],pAns1,pAns2)) {
							ans=diff;
							pAns1=recPoints[0];
							pAns2=recPoints[3];
						}
					}
				}
			}
			
			
			StringBuilder sb=new StringBuilder();
			sb.append(String.format("%.1f",a));
			sb.append(' ');
			sb.append(pAns1.x);
			sb.append(' ');
			sb.append(pAns1.y);
			sb.append(' ');
			sb.append(pAns2.x);
			sb.append(' ');
			sb.append(pAns2.y);
			System.out.println(sb);
		}
	}

}
