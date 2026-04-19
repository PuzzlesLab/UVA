import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<int []> Flip;
	private static ArrayList<Integer> Ans=null;
	private static int MAX_X;
	private static int MAX_Y;
	private static int SIZE;
	private static int END_STATE;

	private static int flatten(int x, int y) {
		return x*MAX_Y+y;
	}

	private static int toggle(int state, int x, int y) {
		for (int i=0;i<Flip.size();i++) {
			int [] d=Flip.get(i);
			int dx=x+d[0];
			int dy=y+d[1];
			if (dx<0||dx>=MAX_X||dy<0||dy>=MAX_Y) continue;
			state^=(1<<flatten(dx,dy));
		}
		return state;
	}

	private static void dfs(int state, int x, int y, int mask) {
		if (Ans!=null) return;

		if (state==END_STATE) {
			Ans=new ArrayList<>();
			for (int i=0;i<SIZE;i++) if ((mask&(1<<i))!=0) Ans.add(i+1);
			return;
		}

		if (y==MAX_Y) {
			x++;
			y=0;
		}
		if (x==MAX_X) return;
		if (x-2>=0 && (state&(1<<flatten(x-2,y)))==0) return; // We can't toggle x-2 row.

		// Don't touch.
		dfs(state,x,y+1,mask);
		// Touch.
		dfs(toggle(state,x,y),x,y+1,mask|(1<<flatten(x,y)));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			MAX_X=Integer.parseInt(st.nextToken());
			MAX_Y=Integer.parseInt(st.nextToken());
			SIZE=MAX_X*MAX_Y;
			END_STATE=(1<<SIZE)-1;

			Flip=new ArrayList<>();
			for (int i=-1;i<=1;i++) {
				s=br.readLine();
				for (int i2=-1;i2<=1;i2++) if (s.charAt(i2+1)=='*') Flip.add(new int [] {i,i2});
			}

			Ans=null;
			dfs(0,0,0,0);

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc++);
			sb.append('\n');
			if (Ans==null) sb.append("Impossible.");
			else {
				for (int i=0;i<Ans.size();i++) {
					sb.append(Ans.get(i));
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
			}
			System.out.println(sb);
		}
	}

}