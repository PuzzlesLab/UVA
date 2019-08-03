import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int row=sc.nextInt();
			int col=sc.nextInt();
			int S=sc.nextInt();;
			char [][] puzzle=new char[row][];
			for (int i=0;i<row;i++) puzzle[i]=sc.next().toCharArray();
			
			for (int s=0;s<S;s++) {
				int sx=sc.nextInt();
				int sy=sc.nextInt();
				char [][] piece=new char[sx][sy];
				for (int i=0;i<sx;i++) piece[i]=sc.next().toCharArray();
				
				//Remove redundant cell of puzzle piece.
				int sxa=sx, sya=sy;
				int sxb=-1, syb=-1;
				for (int i=0;i<sx;i++) for (int i2=0;i2<sy;i2++) if (piece[i][i2]=='*') {
					sxa=Math.min(sxa, i);
					sya=Math.min(sya, i2);
					sxb=Math.max(sxb, i);
					syb=Math.max(syb, i2);
				}
				
				int xSize=sxb-sxa;
				int ySize=syb-sya;
				
				boolean flag=false;
				if (sxb >= 0 && syb >= 0) {
					for (int startx=0;startx<row-xSize && !flag;startx++) for (int starty=0;starty<col-ySize && !flag;starty++) {
						boolean same=true;
						for (int testx=0;testx<=xSize && same;testx++) for (int testy=0;testy<=ySize && same;testy++) {
							same&=puzzle[startx+testx][starty+testy]=='*' || piece[sxa+testx][sya+testy]==puzzle[startx+testx][starty+testy];
						}
						flag=same;
					}
				} else flag=true;

				
				System.out.println(flag? "Yes" : "No");
			}
			
			System.out.println();
		}
	}

}