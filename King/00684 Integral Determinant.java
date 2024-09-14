import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static void swapCol(double [][] mat, int col1, int col2) {
		if (col1==col2) return;

		for (int row=0;row<mat.length;row++) {
			double temp=mat[row][col1];
			mat[row][col1]=mat[row][col2];
			mat[row][col2]=temp;
		}
	}

	public static void main(String [] args) throws Exception {
		final double ROUND=0.0001;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			double [][] mat=new double [N][N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) mat[n][n2]=Long.parseLong(st.nextToken());
			}
			
			double ans=1;
			for (int i=0;i<N;i++) {
				int maxCol=i;
				for (int i2=i+1;i2<N;i2++) if (Math.abs(mat[i][i2])>Math.abs(mat[i][maxCol])) maxCol=i2;
				if (Math.abs(mat[i][maxCol])<ROUND) {
					ans=0;
					break;
				}
				if (maxCol!=i) {
					ans=-ans;
					swapCol(mat,i,maxCol);
				}
				for (int i2=i+1;i2<N;i2++) for (int i3=N-1;i3>=i;i3--) {
					mat[i3][i2]-=(mat[i3][i]*mat[i][i2])/mat[i][i];
				}
				ans*=mat[i][i];
			}

			if (ans<0) ans-=ROUND;
			else if (ans>0) ans+=ROUND;
			System.out.println((long)ans);
		}
		System.out.println('*');
 	}

}