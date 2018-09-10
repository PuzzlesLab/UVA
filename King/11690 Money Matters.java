import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int findSet(int [] parent, int key) {
		return (parent[key]==key) ? key : (parent[key]=findSet(parent, parent[key]));
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken()); int M=Integer.parseInt(st.nextToken());
			
			int [] sum=new int [N];
			for (int n=0;n<N;n++) sum[n]=Integer.parseInt(br.readLine());
			
			int [] parent=new int [N];
			int [] rank=new int [N];
			for (int n=0;n<N;n++) parent[n]=n;
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int x=findSet(parent, Integer.parseInt(st.nextToken()));
				int y=findSet(parent, Integer.parseInt(st.nextToken()));
				if (x!=y) {
					if (rank[x]>rank[y]) {
						parent[y]=x;
						sum[x]+=sum[y];
					} else {
						if (rank[x]==rank[y]) rank[y]++;
						parent[x]=y;
						sum[y]+=sum[x];
					}
				}
			}
			
			boolean possible=true;
			for (int n=0;n<N && possible;n++) possible&=(sum[findSet(parent, n)]==0); 
			if (possible) System.out.println("POSSIBLE");
			else System.out.println("IMPOSSIBLE");
		}
	}

}
