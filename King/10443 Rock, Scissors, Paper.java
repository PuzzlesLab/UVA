import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [][] deltas= {{-1,0},{0,-1},{0,1},{1,0}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			
			char [][] map=new char [R][C];
			for (int r=0;r<R;r++) map[r]=br.readLine().trim().toCharArray();
			
			char [] opp=new char [128];
			opp['S']='R'; opp['R']='P'; opp['P']='S';
			
			for (int n=0;n<N;n++) {
				char [][] newMap=new char [R][C];
				for (int r=0;r<R;r++) for (int c=0;c<C;c++) {
					boolean hasOpp=false;
					for (int [] delta : deltas) {
						int dr=r+delta[0];
						int dc=c+delta[1];
						if (dr>=0 && dr<R && dc>=0 && dc<C) hasOpp|=map[dr][dc]==opp[map[r][c]];
					}
					if (hasOpp) newMap[r][c]=opp[map[r][c]];
					else newMap[r][c]=map[r][c];
				}
				map=newMap;
			}
			
			if (testCase>0) System.out.println();
			for (int r=0;r<R;r++) System.out.println(new String(map[r]));
		}
	}

}
