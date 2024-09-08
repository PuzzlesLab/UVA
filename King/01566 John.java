import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int N=Integer.parseInt(br.readLine());
			int [] mm=new int[N];
			StringTokenizer st=new StringTokenizer(br.readLine());
			for (int n=0;n<N;n++) mm[n]=Integer.parseInt(st.nextToken());
			
			int nim=0;
			int nOnes=0;
			boolean nMores=false;
			for (int n=0;n<mm.length;n++) {
				if (mm[n]==1) nOnes=(nOnes+1)&1;
				nMores|=mm[n]>1;
				nim^=mm[n];
			}
			if (nMores) {
				System.out.println((nim!=0) ? "John" : "Brother");
			} else {
				System.out.println((nOnes==0) ? "John" : "Brother");
			}
		}
 	}

}
