import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX_ANS=1000000;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> [] toggle=new ArrayList [M];
			for (int m=0;m<M;m++) {
				toggle[m]=new ArrayList<>();
				st=new StringTokenizer(br.readLine());
				for (int n=0;n<N;n++) {
					char c=st.nextToken().charAt(0);
					if (c=='1') toggle[m].add(n);
				}
			}
			
			int ans=MAX_ANS;
			LinkedList<int []> q=new LinkedList<>();
			boolean [] visited=new boolean [1<<N];
			q.add(new int []{(1<<N)-1,0});
			visited[q.getFirst()[0]]=true;
			
			while (!q.isEmpty()) {
				int [] curr=q.removeFirst();
				if (curr[0]==0) {
					ans=curr[1];
					break;
				}
				
				for (int m=0;m<M;m++) if (!toggle[m].isEmpty()) {
					int nMask=curr[0];
					for (int i=0;i<toggle[m].size();i++) nMask^=1<<toggle[m].get(i);
					if (!visited[nMask]) {
						visited[nMask]=true;
						q.add(new int [] {nMask,curr[1]+1});
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			if (ans==MAX_ANS) sb.append("IMPOSSIBLE");
			else sb.append(ans);
			System.out.println(sb);
		}
	}

}