import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;
			
			st=new StringTokenizer(br.readLine());
			long [] cCount=new long [M];
			for (int n=0;n<N;n++) cCount[Integer.parseInt(st.nextToken())-1]++;

			long way3=0;
			for (int m=0;m<M;m++) {
				// 3 same countries
				way3+=(cCount[m]*(cCount[m]-1)*(cCount[m]-2))/6;
				// 3 different countries
				for (int m2=m+1;m2<M;m2++) for (int m3=m2+1;m3<M;m3++) {
					way3+=cCount[m]*cCount[m2]*cCount[m3];
				}
			}

			long way4=0;
			for (int m=0;m<M;m++) {
				// 4 different countries
				for (int m2=m+1;m2<M;m2++) for (int m3=m2+1;m3<M;m3++) for (int m4=m3+1;m4<M;m4++) {
					way4+=cCount[m]*cCount[m2]*cCount[m3]*cCount[m4];
				}
				if (cCount[m]>=3) {
					long base=(cCount[m]*(cCount[m]-1)*(cCount[m]-2))/6;
					// 4 same country
					way4+=(base*(cCount[m]-3))>>2;
					// 3 same country
					way4+=(base*(N-cCount[m]));
				}
			}
			// 2 same + 1 diff + 1 diff
			for (int m=0;m<M;m++) for (int m2=m+1;m2<M;m2++) for (int m3=m2+1;m3<M;m3++) {
				long base=cCount[m]*cCount[m2]*cCount[m3];
				long m1s=cCount[m]-1;
				long m2s=cCount[m2]-1;
				long m3s=cCount[m3]-1;
				
				way4+=(base*m1s)>>1;
				way4+=(base*m2s)>>1;
				way4+=(base*m3s)>>1;
			}

			StringBuilder sb=new StringBuilder();
			sb.append(way3);
			sb.append(' ');
			sb.append(way4);
			System.out.println(sb);
		}
	}

}