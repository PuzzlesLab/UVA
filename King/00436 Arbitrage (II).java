import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			HashMap<String,Integer> currencyIdxMap=new HashMap<>();
			for (int n=0;n<N;n++) currencyIdxMap.put(br.readLine(),n);
			
			int M=Integer.parseInt(br.readLine());
			double [][] dist=new double [N][N];
			for (int n=0;n<N;n++) dist[n][n]=1;
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int n1=currencyIdxMap.get(st.nextToken());
				double rate=Double.parseDouble(st.nextToken());
				int n2=currencyIdxMap.get(st.nextToken());
				dist[n1][n2]=rate;
			}

			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) dist[i][j]=Math.max(dist[i][j],dist[i][k]*dist[k][j]);

			boolean found=false;
			for (int n=0;n<N && !found;n++) found|=Double.isNaN(dist[n][n]) || dist[n][n]>1; // When dist becomes too big, it will become NaN
			System.out.printf("Case %d: %s\n", tc++, found?"Yes":"No");

			br.readLine(); // empty line
		}
	}

}