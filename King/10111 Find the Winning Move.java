import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

	private static class Tuple {
		int x, y;
		public Tuple(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static char [][] Game;
	private static HashMap<String,Tuple> Dp=new HashMap<>();

	private static String getGameKey(char p) {
		StringBuilder sb=new StringBuilder();
		sb.append(p);
		sb.append('-');
		for (int i=0;i<Game.length;i++) sb.append(new String(Game[i]));
		return sb.toString();
	}

	private static boolean canWin(char c) {
		// Check horizontal
		for (int row=0;row<Game.length;row++) {
			if (Game[row][0]==c && Game[row][0]==Game[row][1] && Game[row][1]==Game[row][2] && Game[row][2]==Game[row][3]) {
				return true;
			}
		}
		// Check vertical
		for (int col=0;col<Game.length;col++) {
			if (Game[0][col]==c && Game[0][col]==Game[1][col] && Game[1][col]==Game[2][col] && Game[2][col]==Game[3][col]) {
				return true;
			}
		}
		// Check diagonals
		if (Game[0][0]==c && Game[0][0]==Game[1][1] && Game[1][1]==Game[2][2] && Game[2][2]==Game[3][3]) {
			return true;
		}
		if (Game[0][3]==c && Game[0][3]==Game[1][2] && Game[1][2]==Game[2][1] && Game[2][1]==Game[3][0]) {
			return true;
		}

		return false;
	}

	private static Tuple play(char p) {
		String key=getGameKey(p);
		
		if (!Dp.containsKey(key)) {
			char p2=p=='x'?'o':'x';

			for (int x=0;x<Game.length;x++) for (int y=0;y<Game.length;y++) if (Game[x][y]=='.') {
				Game[x][y]=p;
				boolean flag=canWin(p)||play(p2)==null;
				Game[x][y]='.';
				if (flag) {
					Tuple ans=new Tuple(x,y);
					Dp.put(key,ans);
					return ans;
				}
			}
			Dp.put(key,null);
		}
		return Dp.get(key);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String s=br.readLine();
			if (s.equals("$")) break;
			
			Game=new char [4][];
			for (int i=0;i<Game.length;i++) {
				Game[i]=br.readLine().toCharArray();
			}

			Tuple ans=play('x');
			if (ans==null) System.out.println("#####");
			else System.out.printf("(%d,%d)\n",ans.x,ans.y);
		}
	}

}
