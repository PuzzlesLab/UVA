import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int T=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			int H=Integer.parseInt(st.nextToken());
			
			int [][] prices= {new int [T], new int[R], new int [H]};
			boolean [][][] compatible= {new boolean [T][R],new boolean [R][H],new boolean [H][T]};
			for (int i=0;i<T;i++) {
				st=new StringTokenizer(br.readLine());
				prices[0][i]=Integer.parseInt(st.nextToken());
				for (int i2=0;i2<R;i2++) compatible[0][i][i2]=st.nextToken().charAt(0)=='0';
			}
			
			for (int i=0;i<R;i++) {
				st=new StringTokenizer(br.readLine());
				prices[1][i]=Integer.parseInt(st.nextToken());
				for (int i2=0;i2<H;i2++) compatible[1][i][i2]=st.nextToken().charAt(0)=='0';
			}
			
			for (int i=0;i<H;i++) {
				st=new StringTokenizer(br.readLine());
				prices[2][i]=Integer.parseInt(st.nextToken());
				for (int i2=0;i2<T;i2++) compatible[2][i][i2]=st.nextToken().charAt(0)=='0';
			}
			
			int [] solution=null;
			int cheapest=Integer.MAX_VALUE;
			for (int t=0;t<T;t++) for (int r=0;r<R;r++) if (compatible[0][t][r]) for (int h=0;h<H;h++) if (compatible[1][r][h] && compatible[2][h][t]) {
				int cost=prices[0][t]+prices[1][r]+prices[2][h];
				if (cost<cheapest) {
					cheapest=cost;
					solution=new int [] {t,r,h};
				}
			}
			
			if (solution==null) System.out.println("Don't get married!");
			else {
				StringBuilder sb=new StringBuilder();
				for (int id : solution) {
					sb.append(id);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append(':');
				sb.append(cheapest);
				System.out.println(sb.toString());
			}
		}
	}

}