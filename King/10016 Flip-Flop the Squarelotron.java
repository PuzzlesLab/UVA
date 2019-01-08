import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void upsideDown(int [][] sq, int r) {
		for (int row=r;row<sq.length/2;row++) for (int col=r;col<sq.length-r;col++) {
			int opRow=sq.length-1-row;
			int tempData=sq[row][col];
			sq[row][col]=sq[opRow][col];
			sq[opRow][col]=tempData;
		}
	}
	
	public static void leftRight(int [][] sq, int r) {
		for (int row=r;row<sq.length-r;row++) for (int col=r;col<sq.length/2;col++) {
			int opCol=sq.length-1-col;
			int tempData=sq[row][col];
			sq[row][col]=sq[row][opCol];
			sq[row][opCol]=tempData;
		}
	}
	
	public static void diag(int [][] sq, int r) {
		int [][] temp=new int[sq.length-2*r][sq.length-2*r];
		
		for (int row=r;row<sq.length-r;row++) for (int col=r;col<sq.length-r;col++) temp[row-r][col-r]=sq[col][row];
		for (int row=r;row<sq.length-r;row++) for (int col=r;col<sq.length-r;col++) sq[row][col]=temp[row-r][col-r];
	}
	
	public static void idiag(int [][] sq, int r) {
		int [][] temp=new int[sq.length-2*r][sq.length-2*r];
		
		for (int row=r;row<sq.length-r;row++) for (int col=r;col<sq.length-r;col++) temp[row-r][col-r]=sq[sq.length-1-col][sq.length-1-row];
		for (int row=r;row<sq.length-r;row++) for (int col=r;col<sq.length-r;col++) sq[row][col]=temp[row-r][col-r];
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int [][] sq=new int [N][N];
			for (int n=0;n<N;n++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for (int n2=0;n2<N;n2++) sq[n][n2]=Integer.parseInt(st.nextToken());
			}
			int R=(N+1)/2;
			for (int r=0;r<R;r++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				int S=Integer.parseInt(st.nextToken());
				for (int s=0;s<S;s++) {
					switch (Integer.parseInt(st.nextToken())) {
						case 1 :upsideDown(sq, r);
								if (r+1<R) upsideDown(sq, r+1);
								break;
						case 2 :leftRight(sq, r);
								if (r+1<R) leftRight(sq, r+1);
								break;
						case 3 :diag(sq, r);
								if (r+1<R) diag(sq, r+1);
								break;
						case 4 :idiag(sq, r);
								if (r+1<R) idiag(sq, r+1);
								break;
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			for (int n=0;n<N;n++) {
				for (int n2=0;n2<N;n2++) {
					sb.append(sq[n][n2]);
					sb.append(' ');
				}
				sb.setLength(sb.length()-1);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}