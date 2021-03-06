import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int ix=Integer.parseInt(st.nextToken());
			int iy=Integer.parseInt(st.nextToken());
			char [][] map=new char [32][32];
			for (int i=0;i<map.length;i++) Arrays.fill(map[i], '.');
			
			int x=31-iy;
			int y=ix;
			
			char [] op=br.readLine().toCharArray();
			for (int i=0;i<op.length && op[i]!='.';i++) {
				char c=op[i];
				if (c=='W') {
					map[x][y-1]='X';
					y--;
				}
				else if (c=='E') {
					map[x+1][y]='X';
					y++;
				}
				else if (c=='N') {
					map[x][y]='X';
					x--;
				}
				else if (c=='S') {
					map[x+1][y-1]='X';
					x++;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Bitmap #");
			sb.append(testCase);
			sb.append('\n');
			for (int i=0;i<map.length;i++) {
				sb.append(map[i]);
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}
}