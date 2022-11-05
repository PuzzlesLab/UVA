import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			boolean [][] same=new boolean [26][26];
			for (int i=0;i<same.length;i++) same[i][i]=true;
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				char c1=st.nextToken().charAt(0);
				char c2=st.nextToken().charAt(0);
				same[c1-'a'][c2-'a']=true;
			}

			for (int k=0;k<same.length;k++) for (int i=0;i<same.length;i++)  for (int j=0;j<same.length;j++) same[i][j]|=(same[i][k]&&same[k][j]);
			
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				char [] msg1=st.nextToken().toCharArray();
				char [] msg2=st.nextToken().toCharArray();
				if (msg1.length!=msg2.length) {
					System.out.println("no");
					continue;
				}
				boolean ok=true;
				for (int i=0;i<msg1.length && ok;i++) {
					char c1=msg1[i];
					char c2=msg2[i];
					ok&=same[c1-'a'][c2-'a'];
				}
				System.out.println(ok?"yes":"no");
			}
		}
	}

}
