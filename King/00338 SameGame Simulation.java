package uva;

import java.util.ArrayList;
import java.util.Scanner;

class Main {
	
	private static int [][] Deltas= {{0,-1},{-1,0},{0,1},{1,0}};
	
	private static int removeNum (int [][] num, int x, int y, int n) {
		int count=1;
		for (int [] delta : Deltas) {
			int dx=x+delta[0], dy=y+delta[1];
			if (dx>=0 && dx<num.length && dy>=0 && dy<num[0].length && num[dx][dy]==n) {
				num[dx][dy]=-1;
				count+=removeNum(num,dx,dy,n);
			}
		}
		return count;
	}
	
	private static void moveDown(int [][] num) {
		for (int y=0;y<num[0].length;y++) for (int x=num.length-1;x>=0;x--) {
			if (num[x][y]==-1) {
				int swapx=-1;
				for (int tx=x-1;tx>=0;tx--) if (num[tx][y]!=-1) {
					swapx=tx;
					break;
				}
				if (swapx==-1) break;
				else {
					int temp=num[x][y];
					num[x][y]=num[swapx][y];
					num[swapx][y]=temp;
				}
			}
		}
	}
	
	private static void moveLeft(int [][] num) {
		for (int y=0;y<num[0].length;y++) {
			boolean clear=true;
			for (int x=0;x<num.length;x++) clear &= num[x][y]==-1;
			if (clear) {
				boolean containsNumber=false;
				for (int i=0;i<num.length;i++) for (int i2=y;i2<num[0].length-1;i2++) {
					int temp=num[i][i2];
					num[i][i2]=num[i][i2+1];
					num[i][i2+1]=temp;
					containsNumber|=num[i][i2]>=0 || num[i][i2+1]>=0;
				}
				if (!containsNumber) y--;
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		int testCase=1;
		while (sc.hasNextInt()) {
			int X=sc.nextInt(), Y=sc.nextInt();
			if (X==0 && Y==0) break;
			
			int [][] num=new int [X][Y];
			for (int x=0;x<X;x++) for (int y=0;y<Y;y++) num[X-x-1][y]=sc.nextInt();
			
			ArrayList<int []> selections=new ArrayList<>();
			while (true) {
				int sx=sc.nextInt(), sy=sc.nextInt();
				if (sx==0 && sy==0) break;
				selections.add(new int [] {X-sx,sy-1});
			}
			
			int remaining=X*Y;
			for (int [] selection : selections) {
				int x=selection[0], y=selection[1];
			 	if (x>=0 && x<X && y>=0 && y<Y && num[selection[0]][selection[1]]>=0) {
					boolean canRemove=false;
					for (int [] delta : Deltas) {
						int dx=x+delta[0], dy=y+delta[1];
						if (dx>=0 && dx<X && dy>=0 && dy<Y) canRemove|=num[x][y]==num[dx][dy];
					}
					if (canRemove) {
						remaining-=removeNum(num,x,y,num[x][y]);
						num[x][y]=-1;
						remaining+=1;
						if (remaining>0) {
							moveDown(num);
							moveLeft(num);
						}
					}
			 	}
			}
			
			if (testCase>1) System.out.println();
			StringBuilder sb=new StringBuilder();
			sb.append("Grid ");
			sb.append(testCase);
			sb.append(".\n");
			if (remaining==0) sb.append("    Game Won\n");
			else {
				for (int x=0;x<X;x++) {
					sb.append("    ");
					for (int y=0;y<Y;y++) {
						sb.append((num[x][y]>=0) ? String.valueOf(num[x][y]) : " ");
						sb.append(' ');
					}
					sb.setCharAt(sb.length()-1, '\n');
				}
			}
			System.out.print(sb.toString());
			testCase++;
		}
		
	}

}
