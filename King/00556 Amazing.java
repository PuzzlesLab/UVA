import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [][] delta = {{0,1},{-1,0},{0,-1},{1,0}};
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int row=Integer.parseInt(st.nextToken());
			int col=Integer.parseInt(st.nextToken());
			boolean [][] maze=new boolean [row][col];
			for (int i=0;i<row;i++) {
				char [] c=br.readLine().toCharArray();
				for (int i2=0;i2<c.length;i2++) maze[i][i2]=c[i2]=='0';
			}
			
			int [][] mapStepCount=new int [row][col];
			final int sX=row-1, sY=0;
			int currX=row-1, currY=0;
			int direction=0;
			do {
				int rightX=currX+delta[(direction+3)%delta.length][0];
				int rightY=currY+delta[(direction+3)%delta.length][1];
				
				if (rightX>=0 && rightX<row && rightY>=0 && rightY<col && maze[rightX][rightY]) { //If the robot can go right...
					currX=rightX;
					currY=rightY;
					direction=(direction+3)%delta.length;
					mapStepCount[currX][currY]++;
					continue;
				}
				
				int nextX=currX+delta[direction][0];
				int nextY=currY+delta[direction][1];
				
				if (nextX>=0 && nextX<row && nextY>=0 && nextY<col && maze[nextX][nextY]) { //If the robot can continue on it's direction.
					currX=nextX;
					currY=nextY;
					mapStepCount[currX][currY]++;
				} else direction=(direction+1)%delta.length;  //Not possible, turn left.
			} while (!(currX==sX && currY==sY));
			
			
			int [] count=new int [5];
			for (int i=0;i<row;i++) for (int i2=0;i2<col;i2++) if (mapStepCount[i][i2]<5 && maze[i][i2]) count[mapStepCount[i][i2]]++;
			System.out.printf("%3d%3d%3d%3d%3d\n",count[0],count[1],count[2],count[3],count[4]);
		}
	}

}
