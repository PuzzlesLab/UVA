import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Position {
		double x, y;
		ArrayList<Position> adjList=new ArrayList<>();
		boolean visited;
		Position pair;
	}

	private static double distBetween(Position p1, Position p2) {
		double dx=p1.x-p2.x;
		double dy=p1.y-p2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}

	private static boolean mcbm(Position curr) {
		if (curr.visited) return false;
		
		curr.visited=true;
		for (int i=0;i<curr.adjList.size();i++) {
			Position next=curr.adjList.get(i);
			if (next.visited) continue;
			
			if (next.pair==null || mcbm(next.pair)) {
				next.pair=curr;
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int S=Integer.parseInt(st.nextToken());
			double V=Integer.parseInt(st.nextToken());
			
			Position [] gophers=new Position[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				gophers[n]=new Position();
				gophers[n].x=Double.parseDouble(st.nextToken());
				gophers[n].y=Double.parseDouble(st.nextToken());
			}
			
			Position [] holes=new Position[M];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				holes[m]=new Position();
				holes[m].x=Double.parseDouble(st.nextToken());
				holes[m].y=Double.parseDouble(st.nextToken());
			}

			for (int n=0;n<N;n++) {
				for (int m=0;m<M;m++) {
					double timeNeeded=distBetween(gophers[n],holes[m])/V;
					if (timeNeeded<=S) gophers[n].adjList.add(holes[m]);
				}
			}

			int safe=0;
			for (int n=0;n<N;n++) {
				for (int g=0;g<N;g++) gophers[g].visited=false;
				if (mcbm(gophers[n])) safe++;
			}
			System.out.println(N-safe);
		}
	}

}
