import java.util.Scanner;

class Main {

	private static final int [][] Deltas={{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	private static char [][] Grid;
	private static String Word;
	
	private static boolean exists(int x, int y, int w, boolean [][] visited) {
		for (int d=0;d<Deltas.length;d++) {
			int nx=x+Deltas[d][0];
			int ny=y+Deltas[d][1];
			if (nx<0 || nx>=Grid.length || ny<0 || ny>=Grid.length) continue;
			if (Grid[nx][ny]!=Word.charAt(w)) continue;

			if (visited[nx][ny]) continue;
			if (w+1==Word.length()) return true;
			visited[nx][ny]=true;
			if (exists(nx,ny,w+1,visited)) return true;
			visited[nx][ny]=false;
		}
		return false;
	}

	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int N=sc.nextInt();
		for (int n=1;n<=N;n++) {
			Grid=new char [4][4];
			for (int i=0;i<4;i++) {
				String s=sc.next();
				for (int i2=0;i2<4;i2++) Grid[i][i2]=s.charAt(i2);
			}

			int M=sc.nextInt();
			int score=0;
			for (int m=0;m<M;m++) {
				Word=sc.next();
				int len=Word.length();
				if (len<3) continue;

				boolean flag=false;
				for (int i=0;i<Grid.length && !flag;i++) for (int i2=0;i2<Grid.length && !flag;i2++) {
					if (Grid[i][i2]!=Word.charAt(0)) continue;
					boolean [][] visited=new boolean [4][4];
					visited[i][i2]=true;
					flag|=exists(i,i2,1,visited);
				}
				if (!flag) continue;

				if (len==3 || len==4) score++;
				else if (len==5) score+=2;
				else if (len==6) score+=3;
				else if (len==7) score+=5;
				else if (len>=8) score+=11;
			}
			
			System.out.printf("Score for Boggle game #%d: %d\n",n,score);
		}
	}
}