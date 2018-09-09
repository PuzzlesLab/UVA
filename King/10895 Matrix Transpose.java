import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken()), N=Integer.parseInt(st.nextToken());
			
			int [][] mat=new int [M][N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int r=Integer.parseInt(st.nextToken());
				int [] cols=new int [r];
				for (int c=0;c<cols.length;c++) cols[c]=Integer.parseInt(st.nextToken())-1;
				
				st=new StringTokenizer(br.readLine());
				for (int c=0;c<cols.length;c++) mat[m][cols[c]]=Integer.parseInt(st.nextToken());
			}
			
			int [][] matT=new int [N][M];
			for (int m=0;m<M;m++) for (int n=0;n<N;n++) matT[n][m]=mat[m][n];
			StringBuilder sb=new StringBuilder();
			sb.append(N); sb.append(' '); sb.append(M); sb.append('\n');
			
			for (int row=0;row<matT.length;row++) {
				int colsCount=0;
				int [] cols=new int[M];
				for (int col=0;col<matT[row].length;col++) if (matT[row][col]!=0) cols[colsCount++]=col+1;
				
				sb.append(colsCount);
				for (int col=0;col<colsCount;col++) {
					sb.append(" ");
					sb.append(cols[col]);
				}
				sb.append('\n');
				for (int col=0;col<colsCount;col++) {
					sb.append(matT[row][cols[col]-1]);
					sb.append(' ');
				}
				if (colsCount>0) sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}

	}

}
