import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class uva {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine();
		int NOEDGE=1000000;
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int F=Integer.parseInt(st.nextToken());
			int I=Integer.parseInt(st.nextToken());

			int [][] adjMat=new int [I][I];
			for (int i=0;i<I;i++) for (int i2=0;i2<I;i2++) adjMat[i][i2]=(i==i2) ? 0 : NOEDGE;
			
			boolean [] hasFireStation=new boolean [I];
			for (int f=0;f<F;f++) hasFireStation[Integer.parseInt(br.readLine())-1]=true;
			
			while (true) {
				String s=br.readLine();
				if (s==null) break;
				st=new StringTokenizer(s);
				if (st.countTokens()==0) break;
				else if (st.countTokens()==3) {
					int it=Integer.parseInt(st.nextToken())-1;
					int it2=Integer.parseInt(st.nextToken())-1;
					int length=Integer.parseInt(st.nextToken());
					adjMat[it][it2]=length;
					adjMat[it2][it]=length;
				}
			}
			
			for (int k=0;k<I;k++) for (int i=0;i<I;i++) for (int j=0;j<I;j++) {
				adjMat[i][j]=Math.min(adjMat[i][j], adjMat[i][k]+adjMat[k][j]);
			}
			
			int ans=1; //No solution = 1.
			int ansMin=Integer.MAX_VALUE;
			for (int test=0;test<I;test++) if (!hasFireStation[test]) {
				int beforeNewMaxDist=Integer.MIN_VALUE;
				for (int src=0;src<I;src++) {
					int distToNearestFireStation=NOEDGE;
					for (int dest=0;dest<I;dest++) if (hasFireStation[dest]) distToNearestFireStation=Math.min(distToNearestFireStation, adjMat[src][dest]);
					beforeNewMaxDist=Math.max(beforeNewMaxDist, distToNearestFireStation);
				}
				
				int afterNewMaxDist=Integer.MIN_VALUE;
				for (int src=0;src<I;src++) {
					int distToNearestFireStation=NOEDGE;
					for (int dest=0;dest<I;dest++) if (hasFireStation[dest] || dest==test) distToNearestFireStation=Math.min(distToNearestFireStation, adjMat[src][dest]);
					afterNewMaxDist=Math.max(afterNewMaxDist, distToNearestFireStation);
				}
				
				if (afterNewMaxDist<beforeNewMaxDist && afterNewMaxDist<ansMin) {
					ansMin=afterNewMaxDist;
					ans=test+1;
				}
			}
			
			if (testCase>0) System.out.println();
			System.out.println(ans);
		}
	}

}