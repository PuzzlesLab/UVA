import java.util.Scanner;

class Main {
	
	private static double distanceBetweenPoints (double x1, double y1, double x2, double y2) {
		double dx=x2-x1;
		double dy=y2-y1;
		return Math.sqrt(dx*dx+dy*dy);
	}
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		double INF=2048; //0,0 to 1024,1024 = 1024 km
		int testCaseCount=sc.nextInt();
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			int N=sc.nextInt();
			double [][] coordinate=new double [N][2];
			for (int n=0;n<N;n++) {
				coordinate[n][0]=sc.nextInt();
				coordinate[n][1]=sc.nextInt();
			}
			
			double [][] adjMat=new double [N][N];
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) adjMat[i][i2]=INF;
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) {
				double dist = distanceBetweenPoints(coordinate[i][0],coordinate[i][1],coordinate[i2][0],coordinate[i2][1]);
				if (dist<=10) adjMat[i][i2]=dist;
			}
					
			for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++)
				if (adjMat[i][k]!=INF && adjMat[k][j]!=INF) adjMat[i][j]=Math.min(adjMat[i][j], adjMat[i][k]+adjMat[k][j]);
			
			boolean allLinked=true;
			double max=0;
			for (int i=0;i<N;i++) {
				boolean localLinked=false;
				for (int j=0;j<N;j++) {
					max=Math.max(adjMat[i][j], max);
					localLinked |= (i!=j && adjMat[i][j]!=INF);
				}
				allLinked &= localLinked;
			}
			allLinked &= (max!=INF);
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(testCase);
			sb.append(":\n");
			if (!allLinked) sb.append("Send Kurdy\n");
			else sb.append(String.format("%.4f\n", max));
			
			System.out.println(sb.toString());
		}
	}

}