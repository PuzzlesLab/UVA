import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Ring {
		double x, y, r;
		
		public Ring(double x, double y, double r) {
			this.x=x;
			this.y=y;
			this.r=r;
		}
		
		public boolean isOverlap(Ring r) {
			double dx=this.x-r.x;
			double dy=this.y-r.y;
			double dist=Math.sqrt(dx*dx+dy*dy);
			return dist>Math.abs(this.r-r.r) && dist<this.r+r.r;
		}
	}

	private static int getParent(int [] parent, int n) {
		if (parent[n]==n) return n;
		return (parent[n]=getParent(parent,parent[n]));
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("-1")) {
			int N=Integer.parseInt(s);
			Ring [] rings=new Ring[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				rings[n]=new Ring(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}

			int [] parent=new int [N];
			for (int n=0;n<N;n++) parent[n]=n;
			
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) {
				if (rings[n].isOverlap(rings[n2])) {
					int p1=getParent(parent,n);
					int p2=getParent(parent,n2);
					if (p1>p2) {
						parent[p1]=p2;
					} else if (p1<p2) {
						parent[p2]=p1;
					}
				}

			}

			int [] count=new int [N];
			for (int n=0;n<N;n++) count[getParent(parent,n)]++;
			int ans=0;
			for (int n=0;n<N;n++) ans=Math.max(ans,count[n]);
			System.out.printf("The largest component contains %d ring%s.\n", ans, ans==1?"":"s");
		}
	}

}
