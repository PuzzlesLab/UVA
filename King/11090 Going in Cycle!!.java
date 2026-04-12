import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final double INF=100000000;
	private static final int MAX_ANS=1000000;
	private static int [][] MinDist;
	private static int N;

	private static class Reader {
		private BufferedReader br;
		private StringTokenizer st;
		
		public Reader() {
			this.br=new BufferedReader(new InputStreamReader(System.in));
			this.st=new StringTokenizer("");
		}
		
		public int nextInt() throws Exception {
			while (!st.hasMoreTokens()) st=new StringTokenizer(br.readLine());
			return Integer.parseInt(st.nextToken());
		}
	}

	private static boolean exists(double mean) {
		double [][] temp=new double [MinDist.length][MinDist.length];
		for (int i=0;i<temp.length;i++) for (int i2=0;i2<temp.length;i2++) {
			if (MinDist[i][i2]==MAX_ANS) temp[i][i2]=INF;
			else temp[i][i2]=MinDist[i][i2]-mean;
		}

		for (int k=0;k<N;k++) for (int i=0;i<N;i++) if (temp[i][k]!=INF) {
			for (int j=0;j<N;j++) temp[i][j]=Math.min(temp[i][j], temp[i][k]+temp[k][j]);
			if (temp[i][i]<0) return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		Reader rd=new Reader();
		int TC=rd.nextInt();
		for (int tc=1;tc<=TC;tc++) {
			N=rd.nextInt();
			int M=rd.nextInt();

			MinDist=new int [N][N];
			for (int n=0;n<N;n++) Arrays.fill(MinDist[n],MAX_ANS);
			for (int m=0;m<M;m++) {
				int n1=rd.nextInt()-1;
				int n2=rd.nextInt()-1;
				int w=rd.nextInt();
				MinDist[n1][n2]=Math.min(MinDist[n1][n2],w);
			}

			double min=0.0;
			double max=MAX_ANS;
			for (int i=0;i<30;i++) {
				double mid=(min+max)/2;
				if (exists(mid)) max=mid;
				else min=mid;
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(tc);
			sb.append(": ");
			if (Math.abs(max-MAX_ANS)<1e-5) sb.append("No cycle found.");
			else sb.append(String.format("%.2f",max));
			System.out.println(sb);
		}
	}

}