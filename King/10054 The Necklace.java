import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static int getParent(int [] parent, int n) {
		if (parent[n]!=n) parent[n]=getParent(parent,parent[n]);
		return parent[n];
	}

	private static void formChain(int n, int [][] adjCount, boolean [] exists, StringBuilder sb) {
		for (int next=1;next<adjCount.length;next++) {
			if (adjCount[n][next]==0) continue;
			
			adjCount[n][next]--;
			adjCount[next][n]--;
			formChain(next,adjCount,exists,sb);
			sb.append(next);
			sb.append(' ');
			sb.append(n);
			sb.append('\n');
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			
			int [][] adjCount=new int [51][51];
			int [] degree=new int [51];
			int [] parent=new int [51];
			for (int n=0;n<parent.length;n++) parent[n]=n;
			boolean [] exists=new boolean [51];

			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				exists[n1]=true;
				exists[n2]=true;
				adjCount[n1][n2]++;
				adjCount[n2][n1]++;
				degree[n1]++;
				degree[n2]++;

				if (n1>=n2) parent[getParent(parent,n1)]=getParent(parent,n2);
				else parent[getParent(parent,n2)]=getParent(parent,n1);
			}
			
			HashSet<Integer> parents=new HashSet<>();
			for (int n=1;n<parent.length;n++) if (exists[n]) parents.add(getParent(parent,n));
			
			boolean allEven=true;
			for (int n=1;n<degree.length;n++) if (exists[n]) allEven&=(degree[n]%2==0);

			boolean lost=parents.size()!=1 || !allEven;
			
			StringBuilder sb=new StringBuilder();
			if (testCase>1) sb.append('\n');
			sb.append("Case #");
			sb.append(testCase);
			sb.append('\n');
			if (lost) sb.append("some beads may be lost\n");
			else {
				int seed=-1;
				for (int n=1;n<exists.length;n++) if (exists[n]) {
					seed=n;
					break;
				}
				
				formChain(seed,adjCount,exists,sb);
			}

			System.out.print(sb.toString());
		}
	}

}
