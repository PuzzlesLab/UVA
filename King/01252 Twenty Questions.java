import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int [] Objs;
	private static int M;
	private static int [][] Dp;

	private static int compute(int fMask, int aMask) {
		if (Dp[fMask][aMask]==-1) {
			int count=0;
			for (int i=0;i<Objs.length;i++) if ((Objs[i]&fMask)==aMask) count++;
			if (count<=1) return Dp[fMask][aMask]=0;
			
			int min=10000000;
			for (int m=0;m<M;m++) if ((fMask&(1<<m))==0) {
				int count0=0;
				int count1=0;
				
				for (int i=0;i<Objs.length;i++) if ((Objs[i]&fMask)==aMask) {
					boolean isZero=(Objs[i]&(1<<m))==0;
					if (isZero) count0++;
					else count1++;
				}
				if (count0!=0 && count1!=0) {
					min=Math.min(min,1+Math.max(compute(fMask|(1<<m),aMask),compute(fMask|(1<<m),aMask|(1<<m))));
				}
			}
			Dp[fMask][aMask]=min;
		}

		return Dp[fMask][aMask];
	}

	public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (!(s=br.readLine()).equals("0 0")) {
        	StringTokenizer st=new StringTokenizer(s);
        	M=Integer.parseInt(st.nextToken());
        	int N=Integer.parseInt(st.nextToken());
        	
        	Objs=new int [N];
        	for (int n=0;n<N;n++) Objs[n]=Integer.parseInt(br.readLine(),2);

        	Dp=new int [1<<M][1<<M];
        	for (int i=0;i<Dp.length;i++) Arrays.fill(Dp[i],-1);
        	System.out.println(compute(0,0));
        }
	}

}