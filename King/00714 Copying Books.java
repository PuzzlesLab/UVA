import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {

	private static int fill(long [] pages, int K, long max) {
		// -1 = not enough, 0 = just nice, 1 = excess
		boolean loadEqMax=false;
		int load=0;
		int k=0;
		for (int i=0;i<pages.length;i++) {
			if (load+pages[i]>max || pages.length-i<K-k) {
				load=0;
				k++;
				if (k>=K) return -1;
			}
			load+=pages[i];
			loadEqMax|=(load==max);
		}
		return loadEqMax ? 0 : 1; // If loadEqMax=false, we can reduce max since no load reached max.
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine().trim());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());

			long [] pages=new long [M];
			st=new StringTokenizer(br.readLine());
			for (int m=0;m<M;m++) pages[m]=Long.parseLong(st.nextToken());

			long min=0;
			for (int m=0;m<M;m++) min=Math.max(min,pages[m]);
			long max=0;
			for (int m=0;m<M;m++) max+=pages[m];
			long mid=0;
			while (min<=max) {
				mid=(min+max)>>1;
				int sol=fill(pages,K,mid);
				if (sol==0) break;
				else if (sol>0) max=mid-1;
				else min=mid+1;
			}
			// mid = best solution.

			Stack<Long>[] scribers=new Stack[K];
			for (int k=0;k<K;k++) scribers[k]=new Stack<>();
			int k=K-1;
			int currLoad=0;
			for (int m=pages.length-1;m>=0;m--) {
				if (currLoad+pages[m]>mid || m<k) {
					currLoad=0;
					k--;
				}
				currLoad+=pages[m];
				scribers[k].push(pages[m]);
			}

			StringBuilder sb=new StringBuilder();
			for (k=0;k<K;k++) {
				while (!scribers[k].isEmpty()) {
					sb.append(scribers[k].pop());
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);;
				if (k<K-1) sb.append(" / ");
			}
			System.out.println(sb);
		}
	}

}