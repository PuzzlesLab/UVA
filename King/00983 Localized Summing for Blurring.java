import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=0;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			int [][] value=new int [N][N];
			for (int i=N-1;i>=0;i--) for (int i2=0;i2<N;i2++) value[i][i2]=Integer.parseInt(br.readLine());
			
			long [][] sum=new long [N][N];
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) {
				sum[i][i2]=value[i][i2];
				if (i>0) sum[i][i2]+=sum[i-1][i2];
				if (i2>0) sum[i][i2]+=sum[i][i2-1];
				if (i>0 && i2>0) sum[i][i2]-=sum[i-1][i2-1];
			}
			
			long [][] ans=new long [N-M+1][N-M+1];
			long ansSum=0;
			for (int i=0;i<ans.length;i++) for (int i2=0;i2<ans.length;i2++) {
				int endI=i+M-1;
				int endI2=i2+M-1;

				long submatrixSum=sum[endI][endI2];
				if (i>0) submatrixSum-=sum[i-1][endI2];
				if (i2>0) submatrixSum-=sum[endI][i2-1];
				if (i>0 && i2>0) submatrixSum+=sum[i-1][i2-1];
				
				ans[i][i2]=submatrixSum;
				ansSum+=submatrixSum;
			}
			
			StringBuilder sb=new StringBuilder();
			if (testCase>0) sb.append('\n');
			for (int i=ans.length-1;i>=0;i--) for (int i2=0;i2<ans.length;i2++) {
				sb.append(ans[i][i2]);
				sb.append('\n');
			}
			sb.append(ansSum);
			System.out.println(sb.toString());
			
			testCase++;
			br.readLine();
		}
	}

}