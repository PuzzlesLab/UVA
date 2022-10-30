import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Tuple implements Comparable<Tuple> {
		int dist, time;
		
		public Tuple(int time, int dist) {
			this.time=time;
			this.dist=dist;
		}
		
		public Tuple(Tuple t1, Tuple t2) {
			this.dist=t1.dist+t2.dist;
			this.time=t1.time+t2.time;
		}

		public int compareTo(Tuple t) { // Time first, only distance (question stated the reverse! :/)
			if (this.time!=t.time) return this.time-t.time;
			return this.dist-t.dist;
		}
	}

	private static Tuple minTuple(Tuple t1, Tuple t2) {
		if (t1==null) return t2;
		if (t2==null) return t1;
		return (t1.compareTo(t2)>0) ? t2 : t1;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			
			Tuple [][] best=new Tuple[X+1][X+1];
			for (int x=1;x<=X;x++) best[x][x]=new Tuple(0,0);

			for (int y=0;y<Y;y++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				best[a][b]=minTuple(best[a][b],new Tuple(c,d));
				best[b][a]=minTuple(best[b][a],new Tuple(c,d));
			}

			for (int k=1;k<=X;k++) for (int i=1;i<=X;i++) for (int j=1;j<=X;j++) {
				if (best[i][k]==null || best[k][j]==null) continue;
				Tuple test=new Tuple(best[i][k],best[k][j]);
				if (best[i][j]==null || best[i][j].compareTo(test)>0) best[i][j]=test;
			}

			if (tc>0) System.out.println();

			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				if (best[from][to]==null) System.out.println("No Path.");
				else System.out.printf("Distance and time to reach destination is %d & %d.\n",best[from][to].dist,best[from][to].time);
			}
		}
	}

}