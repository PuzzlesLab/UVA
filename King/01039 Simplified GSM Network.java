import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static class City {
		double x, y;
		int towerId;

		public City(double x, double y) {
			this.x=x;
			this.y=y;
		}
	}

	private static class Tower {
		double x, y;
		double minX,maxX,minY,maxY;

		public Tower(double x, double y) {
			this.x=x;
			this.y=y;
			
			this.minX=x-5;
			this.maxX=x+5;
			this.minY=y-5;
			this.maxY=y+5;
		}
		
		public boolean isIn(City c) {
			return c.x>=this.minX && c.x<=this.maxX && c.y>=this.minY && c.y<=this.maxY;
		}
	}

	public static void main(String [] args) throws Exception {
		final int MAX_DIST=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int B=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			
			Tower [] towers=new Tower[B];
			for (int b=0;b<B;b++) {
				st=new StringTokenizer(br.readLine());
				towers[b]=new Tower(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
			}
			int [][] towerDist=new int [B][B];
			for (int b=0;b<B;b++) {
				Arrays.fill(towerDist[b],MAX_DIST);
				towerDist[b][b]=0;
			}
			for (int b=0;b<B;b++) for (int b2=b+1;b2<B;b2++) {
				boolean dx=Math.abs(towers[b].x-towers[b2].x)==10;
				boolean dy=Math.abs(towers[b].y-towers[b2].y)==10;
				if (dx^dy) towerDist[b][b2]=towerDist[b2][b]=1;
			}
			for (int k=0;k<B;k++) for (int i=0;i<B;i++) for (int j=0;j<B;j++) towerDist[i][j]=Math.min(towerDist[i][j],towerDist[i][k]+towerDist[k][j]);

			City [] cities=new City[C];
			ArrayList<Integer> [] cityAdjList=new ArrayList [C];
			for (int c=0;c<C;c++) {
				st=new StringTokenizer(br.readLine());
				cities[c]=new City(Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken()));
				cityAdjList[c]=new ArrayList<>();
				for (int b=0;b<B;b++) if (towers[b].isIn(cities[c])) {
					cities[c].towerId=b;
					break;
				}
			}
			int [][] switchCount=new int [C][C];
			for (int c=0;c<C;c++) {
				Arrays.fill(switchCount[c],MAX_DIST);
				switchCount[c][c]=0;
			}

			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				int c1=Integer.parseInt(st.nextToken())-1;
				int c2=Integer.parseInt(st.nextToken())-1;
				cityAdjList[c1].add(c2);
				cityAdjList[c2].add(c1);
				switchCount[c1][c2]=switchCount[c2][c1]=towerDist[cities[c1].towerId][cities[c2].towerId];
			}
			for (int k=0;k<C;k++) for (int i=0;i<C;i++) for (int j=0;j<C;j++) switchCount[i][j]=Math.min(switchCount[i][j],switchCount[i][k]+switchCount[k][j]);


			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc++);
			sb.append(":\n");
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int c1=Integer.parseInt(st.nextToken())-1;
				int c2=Integer.parseInt(st.nextToken())-1;
				sb.append(switchCount[c1][c2]==MAX_DIST?"Impossible":switchCount[c1][c2]);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}