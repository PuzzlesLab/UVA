import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		br.readLine();
		for (int tc=0;tc<TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken())-1;
			int M=Integer.parseInt(st.nextToken())-1;
			
			boolean [][] cat=new boolean [R][R];
			for (int r=0;r<R;r++) cat[r][r]=true;
			while (true) {
				st=new StringTokenizer(br.readLine());
				int A=Integer.parseInt(st.nextToken())-1;
				int B=Integer.parseInt(st.nextToken())-1;
				if (A<0 && B<0) break;
				cat[A][B]=true;
			}

			boolean [][] mouse=new boolean [R][R];
			boolean [][] mouse2=new boolean [R][R];
			for (int r=0;r<R;r++) mouse[r][r]=true;
			while (true) {
				String s=br.readLine();
				if (s==null || s.isEmpty()) break;
				st=new StringTokenizer(s);
				int A=Integer.parseInt(st.nextToken())-1;
				int B=Integer.parseInt(st.nextToken())-1;
				mouse[A][B]=true;
				mouse2[A][B]=true;
			}

			for (int k=0;k<R;k++) for (int i=0;i<R;i++) for (int j=0;j<R;j++) {
				cat[i][j]|=(cat[i][k]&cat[k][j]);
				mouse[i][j]|=(mouse[i][k]&mouse[k][j]);
			}

			boolean meet=false;
			for (int r=0;r<R && !meet;r++) meet=cat[C][r]&&mouse[M][r];
			for (int k=0;k<R;k++) if (!cat[C][k]) {
				for (int i=0;i<R;i++) if (!cat[C][i]) {
					for (int j=0;j<R;j++) if (!cat[C][j]) {
						mouse2[i][j]|=(mouse2[i][k]&mouse2[k][j]);
					}
				}
			}

			StringBuilder sb=new StringBuilder();
			if (tc>0) sb.append('\n');
			sb.append(meet?'Y':'N');
			sb.append(' ');
			sb.append(mouse2[M][M]?'Y':'N');
			System.out.println(sb.toString());
		}
	}

}
