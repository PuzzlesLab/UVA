import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static int [][] neighbours = {{0,-1},{0,1},{-1,0},{1,0}};
	
	private static class Coordinate {
		public int x, y;
		
		public Coordinate(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static void flood(boolean [][] table, int x, int y, int [][] id, int idToFill) {
		Stack<Coordinate> stk=new Stack<>();
		stk.push(new Coordinate(x,y));
		
		while (!stk.isEmpty()) {
			Coordinate c=stk.pop();
			id[c.x][c.y]=idToFill;
			for (int [] neighbour : neighbours) {
				int x1=c.x+neighbour[0];
				int y1=c.y+neighbour[1];
				if (x1>=0 && x1<table.length && y1>=0 && y1<table[0].length && table[x1][y1]==table[c.x][c.y] && id[x1][y1]==0) stk.push(new Coordinate(x1,y1));
			}
		}
	}
	
	public static void bfs(boolean [][] visited, int x, int y, int [][] id, int idToFind, Set<Integer> neighbourIDs) {
		Stack<Coordinate> stk=new Stack<>();
		stk.push(new Coordinate(x,y));
		
		while (!stk.isEmpty()) {
			Coordinate c=stk.pop();
			visited[c.x][c.y]=true;
			for (int [] neighbour : neighbours) {
				int x1=c.x+neighbour[0];
				int y1=c.y+neighbour[1];
				if (x1>=0 && x1<visited.length && y1>=0 && y1<visited[0].length && !visited[x1][y1]) {
					if (id[x1][y1]!=idToFind) neighbourIDs.add(id[x1][y1]);
					else stk.add(new Coordinate(x1,y1));
				}
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		boolean [][] lookup=new boolean [128][4];
		for (int i=1;i<16;i++) {
			char hex = Integer.toHexString(i).charAt(0);
			char [] bin = Integer.toBinaryString(i).toCharArray();
			for (int i2=0;i2<bin.length;i2++) lookup[hex][(4-bin.length)+i2]=bin[i2]=='1';
		}
		
		char [] ansLookup= {'W','A','K','J','S','D'};
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int H=Integer.parseInt(st.nextToken());
			int W=Integer.parseInt(st.nextToken());
			boolean [][] table=new boolean [H][W*4];
			for (int h=0;h<H;h++) {
				char [] chs=br.readLine().toCharArray();
				for (int w=0;w<W;w++) for (int i2=0;i2<4;i2++) table[h][w*4+i2]=lookup[chs[w]][i2];
			}
			
			int [][] ID=new int [H][W*4];
			//Fill the outside white char from the edge white chars
			for (int w=0;w<table[0].length;w++) {
				if (!table[0][w] && ID[0][w]==0) flood(table,0,w,ID,1);
				if (!table[table.length-1][w] && ID[table.length-1][w]==0) flood(table,table.length-1,w,ID,1);
			}
			for (int h=0;h<table.length;h++) {
				if (!table[h][0] && ID[h][0]==0) flood(table,h,0,ID,1);
				if (!table[h][table[0].length-1] && ID[h][table[0].length-1]==0) flood(table,h,table[0].length-1,ID,1);
			}
			
			//Flood the remaining white/black char.
			int currID=2;
			for (int w=0;w<table[0].length;w++) for (int h=0;h<table.length;h++) if (ID[h][w]==0) {
				flood(table,h,w,ID,currID++);
			}

			ArrayList<Character> ansList=new ArrayList<>();
			int [] neighbourCount=new int [currID+30];
			for (int h=0;h<table.length;h++) for (int w=0;w<table[0].length;w++) if (table[h][w] && neighbourCount[ID[h][w]]==0) {
				Set<Integer> set=new HashSet<>();
				dfs(new boolean [table.length][table[0].length], h,w,ID,ID[h][w],set);
				neighbourCount[ID[h][w]]=set.size();
				ansList.add(ansLookup[set.size()-1]);
			}
			
			Collections.sort(ansList);
			StringBuilder ans=new StringBuilder();
			ans.append("Case ");
			ans.append(testCase++);
			ans.append(": ");
			for (char c : ansList) ans.append(c);
			System.out.println(ans.toString());
		}
	}

}
