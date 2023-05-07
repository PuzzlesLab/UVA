import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static int findLowIdx(long [] bound, int targ, int max) {
		int min=1;
		while (min<=max) {
			int mid=(min+max)>>1;
			if (bound[mid]>=targ) max=mid-1;
			else min=mid+1;
		}
		return min;
	}

	public static void main(String[] args) throws Exception {
		int MAX=1000001;
		long [] bound=new long [MAX];
		bound[1]=1;
		bound[2]=3;

		int maxFound=2;
		for (int n=3;n<bound.length;n++) {
			bound[n]=bound[n-1]+findLowIdx(bound,n,maxFound);
			maxFound++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;

			System.out.println(findLowIdx(bound,N,maxFound));
		}
	}

}
