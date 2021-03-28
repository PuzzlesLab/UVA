import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int antsCount=Integer.parseInt(s);
			int maxH=0;
			int maxHV=0;
			for (int i=0;i<antsCount;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int L=Integer.parseInt(st.nextToken());
				int W=Integer.parseInt(st.nextToken());
				int H=Integer.parseInt(st.nextToken());
				//g = constant, H larger => g/2H smaller => g-g/2H larger, find max H is sufficient.
				if (H>=maxH) {
					if (H>maxH) maxHV=L*W*H;
					else maxHV=Math.max(maxHV, L*W*H);
					maxH=Math.max(maxH, H);
				}
			}
			System.out.println(maxHV);
		}
	}
}