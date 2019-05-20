import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s.trim());
			double [][][] adjMat=new double [N+1][N][N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) adjMat[1][n][n2]=(n!=n2) ? Double.parseDouble(st.nextToken()) : 1.0;
			}
			
			int [][][] parent=new int [N+1][N][N];
			for (int i=0;i<N;i++) for (int j=0;j<N;j++) parent[1][i][j]=i;
			
			LinkedList<Integer> bestSolution=null;
			for (int c=2;c<=N && bestSolution==null;c++) {
				for (int k=0;k<N;k++) for (int i=0;i<N;i++) for (int j=0;j<N;j++) {
					double d=adjMat[c-1][i][k]*adjMat[1][k][j];
					if (d>adjMat[c][i][j]) {
						adjMat[c][i][j]=d;
						parent[c][i][j]=k;
					}
				}
				
				for (int i=0;i<N && bestSolution==null;i++) if (adjMat[c][i][i]>1.01) {
					bestSolution=new LinkedList<>();
					bestSolution.add(i+1);
					int k=i;
					for (int tempC=c;tempC>=1;tempC--) {
						bestSolution.addFirst(parent[tempC][i][k]+1);
						k=parent[tempC][i][k];
					}
				}
			}
			
			if (bestSolution!=null) {
				StringBuilder sb=new StringBuilder();
				for (int i : bestSolution) {
					sb.append(i);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
			}
			else System.out.println("no arbitrage sequence exists");
		}
	}

}