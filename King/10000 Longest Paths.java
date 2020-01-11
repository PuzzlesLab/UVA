import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

class uva {
	
	private static class Data {
		int next, count;
		public Data(int n, int c) {
			this.next=n;
			this.count=c;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (N==0) break;
			
			int S=Integer.parseInt(br.readLine());
			boolean [][] adjMat=new boolean [N+1][N+1];
			int [] incoming=new int [N+1];
			
			while (true) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int left=Integer.parseInt(st.nextToken());
				int right=Integer.parseInt(st.nextToken());
				if (left==0 || right==0) break;
				if (!adjMat[left][right]) {
					adjMat[left][right]=true;
					incoming[right]++;
				}
			}
			
			int [] pathLength=new int [N+1];
			Arrays.fill(pathLength, -1);
			LinkedList<Data> q=new LinkedList<>();
			q.add(new Data(S,0));
			
			while (!q.isEmpty()) {
				Data dat=q.removeFirst();
				if (dat.count>pathLength[dat.next]) {
					pathLength[dat.next]=dat.count;
					for (int neighbour=1;neighbour<=N;neighbour++) if (adjMat[dat.next][neighbour]) q.addLast(new Data(neighbour, dat.count+1));
				}
			}
			
			int max=Integer.MIN_VALUE, maxIndex=-1;
			for (int i=1;i<=N;i++) if (pathLength[i]>max) {
				maxIndex=i;
				max=pathLength[i];
			}
			
			System.out.printf("Case %d: The longest path from %d has length %d, finishing at %d.\n\n", testCase++, S, max, maxIndex);
		}
	}

}