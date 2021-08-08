import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			long [] coins=new long [N];
			long average=0;
			for (int n=0;n<N;n++) {
				coins[n]=Long.parseLong(br.readLine());
				average+=coins[n];
			}
			average/=N;
			
			long [] diff=new long [N];
			for (int n=1;n<N;n++) diff[n]=coins[n]+(diff[n-1]-average);
			Arrays.sort(diff);
			long ans=0;
			for (int n=0;n<N;n++) ans+=Math.abs(diff[n]-diff[N/2]);
			System.out.println(ans);
		}
	}
}