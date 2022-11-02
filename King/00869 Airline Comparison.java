import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static void floydWarshall(boolean [][] flag) {
		for (int k=0;k<flag.length;k++) for (int i=0;i<flag.length;i++) for (int j=0;j<flag.length;j++) {
			flag[i][j]|=flag[i][k]&&flag[k][j];
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			br.readLine();

			boolean [][] company1=new boolean [26][26];
			int N=Integer.parseInt(br.readLine());
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int a=st.nextToken().charAt(0)-'A';
				int b=st.nextToken().charAt(0)-'A';
				company1[a][b]=true;
			}
			floydWarshall(company1);
			
			boolean [][] company2=new boolean [26][26];
			int M=Integer.parseInt(br.readLine());
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int a=st.nextToken().charAt(0)-'A';
				int b=st.nextToken().charAt(0)-'A';
				company2[a][b]=true;
			}
			floydWarshall(company2);


			boolean same=true;
			for (int i=0;i<company1.length && same;i++) for (int i2=0;i2<company1.length && same;i2++) {
				same&=company1[i][i2]==company2[i][i2];
			}
			
			if (tc>0) System.out.println();
			System.out.println(same?"YES":"NO");
		}
	}

}
