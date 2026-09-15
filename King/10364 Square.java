import java.util.ArrayList;
import java.util.Scanner;

class Main {

	private static int [] Sticks;
	private static ArrayList<Integer> SolMasks;
	private static int Side;

	private static void compute(int mask, int n, int remLen) {
		if (remLen==0) {
			SolMasks.add(mask);
			return;
		}
		if (n==Sticks.length) return;

		if (remLen-Sticks[n]>=0) {
			compute(mask|(1<<n),n+1,remLen-Sticks[n]);
		}
		compute(mask,n+1,remLen);
	}

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
        int TC=sc.nextInt();
        for (int tc=0;tc<TC;tc++) {
        	int M=sc.nextInt();
        	
        	Sticks=new int [M];
        	int sum=0;
        	for (int m=0;m<M;m++) {
        		Sticks[m]=sc.nextInt();
        		sum+=Sticks[m];
        	}

        	if (M<4 || sum%4!=0) {
        		System.out.println("no");
        		continue;
        	}

        	Side=sum>>2;
        	SolMasks=new ArrayList<>();
        	compute(0,0,Side);
        	
        	boolean hasSol=false;
        	int endMask=(1<<M)-1;

        	for (int i=0;i<SolMasks.size() && !hasSol;i++) {
        		int mask1=SolMasks.get(i);
        		int mask1BC=Integer.bitCount(mask1);

        		for (int i2=i+1;i2<SolMasks.size() && !hasSol;i2++) {
        			int mask2=SolMasks.get(i2);
        			int mask2BC=Integer.bitCount(mask2);
        			if (Integer.bitCount(mask1|mask2)!=mask1BC+mask2BC) continue;

            		for (int i3=i2+1;i3<SolMasks.size() && !hasSol;i3++) {
            			int mask3=SolMasks.get(i3);
            			int mask3BC=Integer.bitCount(mask3);
            			if (Integer.bitCount(mask1|mask2|mask3)!=mask1BC+mask2BC+mask3BC) continue;

            			for (int i4=i3+1;i4<SolMasks.size() && !hasSol;i4++) {
            				hasSol=(mask1|mask2|mask3|SolMasks.get(i4))==endMask;
            			}
            			
            		}
        		}
        	}

        	System.out.println(hasSol?"yes":"no");
        }
	}

}