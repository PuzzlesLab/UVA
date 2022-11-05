import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		final int MAX=1000000;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);

			int [][] dist=new int [N][N];
			for (int n=0;n<N;n++) {
				Arrays.fill(dist[n],MAX);
				dist[n][n]=0;
			}
			boolean [][] adjMat=new boolean [N][N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) {
					int next=Integer.parseInt(st.nextToken())-1;
					adjMat[n][next]=true;
					dist[n][next]=1;
				}
			}

			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
				int nd=dist[i][k]+dist[k][j];
				if (dist[i][j]>nd) dist[i][j]=nd;
			}
			
			int Q=Integer.parseInt(br.readLine());
			for (int q=0;q<Q;q++) {
				boolean [] subset=new boolean [N];
				StringTokenizer st=new StringTokenizer(br.readLine());
				while (st.hasMoreTokens()) subset[Integer.parseInt(st.nextToken())-1]=true;
				
				boolean [] partOfSp=new boolean [N];
				for (int k=0;k<N;k++) if (!subset[k]) {
					for (int i=0;i<N && !partOfSp[k];i++) if (subset[i] && i!=k) {
						for (int j=0;j<N && !partOfSp[k];j++) if (subset[j] && j!=i && j!=k) {
							int nd=dist[i][k]+dist[k][j];
							if (nd==dist[i][j]) partOfSp[k]=true;
						}
					}
				}
				boolean ans=true;
				for (int n=0;n<N;n++) ans&=subset[n]||partOfSp[n];
				System.out.println(ans?"yes":"no");
			}
		}
	}

}