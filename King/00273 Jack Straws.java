import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple {
		int x, y;
		
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}

		public int cross(Tuple t) {
			return this.x*t.y-this.y*t.x;
		}
		
		public Tuple toVec(Tuple t) {
			return new Tuple(t.x-this.x,t.y-this.y);
		}
	}

	private static class Straw {
		Tuple p1, p2;

		public Straw(int x1, int y1, int x2, int y2) {
			this.p1=new Tuple(x1,y1);
			this.p2=new Tuple(x2,y2);
		}
		
		private static boolean hasPoint(Tuple p, Tuple q, Tuple r) {
			return r.x>=Math.min(p.x,q.x) && r.x<=Math.max(p.x,q.x) && r.y>=Math.min(p.y,q.y) && r.y<=Math.max(p.y,q.y);
		}

		private int ccw(Tuple r) {
			Tuple vecPQ=this.p1.toVec(this.p2);
			Tuple vecPR=this.p1.toVec(r);
			int cp=vecPQ.cross(vecPR);
			if (cp==0) return 0;
			return cp>0?1:2;
		}

		public boolean isConnected(Straw s) {
			int v1=this.ccw(s.p1);
			int v2=this.ccw(s.p2);
			int v3=s.ccw(this.p1);
			int v4=s.ccw(this.p2);
			
			if (v1!=v2 && v3!=v4) return true;
			if (v1==0 && Straw.hasPoint(this.p1,this.p2,s.p1)) return true;
			if (v2==0 && Straw.hasPoint(this.p1,this.p2,s.p2)) return true;
			if (v3==0 && Straw.hasPoint(s.p1,s.p2,this.p1)) return true;
			if (v4==0 && Straw.hasPoint(s.p1,s.p2,this.p2)) return true;
			return false;
			
		}
	}

	private static int getParent(int [] parent, int i) {
		if (parent[i]==i) return i;
		return parent[i]=getParent(parent,parent[i]);
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine(); // Empty.
			int N=Integer.parseInt(br.readLine());
			Straw [] straws=new Straw[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				straws[n]=new Straw(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			
			int [] parent=new int [N];
			for (int n=0;n<N;n++) parent[n]=n;
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) {
				int p1=getParent(parent,n);
				int p2=getParent(parent,n2);
				if (p1==p2) continue;
				if (!straws[n].isConnected(straws[n2])) continue;
				if (p1>p2) parent[p1]=p2;
				else parent[p2]=p1;
			}

			boolean first=true;
			while (true) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				if (a==0 || b==0) break;
				
				if (first) {
					if (tc>0) System.out.println();
					first=false;
				}
				System.out.println(getParent(parent,a-1)==getParent(parent,b-1) ? "CONNECTED" : "NOT CONNECTED");
			}
		}
	}

}
