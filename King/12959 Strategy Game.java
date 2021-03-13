import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int J=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			
			int [] points=new int [J];
			st=new StringTokenizer(br.readLine());
			int currIdx=0;
			while (st.hasMoreTokens()) {
				points[currIdx]+=Integer.parseInt(st.nextToken());
				currIdx=(currIdx+1)%J;
			}
			
			int max=0;
			for (int j=0;j<J;j++) max=Math.max(max, points[j]);
			for (int j=J-1;j>=0;j--) if (points[j]==max) {
				System.out.println(j+1);
				break;
			}
		}
	}
}