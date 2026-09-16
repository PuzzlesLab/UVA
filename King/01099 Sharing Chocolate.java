import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] SplitArea;
	private static int [] AreaSum;
	private static int [][] Dp;

	private static void computeAreaSum(int n, int mask, int currSum) {
		if (n==SplitArea.length) {
			AreaSum[mask]=currSum;
			return;
		}
		
		computeAreaSum(n+1,mask,currSum);
		computeAreaSum(n+1,mask|1<<n,currSum+SplitArea[n]);
	}

	private static boolean check(int mask, int x) {
		if (Integer.bitCount(mask)<=1) return true;
		
		if (Dp[mask][x]==0) {
			boolean ans=false;
			if (AreaSum[mask]%x==0) {
				int y=AreaSum[mask]/x;
				for (int m1=(mask-1)&mask;m1!=0&&!ans;m1=(m1-1)&mask) {
					// Clear lowest right set bit at a time.
					int m2=mask^m1; // m1|m2 = mask
					if (AreaSum[m1]%x==0 && AreaSum[m2]%x==0) ans|=check(m1,x)&&check(m2,x);
					if (!ans && AreaSum[m1]%y==0 && AreaSum[m2]%y==0) ans|=check(m1,AreaSum[m1]/y)&&check(m2,AreaSum[m2]/y);
				}
			}
			
			Dp[mask][x]=ans?1:2;
		}
		return Dp[mask][x]==1;
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int tc=1;
        String s;
        while (!(s=br.readLine()).equals("0")) {
        	int N=Integer.parseInt(s);
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	int x=Integer.parseInt(st.nextToken());
        	int y=Integer.parseInt(st.nextToken());

        	st=new StringTokenizer(br.readLine());
        	SplitArea=new int[N];
        	for (int n=0;n<N;n++) SplitArea[n]=Integer.parseInt(st.nextToken());
        	
        	AreaSum=new int [1<<N];
        	computeAreaSum(0,0,0);

        	boolean ans=false;
        	int endMask=(1<<N)-1;
        	if (x*y==AreaSum[endMask]) {
            	Dp=new int [1<<N][Math.max(x,y)+1];
            	ans=check(endMask,x);
        	}
        	System.out.printf("Case %d: %s\n",tc++,ans?"Yes":"No");
        }
	}

}