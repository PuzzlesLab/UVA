import java.util.HashMap;
import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCase=1;
		int NOEDGE=100;
		while (true) {
			int P=Integer.parseInt(sc.next());
			int R=Integer.parseInt(sc.next());
			if (P==0 && R==0) break;
			
			HashMap<String, Integer> nameToID=new HashMap<>();
			int idMax=0;
			int [][] adjMat=new int [P][P];
			for (int i=0;i<P;i++) for (int i2=0;i2<P;i2++) adjMat[i][i2]=(i==i2) ? 0 : NOEDGE;
			
			for (int r=0;r<R;r++) {
				String n1=sc.next();
				if (!nameToID.containsKey(n1)) nameToID.put(n1, idMax++);
				int i=nameToID.get(n1);

				String n2=sc.next();
				if (!nameToID.containsKey(n2)) nameToID.put(n2, idMax++);
				int j=nameToID.get(n2);
				
				adjMat[i][j]=1;
				adjMat[j][i]=1;
			}
			
			for (int k=0;k<P;k++) for (int i=0;i<P;i++) for (int j=0;j<P;j++) adjMat[i][j]=Math.min(adjMat[i][j], adjMat[i][k]+adjMat[k][j]);
			
			int max=Integer.MIN_VALUE;
			for (int i=0;i<P;i++) for (int j=0;j<P;j++) max=Math.max(max, adjMat[i][j]);
			System.out.printf("Network %d: %s\n\n",testCase++,(max==Integer.MIN_VALUE || max>=NOEDGE) ? "DISCONNECTED" : max);
		}
	}

}