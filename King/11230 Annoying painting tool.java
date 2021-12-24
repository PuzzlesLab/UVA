import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static void flip(boolean [][] bool, int x, int y, int r, int c) {
		for (int dx=0;dx<r;dx++) for (int dy=0;dy<c;dy++) bool[x+dx][y+dy]=!bool[x+dx][y+dy];
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			
			boolean [][] curr=new boolean [N][M];
			boolean [][] expected=new boolean [N][M];
			for (int n=0;n<N;n++) {
				char [] ch=br.readLine().toCharArray();
				for (int m=0;m<M;m++) expected[n][m]=ch[m]=='1';
			}
			
			int ans=0;
			for (int n=0;n<N-R+1;n++) for (int m=0;m<M-C+1;m++) if (curr[n][m]!=expected[n][m]) {
				flip(curr,n,m,R,C);
				ans++;
			}
			
			boolean same=true;
			for (int n=0;n<N;n++) for (int m=0;m<M;m++) same&=curr[n][m]==expected[n][m];
			
			System.out.println(same?ans:-1);
		}
	}

}
