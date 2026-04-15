import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_X=7;
	private static final int MAX_Y=8;
	private static final int [][] Deltas={{1,0},{0,1}};
	
	private static int [][] PipToBone;
	private static int [][] PipMap;
	private static ArrayList<String> Ans;

	private static boolean isOutOfBound(int x, int y) {
		return (x<0 || x>=MAX_X || y<0 || y>=MAX_Y);
	}
	
	private static String formatAry(int [][] curr) {
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<MAX_X;i++) {
			for (int i2=0;i2<MAX_Y;i2++) {
				if (curr[i][i2]<10) sb.append("   ");
				else if (curr[i][i2]<100) sb.append("  ");
				sb.append(curr[i][i2]);
			}
			sb.append('\n');
		}
		sb.setLength(sb.length()-1);
		return sb.toString();
	}

	private static void dfs(int [][] curr, int x, int y, int mask) {
		if (mask==(1<<29)-1) {
			Ans.add(formatAry(curr));
			return;
		}

		if (y==MAX_Y) {
			dfs(curr,x+1,0,mask);
			return;
		}
		if (x==MAX_X) return;
		if (curr[x][y]!=0) {
	        dfs(curr,x,y+1,mask);
	        return;
	    }

		for (int [] pair: Deltas) {
			int x2=x+pair[0];
			int y2=y+pair[1];
			if (isOutOfBound(x2,y2) || curr[x2][y2]!=0) continue;
			
			int n1=Math.min(PipMap[x][y],PipMap[x2][y2]);
			int n2=Math.max(PipMap[x][y],PipMap[x2][y2]);
			int boneI=PipToBone[n1][n2];
			if ((mask&(1<<boneI))!=0) continue;

			curr[x][y]=curr[x2][y2]=boneI;
			dfs(curr,x,y+1,mask|(1<<boneI));
			curr[x][y]=curr[x2][y2]=0;
		}
	}

	public static void main(String[] args) throws Exception {
		PipToBone=new int [7][7];
		int boneCount=1;
		for (int i=0;i<7;i++) for (int i2=i;i2<7;i2++) PipToBone[i][i2]=boneCount++;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			PipMap=new int [MAX_X][MAX_Y];
			boolean end=false;
			for (int i=0;i<PipMap.length && !end;i++) {
				String s=br.readLine();
				if (s==null) {
					end=true;
					break;
				}
				StringTokenizer st=new StringTokenizer(s);
				for (int i2=0;i2<PipMap[i].length;i2++) PipMap[i][i2]=Integer.parseInt(st.nextToken());
			}
			if (end) break;

			Ans=new ArrayList<>();
			dfs(new int [MAX_X][MAX_Y],0,0,1);
			
			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append("\n\n\n\n");
			sb.append("Layout #");
			sb.append(tc);
			sb.append(":\n\n\n");
			sb.append(formatAry(PipMap));
			sb.append("\n\nMaps resulting from layout #");
			sb.append(tc);
			sb.append(" are:\n\n\n");
			for (int i=0;i<Ans.size();i++) {
				sb.append(Ans.get(i));
				sb.append("\n\n\n");
			}
			sb.append("There are ");
			sb.append(Ans.size());
			sb.append(" solution(s) for layout #");
			sb.append(tc);
			sb.append('.');
			System.out.println(sb);
			tc++;
		}
	}

}