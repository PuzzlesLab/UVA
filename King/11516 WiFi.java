import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static double [] Houses;

	private static int place(double maxDist) {
		double last=-1;
		int count=0;
		for (int m=0;m<Houses.length;m++) {
			if (last<0 || Math.abs(Houses[m]-last)>=maxDist) {
				last=Houses[m]+maxDist;
				count++;
			}
		}
		return count;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			Houses=new double [M];
			for (int m=0;m<M;m++) Houses[m]=Integer.parseInt(br.readLine());
			Arrays.sort(Houses);

			double min=0.0;
			double max=(Houses[M-1]-Houses[0])/2;
			int loop=50;
			while (max>min+1e-6 && loop-->0) {
				double mid=(min+max)/2;
				if (place(mid)<=N) max=mid; // If we need less or = AP with this dist, the dist is too far. 
				else min=mid; // We need more AP, dist is too short.
			}
			
			System.out.printf("%.1f\n",max);
		}
	}

}
