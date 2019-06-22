import java.util.Scanner;

class Main {
	
	private static class Data {
		int x, y;
		public Data (int x, int y) { this.x=x;this.y=y; }
	}
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int [][] num=new int [5][5];
			Data [] data=new Data[76];
			for (int i=0;i<5;i++) for (int i2=0;i2<5;i2++) if (!(i==2 && i2==2)) {
				num[i][i2]=sc.nextInt();
				data[num[i][i2]]=new Data(i,i2);
			}
			
			int [] list=new int [75];
			for (int i=0;i<75;i++) list[i]=sc.nextInt();
				
				
			boolean [][] filled=new boolean [5][5];
			filled[2][2]=true;
			int count=0;
			for (int i=0;i<75;i++) {
				int curr=list[i];
				count++;
				if (data[curr]==null) continue;

				filled[data[curr].x][data[curr].y]=true;
				boolean flag=false;
				for (int x=0;x<5 && !flag;x++) {
					boolean found=true;
					for (int y=0;y<5;y++) found&=filled[x][y];
					flag |= found;
				}
				
				for (int x=0;x<5 && !flag;x++) {
					boolean found=true;
					for (int y=0;y<5;y++) found&=filled[y][x];
					flag |= found;
				}
				
				flag |= (filled[0][0] && filled[1][1] && filled[2][2] && filled[3][3] && filled[4][4]);
				flag |= (filled[0][4] && filled[1][3] && filled[2][2] && filled[3][1] && filled[4][0]);
				
				if (flag) {
					System.out.printf("BINGO after %d numbers announced\n", count);
					break;
				}
			}
			
		}
	}

}
