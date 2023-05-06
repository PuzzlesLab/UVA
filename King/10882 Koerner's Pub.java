import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int X=Integer.parseInt(st.nextToken());
			int Y=Integer.parseInt(st.nextToken());
			int Z=Integer.parseInt(st.nextToken());
			int min=-1, max=-1;

			if (X<=100 && Y<=100 & Z<=100) {
				int nAB=100-X;
				int nBC=100-Y;
				int nAC=100-Z;
				int sum=(nAB+nBC+nAC)>>1;
				min=Math.max(0,100-sum);
				max=Math.min(X,Math.min(Y,Z));
				if (min>max) {
					min=-1;
					max=-1;
				}
			}
			
			if (min!=-1 && max!=-1) {
				System.out.printf("Case #%d: Between %d and %d times.\n",n,min,max);
			} else {
				System.out.printf("Case #%d: The records are faulty.\n",n);
			}
		}
	}
}
