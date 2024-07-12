import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Mat {
		int x, y;
		
		public Mat(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}

	private static Mat [] Mat;
	private static int [][] Next;
	private static int [][] Dp;

	private static int calc(int start, int end) {
		if (start==end) return 0;
		if (Dp[start][end]==-1) {
			int min=Integer.MAX_VALUE;
			for (int next=start;next<end;next++) {
				int curr=calc(start,next)+Mat[start].x*Mat[next].y*Mat[end].y+calc(next+1,end);
				if (curr<min) {
					min=curr;
					Next[start][end]=next;
				}
			}
			Dp[start][end]=min;
		}
		return Dp[start][end];
	}

	private static void write(StringBuilder sb, int start, int end) {
		if (start==end) {
			sb.append('A');
			sb.append(start+1);
			return;
		}
		
		sb.append('(');
		write(sb,start,Next[start][end]);
		sb.append(" x ");
		write(sb,Next[start][end]+1,end);
		sb.append(')');
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (N==0) break;
			
			Mat=new Mat[N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				Mat[n]=new Mat(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}

			Dp=new int [N][N];
			for (int n=0;n<N;n++) Arrays.fill(Dp[n],-1);
			Next=new int [N][N];
			calc(0,Mat.length-1);

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase);
			sb.append(": ");
			write(sb,0,Mat.length-1);
			System.out.println(sb.toString());

			testCase++;
		}
	}

}
