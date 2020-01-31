import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class Main {
	
	private static class Tuple implements Comparable<Tuple> {
		public int n, d;
		public Tuple(int n, int d) {
			this.n=n;
			this.d=d;
		}
		public int compareTo(Tuple o) {
			return this.d-o.d;
		}
	}
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=sc.nextInt();
			int M=sc.nextInt();
			int K=sc.nextInt();
			
			boolean [] airport=new boolean [N+1];
			for (int k=0;k<K;k++) airport[sc.nextInt()]=true;
			
			int [][] adjMat=new int [N+1][N+1];
			for (int i=0;i<adjMat.length;i++) Arrays.fill(adjMat[i], Integer.MAX_VALUE);
			
			for (int m=0;m<M;m++) {
				int a=sc.nextInt();
				int b=sc.nextInt();
				adjMat[a][b]=(airport[b]) ? 0 : 1;
				adjMat[b][a]=(airport[a]) ? 0 : 1;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(":\n");
			
			int Q=sc.nextInt();
			for (int q=0;q<Q;q++) {
				int x=sc.nextInt();
				int y=sc.nextInt();
				int [] minDist=new int [N+1];
				Arrays.fill(minDist, Integer.MAX_VALUE);
				
				if (x!=y) {
					PriorityQueue<Tuple> queue=new PriorityQueue<>();
					minDist[x]=airport[x] ? 0 : 1;
					queue.offer(new Tuple(x,minDist[x]));
					while (!queue.isEmpty()) {
						Tuple t=queue.poll();
						if (t.n==y) break;
						if (t.d != minDist[t.n]) continue;
						
						for (int i=1;i<=N;i++) if (adjMat[t.n][i]!=Integer.MAX_VALUE && t.d+adjMat[t.n][i]<minDist[i]) {
							minDist[i]=t.d+adjMat[t.n][i];
							queue.offer(new Tuple(i,minDist[i]));
						}
					}
				} else minDist[x]=0;
				
				sb.append(minDist[y]!=Integer.MAX_VALUE ? minDist[y] : -1);
				sb.append('\n');
			}
			
			System.out.println(sb.toString());
		}
	}

}