import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
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
				int K=Integer.parseInt(st.nextToken());
				for (int k=0;k<K;k++) toggle[m].add(Integer.parseInt(st.nextToken()));
			}
			
			int [] steps=new int [1<<N];
			Arrays.fill(steps,-1);
			steps[0]=0;
			LinkedList<Integer> q=new LinkedList<>();
			q.addLast(0);
			while (!q.isEmpty()) {
				int curr=q.removeFirst();
				
				for (int m=0;m<M;m++) {
					int nMask=curr;
					for (int i=0;i<toggle[m].size();i++) nMask^=(1<<toggle[m].get(i));
					if (steps[nMask]==-1) {
						steps[nMask]=steps[curr]+1;
						q.addLast(nMask);
					}
				}
			}
			
			int Q=Integer.parseInt(br.readLine());
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(":\n");
			for (int i=0;i<Q;i++) {
				sb.append(steps[Integer.parseInt(br.readLine(),2)]);
				sb.append('\n');
			}
			System.out.println(sb);
		}
	}

}