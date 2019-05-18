import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int [][] adjMat=new int [100][100];
			for (int i=0;i<100;i++) for (int i2=0;i2<100;i2++) adjMat[i][i2]=Integer.MAX_VALUE;
			HashSet<Integer> nodes=new HashSet<>();
			while (true) {
				int src=Integer.parseInt(st.nextToken())-1;
				int dest=Integer.parseInt(st.nextToken())-1;
				if (src==-1 || dest==-1) break;
				adjMat[src][dest]=1;
				nodes.add(src);
				nodes.add(dest);
			}
			
			for (int k=0;k<100;k++) if (nodes.contains(k)) 
				for (int i=0;i<100;i++) if (i!=k && nodes.contains(i) && adjMat[i][k]!=Integer.MAX_VALUE)
					for (int j=0;j<100;j++) if (i!=j && j!=k && nodes.contains(j) && adjMat[k][j]!=Integer.MAX_VALUE)
						adjMat[i][j]=Math.min(adjMat[i][j], adjMat[i][k]+adjMat[k][j]);
			
			double total=0;
			int pair=0;
			for (int i=0;i<100;i++) if (nodes.contains(i))
				for (int j=0;j<100;j++) if (nodes.contains(j) && i!=j) {
					total+=adjMat[i][j];
					pair++;
				}
			total/=pair;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCase++);
			sb.append(": average length between pages = ");
			sb.append(String.format("%.3f", total));
			sb.append(" clicks");
			System.out.println(sb.toString());
		}
	}

}
