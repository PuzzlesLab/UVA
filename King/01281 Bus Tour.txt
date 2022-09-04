import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int NO_TIME=1000000;
	private static int [][] ShortestTime;
	private static int HotelsCount;

	// Test data : https://icpc.global/worldfinals/problems/2012%20ACM-ICPC%20World%20Finals/icpc2012-testdata.tar.gz (C)

	private static int countOnes(int n) {
		int ans=0;
		while (n>0) {
			ans+=n%2;
			n>>=1;
		}
		return ans;
	}

	private static int [][] tsp(int src) {
		int half=(HotelsCount+1)/2;
		int [][] dp=new int [1<<HotelsCount][HotelsCount];
		for (int i=0;i<dp.length;i++) Arrays.fill(dp[i],NO_TIME);
		for (int i=0;i<HotelsCount;i++) dp[1<<i][i]=ShortestTime[src][i+1]; // When set contains only 1 hotel.

		for (int mask=0;mask<dp.length;mask++) {
			if (countOnes(mask)>half) continue;

			int tempMask=mask;
			int pos=0;
			while (tempMask>0) {
				if (tempMask%2==1) { // Visit hotel
					int prev=mask-(1<<pos); // Get set without current hotel
					int ans=NO_TIME;
					for (int i=0;i<dp[prev].length;i++) ans=Math.min(ans,dp[prev][i]+ShortestTime[i+1][pos+1]); // Try come from every hotel from previous set
					dp[mask][pos]=Math.min(ans,dp[mask][pos]); // Store best solution for current hotel
				}
				pos++;
				tempMask>>=1;
			}
		}
		
		return dp;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			HotelsCount=N-2;

			ShortestTime=new int [N][N];
			for (int i=0;i<N;i++) {
				Arrays.fill(ShortestTime[i],NO_TIME);
				ShortestTime[i][i]=0;
			}
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken());
				int n2=Integer.parseInt(st.nextToken());
				int t=Integer.parseInt(st.nextToken());
				ShortestTime[n1][n2]=t;
				ShortestTime[n2][n1]=t;
			}

			// Floyd-Warshall
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
				ShortestTime[i][j]=Math.min(ShortestTime[i][j], ShortestTime[i][k]+ShortestTime[k][j]);
			}

			int ans=NO_TIME;
			if (N==3) ans=2*(ShortestTime[0][1]+ShortestTime[1][2]);
			else {
				int [][] dp1=tsp(0);
				int [][] dp2=tsp(N-1);
				
				int lim=(HotelsCount%2==0) ? dp1.length/2 : dp1.length;
				
				for (int fMask=0;fMask<lim;fMask++) if (countOnes(fMask)==HotelsCount/2) {
					int bMask=dp1.length-1-fMask;

					int fDist=NO_TIME;
					// Brute force first half last node + second half first node
					for (int p1=0;p1<dp1[fMask].length;p1++) for (int p2=0;p2<dp2[bMask].length;p2++) {
						// Best first half from 0 + path + best second half to N-1
						fDist=Math.min(fDist,dp1[fMask][p1]+ShortestTime[p1+1][p2+1]+dp2[bMask][p2]);
					}

					int bDist=NO_TIME;
					// Brute force first half last node + second half first node
					for (int p1=0;p1<dp2[fMask].length;p1++) for (int p2=0;p2<dp1[bMask].length;p2++) {
						// Best first half from N-1 + path + best second half To 0
						bDist=Math.min(bDist,dp2[fMask][p1]+ShortestTime[p1+1][p2+1]+dp1[bMask][p2]);
					}
					
					ans=Math.min(ans,fDist+bDist);
				}
			}


			System.out.printf("Case %d: %d\n",testCase++,ans);
		}
	}

}
