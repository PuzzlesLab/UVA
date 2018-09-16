import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			int size=Integer.parseInt(br.readLine().trim().replace("N = ", ""));
			long [][] mat=new long[size][size];
			for (int i=0;i<size;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<size;i2++) mat[i][i2]=Long.parseLong(st.nextToken());
			}
			
			boolean flag=true;
			for (int x=0;x<size && flag;x++) for (int y=0;y<size && flag;y++) {
				int xa=size-1-x;
				int ya=size-1-y;
				flag&=(mat[x][y]==mat[xa][ya] && mat[x][y]>=0);
			}
			
			if (flag) System.out.printf("Test #%d: Symmetric.\n", t);
			else System.out.printf("Test #%d: Non-symmetric.\n", t);
		}
	}

}