import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int N=Integer.parseInt(br.readLine());
			int [] repetition=new int [101];
			
			boolean ok=true;
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) {
					int num=Integer.parseInt(st.nextToken());
					repetition[num]++;
					if (repetition[num]>N) {
						ok=false;
					}
				}
			}
			
			System.out.printf("Case %d: %s\n",t,ok?"yes":"no");
		}
	}

}
