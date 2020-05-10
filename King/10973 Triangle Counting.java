import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			boolean [][] adjMat=new boolean[N][N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				adjMat[n1][n2]=true;
				adjMat[n2][n1]=true;
			}
			
			int count=0;
			for (int n1=0;n1<N;n1++)
				for (int n2=n1+1;n2<N;n2++) if (adjMat[n1][n2] || adjMat[n2][n1])
					for (int n3=n2+1;n3<N;n3++) if ((adjMat[n2][n3] || adjMat[n3][n2]) && (adjMat[n3][n1] || adjMat[n1][n3]))
						count++;
			
			System.out.println(count);
		}
	}

}