import java.util.Arrays;
import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int a =1;
		while (true) {
			int N=sc.nextInt();
			if (N==-1) break;
			boolean [][] adjacencyMat=new boolean [N][N];
			
			int [] energy=new int [N];
			for (int i=0;i<N;i++) {
				energy[i]=sc.nextInt();
				int R=sc.nextInt();
				for (int r=0;r<R;r++) adjacencyMat[i][sc.nextInt()-1]=true;
			}
			
			//Connectedness
			boolean [][] connectedness=new boolean [N][N];
			for (int n1=0;n1<N;n1++) for (int n2=0;n2<N;n2++) if (adjacencyMat[n1][n2] || connectedness[n1][n2]) {
				connectedness[n1][n2]=true;
				for (int n3=0;n3<N;n3++) connectedness[n1][n3] |= adjacencyMat[n2][n3];
			}

			boolean win=false;

			//Bellman-ford
			int [] dist=new int [N];
			Arrays.fill(dist, Integer.MIN_VALUE);
			dist[0]=100;
			for (int n=0;n<N-1;n++) 
				for (int src=0;src<N;src++) if (dist[src]>0) 
					for (int dest=0;dest<N;dest++) if (adjacencyMat[src][dest]) dist[dest]=Math.max(dist[dest], dist[src]+energy[dest]);
			win=dist[N-1]>0; //Able to reach end point without cycle?
			
			if (!win) {
				//Contains positive cycle that can both reach start and end.
				for (int src=0;src<N && !win;src++) if (dist[src]>0) 
					for (int dest=0;dest<N &&!win;dest++) if (adjacencyMat[src][dest]) {
						win = connectedness[dest][N-1] && dist[dest]<dist[src]+energy[dest];
					}
			}
			
			System.out.println(win ? "winnable" : "hopeless");
		}
	}

}
