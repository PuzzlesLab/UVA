import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int [][] Deltas={{0,0},{-1,0},{1,0},{0,-1},{0,1}};
	private static final int SIZE=10;
	private static final int MAX_ANS=101;
	private static int Ans=MAX_ANS;

	private static void toggle(int [] map, int x, int y) {
		for (int [] d: Deltas) {
			int dx=x+d[0];
			int dy=y+d[1];
			if (dx>=0 && dx<SIZE && dy>=0 && dy<SIZE) map[dx]^=1<<dy;
		}
	}

	private static void find(int [] map, int x, int y, int depth) {
		if (y==SIZE) {
			x++;
			y=0;
		}
		if (depth>=Ans) return;


		if (x==0) {
			// Continue below / delegate switching to light below.
			find(map,x,y+1,depth);
			toggle(map,x,y);
			find(map,x,y+1,depth+1);
			toggle(map,x,y);
		} else if (x<SIZE) {
			if ((map[x-1]&(1<<y))!=0) { // Top is on, must perform switch to turn off.
				toggle(map,x,y);
				find(map,x,y+1,depth+1);
				toggle(map,x,y);
			} else find(map,x,y+1,depth); // Continue.
		} else {
			boolean flag=true;
			for (int i=0;i<SIZE && flag;i++) flag&=map[i]==0;
			if (flag) Ans=Math.min(Ans,depth);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("end")) {
			String name=s;
			int [] map=new int [SIZE];
			for (int i=0;i<SIZE;i++) {
				s=br.readLine();
				for (int i2=0;i2<SIZE;i2++) if (s.charAt(i2)=='O') map[i]|=(1<<i2);
			}
			
			Ans=MAX_ANS;
			find(map,0,0,0);
			if (Ans==MAX_ANS) Ans=-1;
			System.out.printf("%s %d\n",name,Ans);
		}
	}

}