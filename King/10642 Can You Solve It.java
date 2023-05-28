import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int calcStep(int x, int y) {
		int base=x+y;
		int s1=(base*(base+1))>>1;
		int s2=base-y;
		return s1+s2;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());
		for (int n=1;n<=N;n++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int sx=Integer.parseInt(st.nextToken());
			int sy=Integer.parseInt(st.nextToken());
			int dx=Integer.parseInt(st.nextToken());
			int dy=Integer.parseInt(st.nextToken());
			int sd=calcStep(sx,sy);
			int dd=calcStep(dx,dy);
			System.out.printf("Case %d: %d\n",n,dd-sd);
		}
	}

}
