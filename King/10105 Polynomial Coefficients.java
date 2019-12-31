import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] fac=new int[13];
		fac[0]=1;
		for (int i=1;i<fac.length;i++) fac[i]=fac[i-1]*i;
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			int ans=fac[N];
			for (int k=0;k<K;k++) ans/=fac[Integer.parseInt(st.nextToken())];
			System.out.println(ans);
		}
	}

}