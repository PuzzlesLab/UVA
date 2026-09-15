import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_INIT_SIZE=8;
	private static final int MAX_COLORS=4;
	private static final int MAX_GROUP_SIZE=3;
	private static int [] Marbles;
	private static long [][][][][][][][] Dp=new long [MAX_INIT_SIZE][MAX_INIT_SIZE][MAX_INIT_SIZE][MAX_INIT_SIZE][MAX_COLORS][MAX_GROUP_SIZE+1][MAX_COLORS+1][MAX_GROUP_SIZE+1];

	private static long count(int rem0, int rem1, int rem2, int rem3, int firstM, int firstSize, int lastM, int lastSize) {
		if (rem0==0 && rem1==0 && rem2==0 && rem3==0) return (lastM!=firstM && lastSize!=firstSize)?1:0;

		if (Dp[rem0][rem1][rem2][rem3][firstM][firstSize][lastM][lastSize]==-1) {
			long ans=0;
			
	    	if (rem0>0 && lastM!=0) for (int size=1;size<=Math.min(rem0,MAX_GROUP_SIZE);size++) {
    			if (size==lastSize) continue;
				ans+=count(rem0-size,rem1,rem2,rem3,firstM,firstSize,0,size);
	    	}
	    	if (rem1>0 && lastM!=1) for (int size=1;size<=Math.min(rem1,MAX_GROUP_SIZE);size++) {
    			if (size==lastSize) continue;
				ans+=count(rem0,rem1-size,rem2,rem3,firstM,firstSize,1,size);
	    	}
	    	if (rem2>0 && lastM!=2) for (int size=1;size<=Math.min(rem2,MAX_GROUP_SIZE);size++) {
    			if (size==lastSize) continue;
				ans+=count(rem0,rem1,rem2-size,rem3,firstM,firstSize,2,size);
	    	}
	    	if (rem3>0 && lastM!=3) for (int size=1;size<=Math.min(rem3,MAX_GROUP_SIZE);size++) {
    			if (size==lastSize) continue;
				ans+=count(rem0,rem1,rem2,rem3-size,firstM,firstSize,3,size);
	    	}
	    	Dp[rem0][rem1][rem2][rem3][firstM][firstSize][lastM][lastSize]=ans;
		}

		return Dp[rem0][rem1][rem2][rem3][firstM][firstSize][lastM][lastSize];
	}

	public static void main(String[] args) throws Exception {
    	for (int i=0;i<Dp.length;i++) for (int i2=0;i2<Dp[i].length;i2++)
        	for (int i3=0;i3<Dp[i][i2].length;i3++) for (int i4=0;i4<Dp[i][i2][i3].length;i4++)
            	for (int i5=0;i5<Dp[i][i2][i3][i4].length;i5++) for (int i6=0;i6<Dp[i][i2][i3][i4][i5].length;i6++)
                	for (int i7=0;i7<Dp[i][i2][i3][i4][i5][i6].length;i7++)
                		Arrays.fill(Dp[i][i2][i3][i4][i5][i6][i7],-1);
		
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int TC=Integer.parseInt(br.readLine());

        for (int tc=0;tc<TC;tc++) {
        	StringTokenizer st=new StringTokenizer(br.readLine());
        	Marbles=new int [MAX_COLORS];
        	int N=Integer.parseInt(st.nextToken());
        	for (int n=0;n<N;n++) Marbles[n]=Integer.parseInt(st.nextToken()); 

        	int group=0;
        	for (int m=0;m<Marbles.length;m++) if (Marbles[m]>0) group++;
        	if (group==0) {
        		System.out.println(1);
        		continue;
        	} else if (group==1) {
        		int size=0;
        		for (int m=0;m<Marbles.length;m++) if (Marbles[m]>0) {
        			size=Marbles[m];
        			break;
        		}
        		System.out.println(size<=3?1:0);
        		continue;
        	}

        	long ans=0;
        	for (int mLeft=0;mLeft<Marbles.length;mLeft++) if (Marbles[mLeft]>0) {
        		for (int sLeft=1;sLeft<=Math.min(Marbles[mLeft],MAX_GROUP_SIZE);sLeft++) {
        			Marbles[mLeft]-=sLeft;
    				ans+=count(Marbles[0],Marbles[1],Marbles[2],Marbles[3],mLeft,sLeft,mLeft,sLeft);
    				Marbles[mLeft]+=sLeft;
        		}
        	}
        	System.out.println(ans);
        }
	}

}