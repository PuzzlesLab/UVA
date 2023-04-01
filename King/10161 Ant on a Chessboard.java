import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			/* Element count per level = 1,3,5,7...
				Total = N/2[(2+(N-1)(2)]
				= (N/2)(2+2N-2)
				= N*N
				= N^2
			*/
			long T=Integer.parseInt(s);
			int layerNo=(int)Math.ceil(Math.sqrt(T));
			long layerMin=(layerNo-1)*(layerNo-1);
			long layerMax=layerNo*layerNo;
			boolean rowFirst=layerNo%2==0;
			long layerMid=(layerMin+1+layerMax)/2;

			long x=-1, y=-1;
			if (rowFirst) {
				if (T<=layerMid) {
					x=T-layerMin;
					y=layerNo;
				} else {
					x=layerNo;
					y=layerNo-(T-layerMid);
				}
			} else {
				if (T<=layerMid) {
					x=layerNo;
					y=layerNo-(layerMid-T);
				} else {
					x=layerNo-(T-layerMid);
					y=layerNo;
				}
			}
			System.out.printf("%d %d\n",x,y);
		}
	}
}
