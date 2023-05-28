import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final double HEIGHT=Math.sqrt(3)/2;

	private static class Tuple {
		double x, y;
		
		public Tuple(double x, double y) {
			this.x=x;
			this.y=y;
		}
	}
	
	private static Tuple getPosition(long N) {
		long max=(long)Math.sqrt(N);
		if (max*max<N) max++;
		long min=(max-1)*(max-1);

		N-=min;
		double x=(N-max)*0.5;
		double y=max-1;
		if ((N&1)>0) y+=2.0/3;
		else y+=1.0/3;
		y*=HEIGHT;

		return new Tuple(x,y);
	}

	private static double calcDist(Tuple p1, Tuple p2) {
		double dx=p1.x-p2.x;
		double dy=p1.y-p2.y;
		return Math.sqrt(dx*dx+dy*dy);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			long N=Long.parseLong(st.nextToken())+1;
			long M=Long.parseLong(st.nextToken())+1;
			
			Tuple pN=getPosition(N);
			Tuple pM=getPosition(M);
			System.out.printf("%.3f\n",calcDist(pN,pM));
		}
	}

}