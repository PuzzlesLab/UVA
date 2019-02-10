import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			long K=Long.parseLong(st.nextToken());
			
			long [][] value=new long [N][M];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				for (int m=0;m<M;m++) value[n][m]=Integer.parseInt(st.nextToken());
			}
			
			long [] ans= {0,0};
			for (int colStart=0;colStart<M;colStart++) {
				long [] temp=new long [N];
				for (int colEnd=colStart;colEnd<M;colEnd++) {
					for (int row=0;row<N;row++) temp[row]+=value[row][colEnd];
					
					long sum=0;
					int firstIndex=0;
					
					//Modified Kadane
					for (int row=0;row<N;row++) {
						sum+=temp[row];
						while (sum>K && firstIndex<N) {
							sum-=temp[firstIndex];
							firstIndex++;
						}
						
						int size=(row-firstIndex+1)*(colEnd-colStart+1);
						if (ans[0]==0 || size>ans[0] || (size==ans[0] && sum<ans[1])) {
							ans[0]=size;
							ans[1]=sum;
						}
					}
				}
			}
			
			System.out.printf("Case #%d: %d %d\n", testCase, ans[0], ans[1]);
		}
	}

}