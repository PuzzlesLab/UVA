import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MOD=100000;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int L=Integer.parseInt(st.nextToken());
			int T=Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> [] adjList=new ArrayList [L];
			int [] inDeg=new int [L];
			for (int l=0;l<L;l++) adjList[l]=new ArrayList<>();
			for (int t=0;t<T;t++) {
				st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken())-1;
				int to=Integer.parseInt(st.nextToken())-1;
				adjList[from].add(to);
				inDeg[to]++;
			}
			
			int [] ways=new int [L];
			int [] nodes=new int [L];
			PriorityQueue<Integer> pq=new PriorityQueue<>();
			for (int l=0;l<L;l++) if (inDeg[l]==0) {
				pq.offer(l);
				ways[l]=1;
			}

			while (!pq.isEmpty()) {
				int curr=pq.poll();
				if (curr==L-1) break;

				for (int i=0;i<adjList[curr].size();i++) {
					int next=adjList[curr].get(i);
					inDeg[next]--;
					ways[next]+=ways[curr];
					ways[next]%=MOD;
					nodes[next]+=nodes[curr]+ways[curr]; // nodes[curr] didn't include curr itself, we include ways[curr] as it tell us how many times nodes[curr] is used.
					nodes[next]%=MOD;
					if (inDeg[next]>0) continue;
					pq.offer(next);
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(nodes[L-1]);
			sb.append(' ');
			sb.append(ways[L-1]);
			System.out.println(sb.toString());
		}
	}

}
