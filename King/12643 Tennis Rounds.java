import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int I=Integer.parseInt(st.nextToken());
			int J=Integer.parseInt(st.nextToken());
			
			int i=Math.min(I,J);
			int j=Math.max(I,J);
			
			for (int n=0;n<=N;n++) {
				if (i==j) {
					System.out.println(n);
					break;
				} else {
					if (i%2==1) i++;
					i>>=1;
					if (j%2==1) j++;
					j>>=1;
				}
			}
		}
	}

}