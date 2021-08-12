import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static boolean check(double [] starts, double w, double max) {
		double currStart=0.0;
		for (int i=0;i<starts.length;i++) {
			if (starts[i]>currStart) return false;
			currStart=starts[i]+w;
		}
		return currStart>=max;
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0.0")) {
			StringTokenizer st=new StringTokenizer(s);
			int nx=Integer.parseInt(st.nextToken());
			int ny=Integer.parseInt(st.nextToken());
			double w=Double.parseDouble(st.nextToken());
			
			double [] e2e=new double[nx];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<nx;i++) e2e[i]=Double.parseDouble(st.nextToken())-w/2;

			double[] s2s=new double[ny];
			st=new StringTokenizer(br.readLine());
			for (int i=0;i<ny;i++) s2s[i]=Double.parseDouble(st.nextToken())-w/2;
			
			boolean flag=true;
			Arrays.sort(e2e);
			flag=check(e2e,w,75.0);
			if (flag) {
				Arrays.sort(s2s);
				flag=check(s2s,w,100.0);
			}

			System.out.println(flag?"YES":"NO");
		}
	}
}