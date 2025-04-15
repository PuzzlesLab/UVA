import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			double [] h=new double[M*N];
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				for (int n=0;n<N;n++) h[m*N+n]=Integer.parseInt(st.nextToken());
			}
			Arrays.sort(h);
			double water=Integer.parseInt(br.readLine());

			double count=0;
			double currH=0;
			if (water>0) {
				count=1;
				currH=h[0];
				for (int i=1;i<h.length && water>0;i++) {
					double fill=(h[i]-h[i-1])*100*count;
					if (water>=fill) {
						water-=fill;
						count++;
						currH=h[i];
					} else {
						currH=water/(count*100)+h[i-1];
						water=0;
					}
				}
				if (water>0) {
					currH=water/(count*100)+h[h.length-1];
					water=0;
				}
			} else count=0;
			
			StringBuilder sb=new StringBuilder();
			sb.append("Region ");
			sb.append(tc++);
			sb.append("\nWater level is ");
			sb.append(String.format("%.2f",currH));
			sb.append(" meters.\n");
			sb.append(String.format("%.2f", count*100.0/h.length));
			sb.append(" percent of the region is under water.\n");
			System.out.println(sb);
		}
	}

}
