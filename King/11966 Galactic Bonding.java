import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] Parent;

	private static int getParent(int n) {
		if (Parent[n]==n) return Parent[n];
		return Parent[n]=getParent(Parent[n]);
	}
	
	private static double distSq(double x1, double y1, double x2, double y2) {
		double dx=x2-x1;
		double dy=y2-y1;
		return dx*dx+dy*dy;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=1;t<=T;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			double D=Double.parseDouble(st.nextToken());
			D*=D;
			
			double [] x=new double[N];
			double [] y=new double[N];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				x[n]=Double.parseDouble(st.nextToken());
				y[n]=Double.parseDouble(st.nextToken());
			}
			
			Parent=new int [N];
			for (int n=0;n<N;n++) Parent[n]=n;

			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) {
				int p1=getParent(n);
				int p2=getParent(n2);
				if (distSq(x[n],y[n],x[n2],y[n2])<=D) Parent[p2]=p1;
			}
			
			boolean [] flag=new boolean [N];
			int count=0;
			for (int n=0;n<N;n++) {
				int p=getParent(n);
				if (!flag[p]) {
					flag[p]=true;
					count++;
				}
			}
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(t);
			sb.append(": ");
			sb.append(count);
			System.out.println(sb);
		}
	}

}