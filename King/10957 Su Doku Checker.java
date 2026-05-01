import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static class Pos {
		int x, y;
		
		public Pos(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static ArrayList<Pos> FillList;
	private static int [][] Game;
	private static int AnsCount;
	private static int [] RowMask;
	private static int [] ColMask;
	private static int [][] QMask;
	private static int N=3;
	private static int TC;

	private static boolean hasRowConflict() {
		for (int n=0;n<Game.length;n++) {
			int mask=0;
			for (int n2=0;n2<Game.length;n2++) {
				if (Game[n][n2]==0) continue;
				if ((mask&(1<<Game[n][n2]))==0) mask|=1<<Game[n][n2];
				else return true;
			}
		}
		return false;
	}

	private static boolean hasColConflict() {
		for (int n=0;n<Game.length;n++) {
			int mask=0;
			for (int n2=0;n2<Game.length;n2++) {
				if (Game[n2][n]==0) continue;
				if ((mask&(1<<Game[n2][n]))==0) mask|=1<<Game[n2][n];
				else return true;
			}
		}
		return false;
	}

	private static boolean hasQConflict() {
		int [][] qMask=new int [N][N];
		for (int n=0;n<Game.length;n++) {
			for (int n2=0;n2<Game.length;n2++) {
				if (Game[n][n2]==0) continue;
				
				int qx=n/N;
				int qy=n2/N;
				if ((qMask[qx][qy]&(1<<Game[n][n2]))==0) qMask[qx][qy]|=1<<Game[n][n2];
				else return true;
			}
		}
		return false;
	}

	private static void fill(int curr) {
		if (AnsCount>2) return;
		if (curr==FillList.size()) {
			AnsCount++;
			return;
		}

		Pos currPos=FillList.get(curr);
		for (int i=1;i<=9;i++) {
			if ((RowMask[currPos.x]&(1<<i))!=0) continue;
			if ((ColMask[currPos.y]&(1<<i))!=0) continue;
			if ((QMask[currPos.x/N][currPos.y/N]&(1<<i))!=0) continue;

			RowMask[currPos.x]^=1<<i;
			ColMask[currPos.y]^=1<<i;
			QMask[currPos.x/N][currPos.y/N]^=1<<i;
			Game[currPos.x][currPos.y]=i;
			fill(curr+1);
			RowMask[currPos.x]^=1<<i;
			ColMask[currPos.y]^=1<<i;
			QMask[currPos.x/N][currPos.y/N]^=1<<i;
		}
	}

	private static void printNoSol(BufferedReader br) throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append("Case ");
		sb.append(TC++);
		sb.append(": Illegal.");
		System.out.println(sb);
		br.readLine();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		TC=1;
		while (true) {
			boolean end=false;
			int NSq=N*N;
			Game=new int [NSq][NSq];
			for (int n=0;n<NSq;n++) {
				String s=br.readLine();
				if (s==null) {
					end=true;
					break;
				}
				StringTokenizer st=new StringTokenizer(s);
				for (int n2=0;n2<NSq;n2++) Game[n][n2]=Integer.parseInt(st.nextToken());
			}
			if (end) break;

			if (hasRowConflict()) {
				printNoSol(br);
				continue;
			}
			if (hasColConflict()) {
				printNoSol(br);
				continue;
			}
			if (hasQConflict()) { // Q=own N*N block;
				printNoSol(br);
				continue;
			}

			RowMask=new int [NSq];
			ColMask=new int [NSq];
			QMask=new int [N][N];
			for (int n=0;n<NSq;n++) for (int n2=0;n2<NSq;n2++) if (Game[n][n2]!=0) {
				RowMask[n]|=(1<<Game[n][n2]);
				ColMask[n2]|=(1<<Game[n][n2]);
				QMask[n/N][n2/N]|=(1<<Game[n][n2]);
			}

			FillList=new ArrayList<>();
			for (int n=0;n<NSq;n++) for (int n2=0;n2<NSq;n2++) if (Game[n][n2]==0) FillList.add(new Pos(n,n2));

			AnsCount=0;
			fill(0);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(TC++);
			sb.append(": ");
			if (AnsCount==0) sb.append("Impossible.");
			else if (AnsCount==1) sb.append("Unique.");
			else sb.append("Ambiguous.");
			System.out.println(sb);

			br.readLine();
		}
	}

}