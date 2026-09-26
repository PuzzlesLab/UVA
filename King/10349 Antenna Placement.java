import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [][] Deltas={{0,-1},{0,1},{-1,0},{1,0}};
	private static int X;
	private static int Y;
	private static ArrayList<Integer> [] AdjList;
	private static int [] Match;
	private static boolean [] Visited;
	
	private static boolean isLeft(int x, int y) {
		return ((x+y)&1)==0;
	}

	private static int mcbm(int l) {
		if (Visited[l]) return 0;
		
		Visited[l]=true;
		for (int i=0;i<AdjList[l].size();i++) {
			int r=AdjList[l].get(i);
			if (Match[r]==-1 || mcbm(Match[r])==1) {
				Match[r]=l;
				return 1;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());
        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	X=Integer.parseInt(st.nextToken());
        	Y=Integer.parseInt(st.nextToken());

        	char [][] map=new char [X][];
        	for (int x=0;x<map.length;x++) map[x]=br.readLine().toCharArray();
        	
        	int [][] iMap=new int [X][Y];
        	for (int x=0;x<X;x++) Arrays.fill(iMap[x], -1);
        	int leftCount=0;
        	for (int x=0;x<map.length;x++) for (int y=0;y<Y;y++) if (map[x][y]=='*' && isLeft(x,y)) iMap[x][y]=leftCount++;
        	int rightCount=0;
        	for (int x=0;x<map.length;x++) for (int y=0;y<Y;y++) if (map[x][y]=='*' && !isLeft(x,y)) iMap[x][y]=rightCount++;

        	AdjList=new ArrayList[leftCount];
        	for (int i=0;i<AdjList.length;i++) AdjList[i]=new ArrayList<>();
        	for (int x=0;x<map.length;x++) for (int y=0;y<Y;y++) if (map[x][y]=='*' && isLeft(x,y)) {
        		for (int [] d: Deltas) {
        			int nx=x+d[0];
        			int ny=y+d[1];
        			if (nx<0 || nx>=X || ny<0 || ny>=Y) continue;
        			if (map[nx][ny]!='*') continue;

        			AdjList[iMap[x][y]].add(iMap[nx][ny]);
        		}
        	}
        	
        	Match=new int [rightCount];
        	Arrays.fill(Match,-1);
        	int pairCount=0;
        	for (int l=0;l<leftCount;l++) {
        		Visited=new boolean [leftCount];
        		pairCount+=mcbm(l);
        	}
        	System.out.println(leftCount+rightCount-pairCount);
        }
	}

}