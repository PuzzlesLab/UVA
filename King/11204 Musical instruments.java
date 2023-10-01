import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			int [] want=new int [N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				for (int n=0;n<N;n++) {
					int pref=Integer.parseInt(st.nextToken());
					if (pref==1) want[n]++;
				}
			}
			
			long ans=1;
			for (int n=0;n<N;n++) if (want[n]>1) ans*=want[n];
			System.out.println(ans);
		}
	}

}
