import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int f=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			
			st=new StringTokenizer(br.readLine());
			double [] fi=new double [f];
			for (int i=0;i<f;i++) fi[i]=Double.parseDouble(st.nextToken());
			Arrays.sort(fi);
			
			st=new StringTokenizer(br.readLine());
			double [] ri=new double [r];
			for (int i=0;i<r;i++) ri[i]=Double.parseDouble(st.nextToken());
			Arrays.sort(ri);
			
			ArrayList<Double> dr=new ArrayList<>();
			for (int i=0;i<f;i++) for (int i2=0;i2<r;i2++) dr.add(ri[i2]/fi[i]);
			Collections.sort(dr);
			
			double maxSpread=Double.MIN_VALUE;
			for (int i=1;i<dr.size();i++) maxSpread=Math.max(maxSpread, dr.get(i)/dr.get(i-1));
			
			System.out.printf("%.2f\n", maxSpread);
		}
	}

}
