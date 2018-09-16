import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static int [][] copy(int [][] c) {
		int [][] newC=new int [c.length][c.length];
		for (int i=0;i<c.length;i++) for (int i2=0;i2<c.length;i2++) newC[i][i2]=c[i][i2];
		return newC;
	}
	
	public static void swapRow(int [][] c, int a, int b) {
		int [][] newC=copy(c);
		c[a]=newC[b];
		c[b]=newC[a];
	}
	
	public static void swapCol(int [][] c, int a, int b) {
		int [][] newC=copy(c);
		for (int row=0;row<c.length;row++) {
			c[row][a]=newC[row][b];
			c[row][b]=newC[row][a];
		}
	}
	
	public static void inc(int [][] c) {
		for (int i=0;i<c.length;i++) for (int i2=0;i2<c.length;i2++) c[i][i2]=((c[i][i2])+1)%10;
	}
	
	public static void dec(int [][] c) {
		for (int i=0;i<c.length;i++) for (int i2=0;i2<c.length;i2++) c[i][i2]=Math.floorMod(c[i][i2]-1,10);
	}
	
	public static void transpose(int [][] c) {
		int [][] newC=copy(c);
		for (int i=0;i<c.length;i++) for (int i2=0;i2<c.length;i2++) c[i][i2]=newC[i2][i];
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int size=Integer.parseInt(br.readLine());
			int [][] c=new int [size][size];
			for (int i=0;i<size;i++) {
				char [] in=br.readLine().toCharArray();
				for (int i2=0;i2<size;i2++) c[i][i2]=in[i2]-'0';
			}
			
			int O=Integer.parseInt(br.readLine());
			for (int o=0;o<O;o++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				String op=st.nextToken();
				switch (op) {
					case "row": swapRow(c, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
								break;
					case "col": swapCol(c, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
								break;
					case "inc": inc(c);
								break;
					case "dec": dec(c);
								break;
					case "transpose": transpose(c);
										break;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #"); sb.append(t); sb.append('\n');
			for (int i=0;i<c.length;i++) {
				for (int i2=0;i2<c.length;i2++) sb.append(c[i][i2]);
				sb.append('\n');
			}
			System.out.println(sb.toString());
		}
	}

}