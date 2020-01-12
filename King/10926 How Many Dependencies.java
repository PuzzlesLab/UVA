import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static int dfs(int curr, boolean [][] adjMat, boolean [] visited) {
		int sum=0;
		for (int next=0;next<adjMat.length;next++) if (adjMat[curr][next] && !visited[next]) {
			visited[next]=true;
			sum+=(1+dfs(next,adjMat,visited));
		}
		return sum;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			
			boolean [][] adjMat=new boolean [N][N];
			int [] incoming=new int [N];
			for (int i=0;i<N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int x=Integer.parseInt(st.nextToken());
				for (int i2=0;i2<x;i2++) {
					int dest=Integer.parseInt(st.nextToken())-1;
					adjMat[i][dest]=true;
					incoming[dest]++;
				}
			}
			
			int best=0, bestV=0;
			for (int i=0;i<N;i++) if (incoming[i]==0) {
				boolean [] visited=new boolean [N];
				visited[i]=true;
				int v=dfs(i,adjMat,visited);
				if (v>bestV) {
					best=i;
					bestV=v;
				}
			}

			System.out.println(best+1);
		}
	}

}