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
			StringTokenizer st=new StringTokenizer(s);
			int P=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			int BH=Integer.parseInt(st.nextToken());
			int OF=Integer.parseInt(st.nextToken());
			int YH=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			
			int [][] dist=new int [P+1][P+1];
			int [][] dist2=new int [P+1][P+1];
			for (int p=0;p<dist.length;p++) {
				Arrays.fill(dist[p],MAX);
				dist[p][p]=0;
				Arrays.fill(dist2[p],MAX);
				dist2[p][p]=0;
			}
			int [][] parent=new int [P+1][P+1];
			for (int p=0;p<parent.length;p++) Arrays.fill(parent[p],p);

			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				int p1=Integer.parseInt(st.nextToken());
				int p2=Integer.parseInt(st.nextToken());
				int d=Integer.parseInt(st.nextToken());
				dist[p1][p2]=d;
				dist[p2][p1]=d;
				dist2[p1][p2]=d;
				dist2[p2][p1]=d;
			}

			for (int k=1;k<=P;k++) for (int i=1;i<=P;i++) for (int j=1;j<=P;j++) {
				int nd=dist[i][k]+dist[k][j];
				if (nd<dist[i][j]) {
					dist[i][j]=nd;
					parent[i][j]=parent[k][j];
				}
			}

			boolean [] blocked=new boolean [P+1];
			for (int p=1;p<=P;p++) if (dist[BH][OF]==dist[BH][p]+dist[p][OF]) blocked[p]=true;

			if (blocked[YH] || blocked[M]) {
				System.out.println("MISSION IMPOSSIBLE.");
				continue;
			}

			for (int k=1;k<=P;k++) if (!blocked[k]) {
				for (int i=1;i<=P;i++) if (!blocked[i]){
					for (int j=1;j<=P;j++) if (!blocked[j]) {
						dist2[i][j]=Math.min(dist2[i][j],dist2[i][k]+dist2[k][j]);
					}
				}
			}

			System.out.println((dist2[YH][M]==MAX)?"MISSION IMPOSSIBLE.":dist2[YH][M]);
		}
	}

}
