import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			double [][] points=new double [N][];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				points[n]=new double [] {Double.parseDouble(st.nextToken()),Double.parseDouble(st.nextToken())};
			}
			double min=Double.MAX_VALUE;
			for (int n=0;n<N;n++) for (int n2=n+1;n2<N;n2++) {
				double dx=points[n][0]-points[n2][0];
				double dy=points[n][1]-points[n2][1];
				double dist=Math.sqrt(dx*dx+dy*dy);
				if (min>dist) min=dist;
			}
			
			if (min<10000) System.out.printf("%.4f\n", min);
			else System.out.println("INFINITY");
		}
	}

}
