import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] foods=new int [N];
			double avg=0;
			for (int n=0;n<N;n++) {
				foods[n]=Integer.parseInt(st.nextToken());
				avg+=foods[n];
			}
			avg/=N;

			int closestCake=0;
			for (int m=0;m<M;m++) {
				double diff1=Math.abs(foods[m]-avg);
				double diff2=Math.abs(foods[closestCake]-avg);
				if (diff1<diff2 || (diff1==diff2 && foods[m]<foods[closestCake])) {
					closestCake=m;
				}
			}

			int closestDrink=M;
			for (int k=M;k<M+K;k++) {
				double diff1=Math.abs(foods[k]-avg);
				double diff2=Math.abs(foods[closestDrink]-avg);
				if (diff1<diff2 || (diff1==diff2 && foods[k]<foods[closestDrink])) {
					closestDrink=k;
				}
			}
			
			System.out.printf("Case #%d: %d %d\n", tc, foods[closestCake], foods[closestDrink]);
		}
	}

}