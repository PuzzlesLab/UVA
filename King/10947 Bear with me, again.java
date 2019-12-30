import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static class Island {
		int x, y, r;
		public Island(int x, int y, int r) {
			this.x=x;
			this.y=y;
			this.r=r;
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		double NOEDGE=999999.9;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int K=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int maxDist=K*M;
			
			st=new StringTokenizer(br.readLine());
			Island start=new Island(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			st=new StringTokenizer(br.readLine());
			Island end=new Island(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			
			int N=Integer.parseInt(br.readLine());
			Island [] islands=new Island[N+2];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				islands[n]=new Island(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			}
			islands[N]=start;
			islands[N+1]=end;
			
			double [][] adjMat=new double[islands.length][islands.length];
			for (int i=0;i<islands.length;i++) for (int j=0;j<islands.length;j++) adjMat[i][j]=NOEDGE;
			for (int i=0;i<islands.length;i++) for (int j=0;j<islands.length;j++) if (i!=j) {
				int dx=islands[i].x-islands[j].x;
				int dy=islands[i].y-islands[j].y;
				int d=dx*dx+dy*dy;
				double dist=Math.max(0, Math.sqrt(d)-islands[i].r-islands[j].r);
				if (dist<=maxDist) adjMat[i][j]=maxDist;
			} else adjMat[i][j]=0;
			
			for (int k=0;k<islands.length;k++) for (int i=0;i<islands.length;i++) for (int j=0;j<islands.length;j++) adjMat[i][j]=Math.min(adjMat[i][j], adjMat[i][k]+adjMat[k][j]);
			
			if (adjMat[N][N+1]<NOEDGE) System.out.println("Larry and Ryan will escape!");
			else System.out.println("Larry and Ryan will be eaten to death.");
		}
		
	}

}