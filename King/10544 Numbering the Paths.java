import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {

	private static ArrayList<Integer> [] Adj;
	private static long [] Paths;

	private static long fillPaths(int curr) {
		if (Paths[curr]!=-1) return Paths[curr];

		long count=0;
		for (int i=0;i<Adj[curr].size();i++) count+=fillPaths(Adj[curr].get(i));
		count=Math.max(count,1);
		return Paths[curr]=count;
	}

	private static long Ans;
	private static void findPath(int [] toFind, int idx, long sum) {
		if (idx==toFind.length) {
			Ans=sum;
			return;
		}

		for (int i=0;i<Adj[toFind[idx-1]].size() && Ans==-1;i++) {
			int next=Adj[toFind[idx-1]].get(i);
			if (next==toFind[idx]) {
				findPath(toFind,idx+1,sum);
				break;
			}
			sum+=Paths[next];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			boolean [][] adjMat=new boolean[N][N];
			Adj=new ArrayList [N];
			for (int n=0;n<N;n++) Adj[n]=new ArrayList<>();
			for (int m=0;m<M;m++) {
				String s=br.readLine();
				int from=s.charAt(0)-'A';
				int to=s.charAt(1)-'A';
				if (!adjMat[from][to]) { // There are malicious repeated edge input.
					Adj[s.charAt(0)-'A'].add(s.charAt(1)-'A');
					adjMat[from][to]=true;
				}
			}
			for (int n=0;n<N;n++) Collections.sort(Adj[n]);

			Paths=new long [N];
			Arrays.fill(Paths,-1);
			fillPaths(0);

			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				String s=br.readLine();
				int [] toFind=new int [s.length()];
				for (int i=0;i<toFind.length;i++) toFind[i]=s.charAt(i)-'A';
				Ans=-1;
				findPath(toFind,1,1);
				System.out.printf("%s: %d\n",s,Ans);
			}
		}
	}

}
