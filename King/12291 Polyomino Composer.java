import java.util.ArrayList;
import java.util.Scanner;


class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		while (true) {
			int M=sc.nextInt();
			int N=sc.nextInt();
			if (M==0 && N==0) break;
			
			int [][] large=new int [M][M];
			for (int i=0;i<M;i++) {
				char [] c=sc.next().toCharArray();
				for (int i2=0;i2<Math.min(M, c.length);i2++) large[i][i2]=c[i2]=='*' ? 1 : 0;
				//Some lines in judge don't have M chars. :/
			}
			
			int [][] small=new int [N][N];
			for (int i=0;i<N;i++) {
				char [] c=sc.next().toCharArray();
				for (int i2=0;i2<Math.min(N, c.length);i2++) small[i][i2]=c[i2]=='*' ? 1 : 0;
				//Some lines in judge don't have N chars. :/
			}
			
			//Compact the small...
			int minCol=N, maxCol=0, minRow=N, maxRow=0;
			for (int i=0;i<N;i++) for (int i2=0;i2<N;i2++) if (small[i][i2]==1) {
				minRow=Math.min(i, minRow);
				maxRow=Math.max(i, maxRow);
				minCol=Math.min(i2, minCol);
				maxCol=Math.max(i2, maxCol);
			}
			int N1=maxRow-minRow+1;
			int N2=maxCol-minCol+1;
			int [][] compactSmall=new int [N1][N2];
			for (int i=0;i<compactSmall.length;i++) for (int i2=0;i2<compactSmall[i].length;i2++) compactSmall[i][i2]=small[minRow+i][minCol+i2];
			
			//Generate possible placements
			ArrayList<int [][]> initialPossibles=new ArrayList<>();
			for (int i=0;i<=M-N1;i++) for (int i2=0;i2<=M-N2;i2++) {
				int [][] test=new int [M][M];
				boolean quit=false;
				for (int j=0;j<N1 && !quit;j++) for (int j2=0;j2<N2 && !quit;j2++) {
					quit = (large[i+j][i2+j2]==0 && compactSmall[j][j2]==1);
					test[i+j][i2+j2]=compactSmall[j][j2];
				}
				if (!quit) initialPossibles.add(test);
			}
			
			//Add 2 possible placements and verify whether it is same with expected.
			boolean hasSolution=false;
			for (int ip=0;ip<initialPossibles.size() && !hasSolution;ip++) {
				int [][] initialState=initialPossibles.get(ip);

				for (int ip2=ip; ip2<initialPossibles.size() && !hasSolution; ip2++) {
					int [][] initialState2=initialPossibles.get(ip2);
					
					int [][] finalState=new int [M][M];
					for (int m=0;m<M;m++) for (int m2=0;m2<M;m2++) finalState[m][m2]=initialState[m][m2]+initialState2[m][m2];

					boolean same=true;
					for (int m=0;m<M && same;m++) for (int m2=0;m2<M && same;m2++) same=finalState[m][m2]==large[m][m2];
					if (same) hasSolution=true;
				}
			}
			
			if (hasSolution) System.out.println(1);
			else System.out.println(0);
		}
	}

}