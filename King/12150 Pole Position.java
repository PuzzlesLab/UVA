import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [] loc=new int [N];
			Arrays.fill(loc, -1);
			boolean collision=false;
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int carId=Integer.parseInt(st.nextToken());
				int delta=Integer.parseInt(st.nextToken());
				
				int lastLoc=n+delta;
				if (!collision) {
					collision=lastLoc<0 || lastLoc>=N || loc[lastLoc]!=-1;
					if (!collision) loc[lastLoc]=carId;
				}
			}
			if (collision) System.out.println(-1);
			else {
				StringBuilder sb=new StringBuilder();
				for (int i=0;i<loc.length;i++) {
					sb.append(loc[i]);
					sb.append(" ");
				}
				sb.setLength(sb.length()-1);
				System.out.println(sb.toString());
			}
		}
	}

}