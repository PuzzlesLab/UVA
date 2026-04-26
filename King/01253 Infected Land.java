import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;

class Main {

	private static int N;
	private static int MapSize;

	private static class State {
		int vehicle, map, step;
		
		public State(int v, int m, int s) {
			this.vehicle=v;
			this.map=m;
			this.step=s;
		}
		
		public long key() {
			long base=1<<MapSize;
			return this.vehicle*base+this.map;
		}
	}

	private static int flatten(int x, int y) {
		return x*N+y;
	}

	private static int[][] countAdjInf(int map) {
		int [][] ans=new int [N][N];
		for (int x=0;x<N;x++) for (int y=0;y<N;y++) {
			int i=flatten(x,y);
			if ((map&(1<<i))==0) continue;

			for (int dx=-1;dx<=1;dx++) for (int dy=-1;dy<=1;dy++) if (dx!=0 || dy!=0) {
				int nx=x+dx;
				int ny=y+dy;
				if (nx>=0 && nx<N && ny>=0 && ny<N) ans[nx][ny]++;
			}
		}
		return ans;
	}

	private static boolean withinRange(int x1, int y1, int x2, int y2) {
		return Math.abs(x2-x1)<=1 && Math.abs(y2-y1)<=1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			N=Integer.parseInt(s);

			int map=0;
			int vehicle=0;
			for (int n=0;n<N;n++) {
				s=br.readLine();
				for (int n2=0;n2<N;n2++) {
					if (s.charAt(n2)=='#') map|=(1<<flatten(n,n2));
					else if (s.charAt(n2)=='@') vehicle=flatten(n,n2);
				}
			}
			MapSize=N*N;
			
			int ans=-1;
			State init=new State(vehicle,map,0);
			HashSet<Long> visited=new HashSet<>();
			LinkedList<State> q=new LinkedList<>();
			q.addLast(init);
			visited.add(init.key());

			while (!q.isEmpty()) {
				State curr=q.removeFirst();
				if (curr.map==0) {
					ans=curr.step;
					break;
				}
				if (curr.step>=10) continue;

				int [][] adjInf=countAdjInf(curr.map);
				for (int dx=-1;dx<=1;dx++) for (int dy=-1;dy<=1;dy++) if (dx!=0 || dy!=0) {
					int nx=(curr.vehicle/N)+dx;
					int ny=(curr.vehicle%N)+dy;
					if (nx<0 || nx>=N || ny<0 || ny>=N) continue; // Out of bound.

					int nI=flatten(nx,ny);
					if ((curr.map&(1<<nI))!=0) continue; // Cannot enter infected area.
					
					int nextMap=0;
					for (int n=0;n<N;n++) for (int n2=0;n2<N;n2++) {
						int cI=flatten(n,n2);
						if (cI==nI) continue; // New vehicle won't be infected.

						boolean currInf=(curr.map&(1<<cI))!=0;
						int adjInfCount=adjInf[n][n2];
						if (withinRange(nx,ny,n,n2)) adjInfCount++; // New vehicle is one of the infection source.
						if (currInf && (adjInfCount==2 || adjInfCount==3)) nextMap|=(1<<cI);
						else if (!currInf && adjInfCount==3) nextMap|=(1<<cI);;
					}

					State nS=new State(nI,nextMap,curr.step+1);
					if (!visited.contains(nS.key())) {
						visited.add(nS.key());
						q.addLast(nS);
					}
				}
			}
			
			System.out.println(ans);
		}
	}

}