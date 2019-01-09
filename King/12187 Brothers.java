import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			
			int [][] data=new int [R][C];
			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				for (int c=0;c<C;c++) data[r][c]=Integer.parseInt(st.nextToken());
			}
			
			for (int k=0;k<K;k++) {
				int [][] newData=new int [R][C];
				for (int r=0;r<R;r++) {
					for (int c=0;c<C;c++) {
						int enemy=Math.floorMod(data[r][c]-1, N);
						
						if ((r>0 && data[r-1][c]==enemy) ||
							(r<R-1 && data[r+1][c]==enemy)||
							(c>0 && data[r][c-1]==enemy)||
							(c<C-1 && data[r][c+1]==enemy))
							newData[r][c]=enemy;
						else newData[r][c]=data[r][c];
					}
				}
				data=newData;
			}
			
			StringBuilder sb=new StringBuilder();
			for (int r=0;r<R;r++) {
				for (int c=0;c<C;c++) {
					sb.append(data[r][c]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}
