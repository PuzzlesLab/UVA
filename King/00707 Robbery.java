import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static int [][] Deltas = {{0,0}, {0,-1},{0,1},{-1,0},{1,0}};

	private static int reachable(boolean [][][] possible, int [][][] ways, int time, int x, int y) {
		if (time>=possible.length) return 1;
		if (!possible[time][x][y]) return 0;
		
		if (ways[time][x][y]!=-1) return ways[time][x][y];
		
		int result=0;
		for (int [] delta: Deltas) {
			int px=x+delta[0];
			int py=y+delta[1];
			if (px>=0 && px<possible[time].length && py>=0 && py<possible[time][px].length && time<possible.length)
				result+=reachable(possible, ways, time+1, px, py);
		}
		ways[time][x][y] = result;
		return ways[time][x][y];
	} 
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCase=0;
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int W=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(br.readLine());
			
			boolean [][][] possible=new boolean [T+1][H][W];
			for (int t=0;t<=T;t++) for (int h=0;h<H;h++) Arrays.fill(possible[t][h], true);
			int [][][] waysToEnd=new int [T+1][H][W];
			for (int t=0;t<=T;t++) for (int h=0;h<H;h++) Arrays.fill(waysToEnd[t][h], -1);

			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				int ti=Integer.parseInt(st.nextToken());
				int Li=Integer.parseInt(st.nextToken())-1;
				int Ti=Integer.parseInt(st.nextToken())-1;
				int Ri=Integer.parseInt(st.nextToken())-1;
				int Bi=Integer.parseInt(st.nextToken())-1;
				for (int x=Ti;x<=Bi;x++) for (int y=Li;y<=Ri;y++) possible[ti][x][y] = false;
			}
			
			int reachableCount=0;
			for (int x=0;x<H;x++) for (int y=0;y<W;y++) if (possible[1][x][y] && reachable(possible,waysToEnd,1,x,y)>0) reachableCount++;

			StringBuilder sb=new StringBuilder();
			sb.append("Robbery #");
			sb.append(++testCase);
			sb.append(":\n");
			if (reachableCount==0) sb.append("The robber has escaped.\n");
			else {
				boolean located=false;
				for (int t=1;t<=T;t++) {
					int count=0, solx=-1, soly=-1;
					for (int x=0;x<H;x++) for (int y=0;y<W;y++) if (waysToEnd[t][x][y]>0) {
						count++;
						solx=x;
						soly=y;
					}
					if (count==1) {
						located=true;
						sb.append("Time step ");
						sb.append(t);
						sb.append(": The robber has been at ");
						sb.append(soly+1);
						sb.append(',');
						sb.append(solx+1);
						sb.append(".\n");
					}
				}
				if (!located) sb.append("Nothing known.\n");
			}
			System.out.println(sb.toString());
		}
	}
}