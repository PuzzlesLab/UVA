import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int [][] distMat=new int [N][N];
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) distMat[i][i2]=(i==i2) ? 0 : 999;
			
			int R=Integer.parseInt(br.readLine());
			for (int r=0;r<R;r++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				distMat[n1][n2]=1;
				distMat[n2][n1]=1;
			}
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int src=Integer.parseInt(st.nextToken());
			int dest=Integer.parseInt(st.nextToken());
			
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++)
				distMat[i][j]=Math.min(distMat[i][j], distMat[i][k]+distMat[k][j]);
			
			int ans=-1;
			for (int i=0;i<N;i++) ans=Math.max(ans, distMat[src][i]+distMat[i][dest]);
			
			System.out.println(String.format("Case %d: %d", testCase, ans));
		}
	}

}
