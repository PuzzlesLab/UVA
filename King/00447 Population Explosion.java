import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static int [][] deltas = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	
	
	private static int neighbourCount (char [][] map, int x, int y) {
		int count=0;
		for (int [] delta : deltas) {
			int dx=x+delta[0], dy=y+delta[1];
			if (dx>=0 && dx<map.length && dy>=0 && dy<map[dx].length && map[dx][dy]=='O') count++;
		}
		return count;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int year=Integer.parseInt(br.readLine());
			char [][] map=new char [20][20];
			for (int i=0;i<map.length;i++) Arrays.fill(map[i], ' ');
			String s;
			while (true) {
				s=br.readLine();
				if (s==null || s.length()==0) break;
				StringTokenizer st=new StringTokenizer(s);
				map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]='O';
			}
			
			StringBuilder sb=new StringBuilder();
			for (int y=0;y<year;y++) {
				//Print starts
				for (int i=0;i<20;i++) sb.append('*');
				sb.append('\n');
				for (int i=0;i<map.length;i++) {
					for (int i2=0;i2<map.length;i2++) sb.append(map[i][i2]);
					sb.append('\n');
				}
				//Print end
				
				char [][] newMap=new char [map.length][map[0].length];
				for (int i=0;i<newMap.length;i++) Arrays.fill(newMap[i], ' ');
				
				for (int i=0;i<map.length;i++) for (int i2=0;i2<map.length;i2++) {
					int nc=neighbourCount(map,i,i2);
					if ((map[i][i2]=='O' && (nc==2 || nc==3)) || (map[i][i2]==' ' && nc==3)) newMap[i][i2]='O';
				}
				map=newMap;
			}
			for (int i=0;i<20;i++) sb.append('*');
			
			if (testCase>0) System.out.println();
			System.out.println(sb.toString());
		}
	}

}