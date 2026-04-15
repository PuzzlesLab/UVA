import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static boolean [][][] Pieces;
	private static int [][] Ans;
	
	private static boolean canPieceFit(int [][] curr, int x, int y, boolean [][] piece) {
		int height=curr.length-x;
		int width=curr[x].length-y;
		if (piece.length>height || piece[0].length>width) return false;
		
		for (int i=0;i<piece.length;i++) for (int i2=0;i2<piece[i].length;i2++) if (piece[i][i2] && curr[x+i][y+i2]!=0) return false;
		return true;
	}

	private static void toggle(int [][] curr, int x, int y, boolean [][] piece, int id) {
		for (int i=0;i<piece.length;i++) for (int i2=0;i2<piece[i].length;i2++) if (piece[i][i2]) curr[x+i][y+i2]=(curr[x+i][y+i2]==id)?0:id;
	}

	private static void dfs(int [][] curr, int x, int y, int mask) {
		if (Ans!=null) return;
		if (mask==(1<<Pieces.length)-1) {
			boolean flag=true;
			for (int i=0;i<curr.length && flag;i++) for (int i2=0;i2<curr[i].length && flag;i2++) flag&=curr[i][i2]!=0;
			if (flag) {
				Ans=new int [4][4];
				for (int i=0;i<curr.length;i++) for (int i2=0;i2<curr[i].length;i2++) Ans[i][i2]=curr[i][i2];
			}
			return;
		}

		if (y==4) {
			y=0;
			x++;
		}
		if (x==4) return;

		for (int i=0;i<Pieces.length;i++) if ((mask&(1<<i))==0) {
			if (!canPieceFit(curr,x,y,Pieces[i])) continue;
			toggle(curr,x,y,Pieces[i],i+1);
			dfs(curr,x,y,mask|(1<<i));
			toggle(curr,x,y,Pieces[i],i+1);
		}
		dfs(curr,x,y+1,mask);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		boolean first=true;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			Pieces=new boolean [N][][];
			
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				Pieces[n]=new boolean [x][y];
				for (int i=0;i<Pieces[n].length;i++) {
					s=br.readLine();
					for (int i2=0;i2<s.length();i2++) Pieces[n][i][i2]=s.charAt(i2)=='1';
				}
			}
			
			Ans=null;
			dfs(new int [4][4],0,0,0);

			if (first) first=false;
			else System.out.println();
			if (Ans==null) System.out.println("No solution possible");
			else {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<Ans.length;i++) {
					for (int i2=0;i2<Ans[i].length;i2++) sb.append(Ans[i][i2]);
					sb.append('\n');
				}
				System.out.print(sb);
			}
		}
	}

}