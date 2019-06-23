import java.util.ArrayList;
import java.util.Scanner;

class Main {
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		System.out.println("HI Q OUTPUT");
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			ArrayList<Integer> data=new ArrayList<>();
			while (true) {
				int input=sc.nextInt();
				if (input==0) break;
				else data.add(input);
			}
			
			int [][] fillIndex=new int [7][7];
			int fillCount=1;
			int [][] fillIndexToIndex=new int [34][0];
			for (int i=0;i<7;i++) {
				int maxI2=(i<2 || i>=5) ? 5 : 7;
				for (int i2=(i<2 || i>=5)? 2 : 0; i2<maxI2;i2++) {
					fillIndexToIndex[fillCount]=new int [] {i, i2};
					fillIndex[i][i2]=fillCount++;
				}
			}
			
			boolean [][] map=new boolean [7][7];
			for (int fill : data) map[fillIndexToIndex[fill][0]][fillIndexToIndex[fill][1]]=true;
			int [][] deltas= {{0,1},{1,0},{-1,0},{0,-1}};
			while (true) {
				boolean hasChange=false;
				for (int i=33;i>0;i--) {
					int x=fillIndexToIndex[i][0];
					int y=fillIndexToIndex[i][1];
					if (!map[x][y]) {
						int maxIndex=0, x1=0, x2=0, y1=0, y2=0;
						for (int [] delta : deltas) {
							if (x+delta[0]>=0 && x+delta[0]<7 && y+delta[1]>=0 && y+delta[1]<7 && fillIndex[x+delta[0]][y+delta[1]]!=0 && map[x+delta[0]][y+delta[1]] && 
								x+2*delta[0]>=0 && x+2*delta[0]<7 && y+2*delta[1]>=0 && y+2*delta[1]<7 && fillIndex[x+2*delta[0]][y+2*delta[1]]!=0 && map[x+2*delta[0]][y+2*delta[1]]) {
								if (fillIndex[x+2*delta[0]][y+2*delta[1]]>maxIndex) {
									maxIndex=fillIndex[x+2*delta[0]][y+2*delta[1]];
									x1=x+delta[0]; x2=x+2*delta[0]; y1=y+delta[1]; y2=y+2*delta[1];
								}
							}
						}
						
						if (maxIndex!=0) {
							map[x1][y1]=false;
							map[x2][y2]=false;
							map[x][y]=true;
							hasChange=true;
						}
					}
					if (hasChange) break;
				}
				if (!hasChange) break;
			}
			
			int count=0;
			for (int x=0;x<7;x++) for (int y=0;y<7;y++) if (map[x][y]) count+=fillIndex[x][y];
			System.out.println(count);
		}
		System.out.println("END OF OUTPUT");
	}

}
