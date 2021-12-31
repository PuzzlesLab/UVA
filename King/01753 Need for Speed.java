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
			double T=Double.parseDouble(st.nextToken());
			
			double [] dist=new double [N];
			double [] speed=new double [N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				dist[n]+=Integer.parseInt(st.nextToken());
				speed[n]=Integer.parseInt(st.nextToken());
			}
			
			double L=Double.MAX_VALUE;
			for (double d: speed) L=Math.min(d, L);
			L=-L;
			double R=10000000;
			
			double c=0;
			for (int i=0;i<60;i++) {
				c=(L+R)/2;
				double totalTime=0.0;
				for (int n=0;n<N;n++) totalTime+=(dist[n]/(speed[n]+c));
				
				if (totalTime<T) R=c;
				else L=c;
			}
			
			System.out.printf("%.9f\n", c);
		}
	}

}