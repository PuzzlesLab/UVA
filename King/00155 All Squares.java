import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int compute(int cx, int cy, int k, int tx, int ty) {
		if (k==0) return 0;
		
		int minx=cx-k;
		int maxx=cx+k;
		int miny=cy-k;
		int maxy=cy+k;
		int curr=(tx>=minx && tx<=maxx && ty>=miny && ty<=maxy)?1:0;
		curr+=compute(minx,miny,k/2,tx,ty);
		curr+=compute(minx,maxy,k/2,tx,ty);
		curr+=compute(maxx,miny,k/2,tx,ty);
		curr+=compute(maxx,maxy,k/2,tx,ty);
		return curr;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int K=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			if (K==0 && x==0 && y==0) break;
			System.out.printf("%3d\n",compute(1024,1024,K,x,y));
		}
 	}

}