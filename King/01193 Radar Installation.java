import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int D=Integer.parseInt(st.nextToken());
			int DSq=D*D;
			
			boolean inD=true;
			double [][] islands=new double [N][];
			for (int n=0;n<N;n++) {
				st=new StringTokenizer(br.readLine());
				double x=Integer.parseInt(st.nextToken());
				double y=Integer.parseInt(st.nextToken());
				double r=Math.sqrt(DSq-y*y);
				islands[n]=new double [] {x-r, x+r};
				inD=inD && y<=D;
			}
			
			Arrays.sort(islands, (a,b)-> {
				if (Double.compare(a[0], b[0])==0) return Double.compare(a[1], b[1]);
				return Double.compare(a[0], b[0]);
			});
			
			//Tried to put the circle as right as possible by coordinate and seems to have different solution. :/
			int ans=-1;
			if (inD) {
				double cx=-10000.0;
				int n=0;
				while (n<N) {
					while (n<N && islands[n][0]<=cx) {
						cx=Math.min(cx, islands[n][1]);
						n++;
					}
					if (n<N) cx=islands[n][1];
					ans++;
				}
			}

			System.out.printf("Case %d: %d\n", testCase++, ans);
			br.readLine();
		}
	}

}