import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

class Main {

	private static void find(StringBuilder currChain, char [][] chMap, boolean [][] taken, TreeSet<String> solution, int x, int y) {
		if (currChain.length()>=3) solution.add(currChain.toString());
		if (currChain.length()==0) {
			for (int n=0;n<chMap.length;n++) for (int n2=0;n2<chMap[n].length;n2++) {
				currChain.append(chMap[n][n2]);
				taken[n][n2]=true;
				find(currChain,chMap,taken,solution,n,n2);
				currChain.setLength(currChain.length()-1);
				taken[n][n2]=false;
			}
		} else {
			for (int dx=-1;dx<=1;dx++) for (int dy=-1;dy<=1;dy++) {
				int nx=x+dx;
				int ny=y+dy;
				if (nx<0 || nx>=chMap.length || ny<0 || ny>=chMap[nx].length) continue;
				if (!taken[nx][ny] && chMap[nx][ny]>currChain.charAt(currChain.length()-1)) {
					currChain.append(chMap[nx][ny]);
					taken[nx][ny]=true;
					find(currChain,chMap,taken,solution,nx,ny);
					currChain.setLength(currChain.length()-1);
					taken[nx][ny]=false;
				}
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			br.readLine(); //useless
			int N=Integer.parseInt(br.readLine());

			char [][] chMap=new char [N][];
			for (int n=0;n<N;n++) chMap[n]=br.readLine().toCharArray();
			TreeSet<String> solution=new TreeSet<>((a,b) -> (a.length()!=b.length()) ? a.length()-b.length() : a.compareTo(b));
			find(new StringBuilder(),chMap,new boolean[N][N],solution,-1,-1);
			
			StringBuilder sb=new StringBuilder();
			if (testCase>0) sb.append('\n');
			for (String s: solution) {
				sb.append(s);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
