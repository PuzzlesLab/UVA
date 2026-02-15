import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static double [] Radius;
	private static double Ans;

	private static void combi(double [] values, int curr, boolean [] visited) {
		if (curr==values.length) {
			double [] pos=new double[values.length];
			pos[0]=values[0];
			double currDist=2*values[0];
			for (int i=1;i<values.length;i++) {
				pos[i]=values[i];
				for (int i2=0;i2<i;i2++) pos[i]=Math.max(pos[i],pos[i2]+2*Math.sqrt(values[i]*values[i2]));
				currDist=Math.max(currDist,pos[i]+values[i]);
			}
			Ans=Math.min(Ans,currDist);
			return;
		}
		
		for (int i=0;i<Radius.length;i++) if (!visited[i]) {
			visited[i]=true;
			values[curr]=Radius[i];
			combi(values,curr+1,visited);
			visited[i]=false;
		}
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=0;n<N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			Radius=new double[M];
			for (int m=0;m<M;m++) Radius[m]=Double.parseDouble(st.nextToken());
			
			Ans=Integer.MAX_VALUE;
			combi(new double[M],0,new boolean[M]);
			System.out.printf("%.3f\n",Ans);
		}
	}

}
