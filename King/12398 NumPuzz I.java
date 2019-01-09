import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int t=1;
		while ((s=br.readLine())!=null) {
			int [][] mat=new int [3][3];
			for (char c : s.toCharArray()) {
				int v=c-'a';
				int row=v/3;
				int col=v%3;
				mat[row][col]++;
				if (row>0) mat[row-1][col]++;
				if (row<mat.length-1) mat[row+1][col]++;
				if (col>0) mat[row][col-1]++;
				if (col<mat.length-1) mat[row][col+1]++;
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case #");
			sb.append(t++);
			sb.append(":\n");
			for (int i=0;i<mat.length;i++) {
				for (int i2=0;i2<mat.length;i2++) {
					sb.append(mat[i][i2]%10);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}