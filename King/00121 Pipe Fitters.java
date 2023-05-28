import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int countSkew(double x, double y) {
		// Explanation : https://i.imgur.com/7zs4dvX.jpg
		if (x<1 || y<1) return 0;
		
		double h=Math.sqrt(3)*0.5;
		int hCount=(int)((x-1)/h)+1;
		int wCount=(int)y;
		int evenLayers=hCount/2;
		int oddLayers=hCount-hCount/2;
		return oddLayers*wCount+evenLayers*((int)(y-0.5));
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			double x=Double.parseDouble(st.nextToken());
			double y=Double.parseDouble(st.nextToken());
			
			int grid=(int)x*(int)y;
			int skew1=countSkew(x,y);
			int skew2=countSkew(y,x);
			int skew=Math.max(skew1, skew2);
			if (grid>=skew) System.out.printf("%d grid\n",grid);
			else System.out.printf("%d skew\n",skew);
		}
	}

}
