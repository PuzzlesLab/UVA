import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			HashMap<String, Integer> map=new HashMap<>();
			String [] mapIntToName=new String [N];
			for (int n=0;n<N;n++) {
				s=br.readLine();
				map.put(s, n);
				mapIntToName[n]=s;
			}

			int [][] adjMat=new int [N][N];
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) if (i!=i2) adjMat[i][i2]=1001;
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int src=Integer.parseInt(st.nextToken())-1;
				int dest=Integer.parseInt(st.nextToken())-1;
				int weight=Integer.parseInt(st.nextToken());
				adjMat[src][dest]=weight;
				adjMat[dest][src]=weight;
			}
			
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++)
				adjMat[i][j]=Math.min(adjMat[i][j], adjMat[i][k]+adjMat[k][j]);
			
			int minCost=Integer.MAX_VALUE, minCostIndex=-1;
			for (int i=0;i<N;i++) {
				int currCost=0;
				for (int i2=0;i2<N;i2++) currCost+=adjMat[i][i2];
				if (currCost<minCost) {
					minCost=currCost;
					minCostIndex=i;
				}
			}
			
			System.out.printf("Case #%d : %s\n", testCase++, mapIntToName[minCostIndex]);
		}
	}

}
