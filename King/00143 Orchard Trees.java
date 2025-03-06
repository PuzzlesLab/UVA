import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static double cmp(double v1, double v2, double v3, boolean findMin) {
		return (findMin) ? Math.min(Math.min(v1, v2), v3) : Math.max(Math.max(v1, v2), v3);
	}

	private static double area(double x1, double y1, double x2, double y2, double x3, double y3) {
		return 0.5*Math.abs(x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2));
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			double x1=Double.parseDouble(st.nextToken());
			double y1=Double.parseDouble(st.nextToken());
			double x2=Double.parseDouble(st.nextToken());
			double y2=Double.parseDouble(st.nextToken());
			double x3=Double.parseDouble(st.nextToken());
			double y3=Double.parseDouble(st.nextToken());
			if (x1==0 && y1==0 && x2==0 && y2==0 && x3==0 && y3==0) break;

			double A=area(x1,y1,x2,y2,x3,y3);
			int minX=(int)Math.max(Math.ceil(cmp(x1,x2,x3,true)),1);
			int minY=(int)Math.max(Math.ceil(cmp(y1,y2,y3,true)), 1);
			int maxX=(int)Math.min(Math.floor(cmp(x1,x2,x3,false)),99);
			int maxY=(int)Math.min(Math.floor(cmp(y1,y2,y3,false)),99);

			int sol=0;
			for (int x=minX;x<=maxX;x++) for (int y=minY;y<=maxY;y++) {
				double A1=area(x1,y1,x2,y2,x,y);
				double A2=area(x1,y1,x3,y3,x,y);
				double A3=area(x2,y2,x3,y3,x,y);
				if (Math.abs(A1+A2+A3-A)<0.000001) sol++;
			}
			System.out.printf("%4d\n",sol);
		}
 	}

}
