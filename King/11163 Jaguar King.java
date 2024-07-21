import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int [][] Deltas= {{-3,-1,4,-4},{1,3,4,-4},{1,-1,4,-4},{1,-1,4,-4}};
	private static int NO_DIST=1000000;
	private static int [][] MinDist;
	private static int PUZZLE_LEN=-1;
	private static int Sol=-1;

	public static void calcMinDist(int N) {
		MinDist=new int [N+1][N+1];
		for (int i=1;i<=N;i++) {
			Arrays.fill(MinDist[i], NO_DIST);
			MinDist[i][i]=0;
		}
		
		for (int i=1;i<=N;i++) {
			int dIdx=i%4;
			for (int i2=0;i2<Deltas[dIdx].length;i2++) {
				int nx=i+Deltas[dIdx][i2];
				if (nx>=1 && nx<=N) MinDist[i][nx]=1;
			}
		}

		for (int k=1;k<=N;k++) for (int i=1;i<=N;i++) for (int j=1;j<=N;j++) {
			MinDist[i][j]=Math.min(MinDist[i][j],MinDist[i][k]+MinDist[k][j]);
		}
	}

	private static int getH(int [] puzzle) {
		int h=0;
		for (int n=1;n<=PUZZLE_LEN;n++) if (puzzle[n]!=1) h+=MinDist[n][puzzle[n]];
		return h;
	}

	private static void swap(int [] puzzle, int s, int t) {
		int temp=puzzle[s];
		puzzle[s]=puzzle[t];
		puzzle[t]=temp;
	}

	public static int search(int [] puzzle, int kPos, int step, int heur, int maxStep) {
		if (Sol!=-1) return Sol;
		if (heur==0) {
			Sol=step;
			return step;
		}
		if (step+heur>maxStep) return step+heur;

		int lowest=Integer.MAX_VALUE;
		for (int d=0;d<4;d++) {
			int ex=kPos+Deltas[kPos%4][d];
			if (ex<=0 || ex>PUZZLE_LEN) continue;

			int nextHeur=heur;
			nextHeur-=MinDist[ex][puzzle[ex]];
			swap(puzzle,kPos,ex);
			nextHeur+=MinDist[kPos][puzzle[kPos]];

			lowest=Math.min(lowest,search(puzzle,ex,step+1,nextHeur,maxStep));
			swap(puzzle,ex,kPos);
		}
		return lowest;
	}

	public static void main (String [] args) throws Exception {
		calcMinDist(45);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);

			int [] puzzle=new int [N+1];
			int kPos=-1;
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=1;n<=N;n++) {
				int j=Integer.parseInt(st.nextToken());
				puzzle[n]=j;
				if (j==1) kPos=n;
			}

			PUZZLE_LEN=N;
			Sol=-1;
			int initLimit=getH(puzzle);
			int currLimit=initLimit;
			while (Sol==-1) {
				currLimit=search(puzzle,kPos,0,initLimit,currLimit);
			}
			StringBuilder sb=new StringBuilder();
			sb.append("Set ");
			sb.append(tc++);
			sb.append(":\n");
			sb.append(Sol);
			System.out.println(sb);
		}
	}

}