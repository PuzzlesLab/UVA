import java.util.*;
import java.io.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			br.readLine();
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] nodeValue=new int [N];
			for (int n=0;n<N;n++) nodeValue[n]=Integer.parseInt(st.nextToken());
			
			int [][] edge=new int [N][2];
			for (int n=0;n<N;n++) edge[n][0]=n;
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int src=Integer.parseInt(st.nextToken());
				int dest=Integer.parseInt(st.nextToken());
				int cost=nodeValue[dest];
				
				if (edge[src][0]==src || edge[src][1]<cost) {
					edge[src][0]=dest;
					edge[src][1]=cost;
				}
			}
			
			int currNode=0, ans=0;
			while (edge[currNode][0]!=currNode) {
				ans+=edge[currNode][1];
				currNode=edge[currNode][0];
			}
			System.out.printf("Case %d: %d %d\n",testCase,ans,currNode);
		}
	}
}