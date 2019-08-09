import java.util.Scanner;
import java.util.Stack;

class Main {
	
	private static int [][] delta= {{0,-1},{0,1},{-1,0},{1,0}};
	
	public static void floodFill(char [][] map, int [][] group, int x, int y, int id) {
                //Not using recursion here due to possibility of running into stackoverflow! (x<=64, y<=64, 64*64=4096!!)
		Stack<Integer> stkx=new Stack<>();
		Stack<Integer> stky=new Stack<>();
		stkx.add(x);
		stky.add(y);
		
		while (!stkx.isEmpty()) {
			int cx=stkx.pop(), cy=stky.pop();
			group[cx][cy]=id;
			for (int [] d : delta) {
				int nx=cx+d[0], ny=cy+d[1];
				if (nx>=0 && nx<group.length && ny>=0 && ny<group[nx].length && group[nx][ny]==0 && map[cx][cy]==map[nx][ny]) {
					group[nx][ny]=id;
					stkx.push(nx);
					stky.push(ny);
				}
			}
		}
		
	}
	
	public static void main (String [] args) throws Exception {
		//There are chance for input data line to be wider than the given width!!
		Scanner sc=new Scanner(System.in);
		int testCaseCount=sc.nextInt();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int x=sc.nextInt();
			int y=sc.nextInt();
			char [][] map=new char [x][y];
			for (int i=0;i<x;i++) map[i]=sc.next().toCharArray();
			
			int [][] group=new int [x][y];
			int maxGroupId=1;
			for (int i=0;i<x;i++) for (int i2=0;i2<y;i2++) if (map[i][i2]!='.' && group[i][i2]==0) {
				floodFill(map,group,i,i2,maxGroupId);
				maxGroupId++;
			}
			
			int [] fishCount=new int [8];
			for (int id=1;id<maxGroupId;id++) {
				int minx=x, miny=y, maxx=0, maxy=0, realSize=0;
				for (int i=0;i<x;i++) for (int i2=0;i2<y;i2++) if (group[i][i2]==id) {
					minx=Math.min(minx, i);
					miny=Math.min(miny, i2);
					maxx=Math.max(maxx, i);
					maxy=Math.max(maxy, i2);
					realSize++;
				}
				int xDiff=(maxx-minx+1), yDiff=(maxy-miny+1), rectSize=xDiff*yDiff, minDiff=Math.min(xDiff, yDiff), maxDiff=Math.max(xDiff, yDiff);
				if (rectSize==realSize) {
					if (realSize==1) /*sardines*/ fishCount[0]++;
					else if (realSize==2) /*mackerels*/ fishCount[1]++;
					else if (minDiff==1) /*salmons*/ fishCount[2]++;
					else if (minDiff==2 && maxDiff>2) /*groupers*/ fishCount[3]++;
					else if (xDiff==yDiff) /*turtles*/ fishCount[4]++;
					else if (minDiff==3 && maxDiff>3) /*dolphins*/ fishCount[5]++;
					else if (minDiff==4 && maxDiff>4) /*whales*/ fishCount[6]++;
				} else fishCount[7]++; /*shark*/
			}
			
			if (testCase>0) System.out.println();
			StringBuilder sb=new StringBuilder();
			for (int count : fishCount) {
				sb.append(count);
				sb.append(' ');
			}
			sb.setLength(sb.length()-1);
			System.out.println(sb.toString());
		}
	}

}
