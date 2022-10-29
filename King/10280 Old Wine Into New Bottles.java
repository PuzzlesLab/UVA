import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static class Bottle {
		int min, max;
		int maxBottles;
		
		public Bottle(int min, int max) {
			this.min=min;
			this.max=max;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine();
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			int W=Integer.parseInt(st.nextToken())*1000;
			int N=Integer.parseInt(st.nextToken());

			Bottle [] bottles=new Bottle[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				bottles[n]=new Bottle(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}

			int coverAbove=Integer.MAX_VALUE;
			for (int n=0;n<N;n++) {
				bottles[n].maxBottles=(bottles[n].max/(bottles[n].max-bottles[n].min))+1;
				coverAbove=Math.min(coverAbove,bottles[n].maxBottles*bottles[n].min);
				/*
				 * Given a type of bottle, when we add more bottles,
				 * the total range becomes larger.
				 * 
				 * min <-> max ... (x)min <-> (x)max ... (x+1)min <-> (x+1)max
				 * 
				 * and exists such condition where (x)max >= (x+1)min,
				 * this means ranges behind start overlapping and should cover all volumes.
				 * 
				 * We iterate through all bottles to find this "magic" value and get the minimum among them.
				 */
			}
			
			int ans=0;
			if (W<coverAbove) { // Only perform dp if we can't confirm volumes are covered.
				boolean [] base=new boolean [4501];
				for (int n=0;n<N;n++) for (int v=bottles[n].min;v<=bottles[n].max;v++) base[v]=true;

				boolean [] dp=new boolean [W+1];
				dp[0]=true;
				for (int i=0;i<base.length;i++) if (base[i]) {
					for (int i2=i;i2<dp.length;i2++) if (dp[i2-i]) {
						dp[i2]=true;
					}
				}

				for (int i=dp.length-1;i>=0;i--) if (dp[i]) {
					ans=W-i;
					break;
				}
			}

			if(tc>0) System.out.println();
			System.out.println(ans);
		}
	}

}