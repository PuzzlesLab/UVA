import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCase=1;
		while ((s=br.readLine())!=null) {
			int rows=Integer.parseInt(s);
			if (rows==0) break;
			
			char [][] map=new char [rows][];
			boolean [][] inOut=new boolean [rows][];
			for (int r=0;r<rows;r++) {
				map[r]=br.readLine().toCharArray();
				inOut[r]=new boolean[map[r].length];
				inOut[r][r]=true;
				for (int c=r+1;c<map[r].length;c++) if (map[r][c]!=' ') inOut[r][c]=!inOut[r][c-1];
			}
			
			int maxSize=0;
			for (int r=0;r<map.length;r++) for (int c=0;c<map[r].length;c++) if (map[r][c]=='-') {
				maxSize=Math.max(maxSize,1);
				
				//Slanting in.
				int row0MinCol=c;
				int row0MaxCol=c;
				int testExpand=0;
				while (r+testExpand+1<map.length && (row0MinCol>=1) && (row0MaxCol+1<map[r].length)) {
					testExpand++;
					row0MinCol--;
					row0MaxCol++;
										
					int tempMinCol=row0MinCol, tempMaxCol=row0MaxCol;
					boolean flag=map[r+testExpand][c]=='-';
					for (int testRow=r;testRow<=r+testExpand && flag;testRow++) {
						flag &=map[testRow][tempMinCol++]=='-';
						flag &=map[testRow][tempMaxCol--]=='-';
					}
					
					if (flag) {
						if (inOut[r][row0MinCol] && inOut[r][row0MaxCol]) {
							int size=testExpand+1;
							maxSize=Math.max(maxSize,size*size);
						} else continue; //Outside triangle is slanting out, attempt to get another slanting in triangle.
					}
					else break;
				}
				
				//Slating out
				int rowNMinCol=c;
				int rowNMaxCol=c;
				testExpand=0;
				while (r+testExpand+1<map.length && (rowNMinCol>=1) && (rowNMaxCol+1<map[r+testExpand+1].length)) {
					testExpand++;
					rowNMinCol--;
					rowNMaxCol++;
					
					boolean flag=map[r+testExpand][c]=='-';
					for (int testCol=rowNMinCol;testCol<=rowNMaxCol && flag;testCol++) {
						flag&=map[r+testExpand][testCol]=='-';
					}
					
					if (flag) {
						if (!inOut[r+testExpand][rowNMinCol] && !inOut[r+testExpand][rowNMaxCol]) {
							int size=testExpand+1;
							maxSize=Math.max(maxSize,size*size);
						} else continue; //Outside triangle is slanting in, attempt to get another slanting out triangle.
					}
					else break;
				} 
			}

			System.out.printf("Triangle #%d\nThe largest triangle area is %d.\n\n", testCase++,maxSize);
		}
	}
}