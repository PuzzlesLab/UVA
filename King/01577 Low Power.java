import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static int K;
	private static int [] Diff;

	private static boolean check(int maxD) {
		int pairs=0;
		int used=0;
		for (int i=0;i<Diff.length && pairs<N;i++) {
			if (Diff[i]<=maxD) {
				pairs++;
				i++;
				used+=2;
			} else used++;
			if (used>2*pairs*K) break;  // Insufficient remaining batteries.
		}
		return pairs==N;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int [] batteries=new int [2*N*K];
			for (int i=0;i<batteries.length;i++) batteries[i]=Integer.parseInt(st.nextToken());
			Arrays.sort(batteries);
			Diff=new int [batteries.length-1];
			for (int i=0;i<Diff.length;i++) Diff[i]=batteries[i+1]-batteries[i];

			int min=Integer.MAX_VALUE>>1;
			int max=0;
			for (int i=0;i<Diff.length;i++) {
				max=Math.max(max,Diff[i]);
				min=Math.min(min,Diff[i]);
			}
			while (max>min+1) {
				int mid=(min+max)>>1;
				if (check(mid)) max=mid;
				else min=mid+1;
			}
			if (check(min)) max=min;
			System.out.println(max);
		}
	}

}