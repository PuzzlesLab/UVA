import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static final double EPS=0.000001;

	private static class Tuple {
		double x, y;
		
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
	}

	private static Tuple [] getCoveredCircle(Tuple t1, Tuple t2, double r) {
		double dx=t1.x-t2.x;
		double dy=t1.y-t2.y;
		double d2=dx*dx+dy*dy;
		double det=r*r/d2-0.25;
		if (det<-EPS) return null;
		double h=Math.sqrt(det);
		Tuple[] ans=new Tuple[2];
		ans[0]=new Tuple((t1.x+t2.x)*0.5+(t1.y-t2.y)*h,(t1.y+t2.y)*0.5+(t2.x-t1.x)*h);
		ans[1]=new Tuple((t1.x+t2.x)*0.5+(t2.y-t1.y)*h,(t1.y+t2.y)*0.5+(t1.x-t2.x)*h);
		return ans;
	}
	
	private static int countInCircle(Tuple center, ArrayList<Tuple> list, double maxDist) {
		double sq=maxDist*maxDist+EPS;
		int count=0;
		for (int i=0;i<list.size();i++) {
			double dx=list.get(i).x-center.x;
			double dy=list.get(i).y-center.y;
			double d=dx*dx+dy*dy;
			if (d<=sq) count++;
		}
		return count;
	}

	public static void main(String [] args) throws Exception {
		final double RAD=2.5;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		br.readLine(); // Empty
		for (int tc=0;tc<TC;tc++) {
			ArrayList<Tuple> chips=new ArrayList<>();
			while (true) {
				String s=br.readLine();
				if (s==null || s.equals("")) break;
				StringTokenizer st=new StringTokenizer(s);
				chips.add(new Tuple(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())));
			}

			int ans=0;
			if (chips.size()<=1) ans=chips.size();
			else {
				for (int i=0;i<chips.size();i++) {
					Tuple c1=chips.get(i);
					ans=Math.max(ans,countInCircle(c1,chips,RAD));
	
					for (int i2=i+1;i2<chips.size();i2++) {
						Tuple c2=chips.get(i2);
						Tuple [] t=getCoveredCircle(c1,c2,RAD);
						if (t!=null) {
							ans=Math.max(ans,countInCircle(t[0],chips,RAD));
							ans=Math.max(ans,countInCircle(t[1],chips,RAD));
						}
					}
				}
			}

			if (tc>0) System.out.println();
			System.out.println(ans);

		}
 	}
}
