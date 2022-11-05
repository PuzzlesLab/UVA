import java.util.ArrayList;
import java.util.Scanner;

class Main {

	private static class Edge {
		int from, to, length;

		public Edge(int from, int to, int length) {
			this.from=from;
			this.to=to;
			this.length=length;
		}
	}

	/*
	 * Simplified problem explanation :
	 * Given an matrix of distance, check whether it is optimized.
	 * If yes, remove the redundant length.
	 */

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in); // Contains malicious input for BufferedReader :/
		int TC=sc.nextInt();
		for (int tc=1;tc<=TC;tc++) {
			int N=sc.nextInt();

			int [][] dist=new int [N+1][N+1];
			int [][] dist2=new int [N+1][N+1];
			for (int i=1;i<N;i++) {
				for (int j=1;j<=i;j++) {
					dist[i+1][j]=dist[j][i+1]=dist2[i+1][j]=dist2[j][i+1]=sc.nextInt();
				}
			}

			boolean notBest=false;
			for (int k=1;k<=N && !notBest;k++) {
				for (int i=1;i<=N && !notBest;i++) if (i!=k) {
					for (int j=1;j<=N && !notBest;j++) if (j!=i && j!=k) {
						int nd=dist[i][k]+dist[k][j];
						if (dist[i][j]>nd) notBest=true;
						else if (dist[i][j]==nd) dist2[i][j]=0;
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(":\n");
			if (notBest) sb.append("Need better measurements.\n");
			else {
				ArrayList<Edge> solution=new ArrayList<>();
				for (int i=1;i<=N;i++) for (int j=i+1;j<=N;j++) if (dist2[i][j]!=0) solution.add(new Edge(i,j,dist2[i][j]));

				sb.append(solution.size());
				sb.append('\n');
				for (int i=0;i<solution.size();i++) {
					Edge e=solution.get(i);
					sb.append(e.from);
					sb.append(' ');
					sb.append(e.to);
					sb.append(' ');
					sb.append(e.length);
					sb.append('\n');
				}
			}

			System.out.println(sb.toString());
		}
	}

}
