import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken())-1;
			int y=Integer.parseInt(st.nextToken())-1;
			char [][] map=new char [N][M];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for (int m=0;m<M;m++) map[n][m]=st.nextToken().charAt(0);
			}
			char [][] robot=new char [N][M];
			for (int n=0;n<N;n++) for (int m=0;m<M;m++) robot[n][m]='?';
			
			boolean [][] visited=new boolean [N][M];
			visited[x][y]=true;
			
			int movement=0;
			while (true) {
				robot[x][y]=map[x][y];
				for (int direction=0;direction<delta.length;direction++) {
					int nx=x+delta[direction][0];
					int ny=y+delta[direction][1];
					if (nx>=0 && nx<N && ny>=0 && ny<M && robot[nx][ny] == '?') robot[nx][ny]=map[nx][ny];
				}
				boolean moved=false;
				for (int direction=0;direction<delta.length;direction++) {
					int nx=x+delta[direction][0];
					int ny=y+delta[direction][1];
					if (nx>=0 && nx<N && ny>=0 && ny<M && !visited[nx][ny] && robot[nx][ny]=='0') {
						visited[nx][ny]=true;
						x=nx;
						y=ny;
						moved=true;
						movement++;
						break;
					}
				}
				if (!moved) break;
			}
			
			StringBuilder sb=new StringBuilder("\n");
			for (int m=0;m<M;m++) sb.append("|---");
			sb.append("|\n");
			
			for (int n=0;n<N;n++) {
				for (int m=0;m<M;m++) {
					sb.append("| ");
					sb.append(robot[n][m]);
					sb.append(' ');
				}
				sb.append("|\n");
				for (int m=0;m<M;m++) sb.append("|---");
				sb.append("|\n");
			}
			
			sb.append("\nNUMBER OF MOVEMENTS: ");
			sb.append(movement);
			
			System.out.println(sb.toString());
		}
	}
}