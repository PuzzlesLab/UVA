import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			int [][] ints=new int [9][9];
			for (int i=0;i<5;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int i2=0;i2<=i;i2++) ints[i*2][i2*2]=Integer.parseInt(st.nextToken());
			}
			
			//Fill odd rows.
			for (int i=0;i<4;i++) {
				for (int i2=0;i2<=i && i2<9;i2++) {
					int x=i*2, y=i2*2;
					ints[x+2][y+1]=(ints[x][y]-ints[x+2][y]-ints[x+2][y+2])/2;
				}
			}

			//Fill even rows.
			for (int i=1;i<8;i+=2) for (int i2=0;i2<=i;i2++) ints[i][i2]=ints[i+1][i2]+ints[i+1][i2+1];
			
			StringBuilder sb=new StringBuilder();
			for (int i=0;i<ints.length;i++) {
				for (int i2=0;i2<=i;i2++) {
					sb.append(ints[i][i2]);
					sb.append(" ");
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
