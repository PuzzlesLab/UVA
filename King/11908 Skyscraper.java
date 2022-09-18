import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static class Advertisement implements Comparable<Advertisement> {
		int startFloor;
		int endFloor;
		int profit;
		
		public int compareTo(Advertisement ad) {
			return this.endFloor-ad.endFloor;
		}
	}

	private static int firstLower(Advertisement [] ads, int start, int end, int find) {
		int idx=0;
		while (start<=end) {
			int mid=(start+end)/2;
			if (ads[mid].endFloor<=find) {
				start=mid+1;
				idx=mid;
			} else end=mid-1;
		}
		return idx;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			
			Advertisement [] ads=new Advertisement[N+1];
			ads[0]=new Advertisement();
			for (int n=1;n<=N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				ads[n]=new Advertisement();
				ads[n].startFloor=Integer.parseInt(st.nextToken());
				ads[n].endFloor=ads[n].startFloor+Integer.parseInt(st.nextToken());
				ads[n].profit=Integer.parseInt(st.nextToken());
			}
			Arrays.sort(ads);

			int [] dp=new int [N+1];
			for (int n=1;n<=N;n++) dp[n]=Math.max(dp[n-1],dp[firstLower(ads,0,n-1,ads[n].startFloor)]+ads[n].profit);
			System.out.printf("Case %d: %d\n",tc,dp[N]);
		}
		
	}

}
