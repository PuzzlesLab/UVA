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
			int M=Integer.parseInt(st.nextToken());
			
			int ans=0;
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				boolean flag=true;
				for (int m=0;m<M;m++) flag&=Integer.parseInt(st.nextToken()) > 0;
				if (flag) ans++;
			}
			
			System.out.println(ans);
		}
	}

}