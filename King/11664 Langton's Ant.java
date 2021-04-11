import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			BigInteger C=new BigInteger(st.nextToken());
			// Question stated : x = left to right, y = bottom to top. Check (0,N-1) reachability.
			int Y=Integer.parseInt(st.nextToken())-1;
			int X=N-Integer.parseInt(st.nextToken());
			
			char [][] map=new char [N][N];
			for (int x=0;x<N;x++) for (int y=0;y<N;y++) {
				int bitPos=(N*N-1)-(x*N+y);
				map[N-1-x][y]=C.testBit(bitPos) ? 'r' : 'b';
			}

			int [][] facingDelta= {{-1,0},{0,-1},{1,0},{0,1}};
			//Facing => 0=north, 1=west, 2=south, 3=east
			int currX=X, currY=Y, currFacing=0;
			boolean die=false;
			while (!die) {
				if (currX==0 && currY==N-1) break;
				if (map[currX][currY]=='b') {
					map[currX][currY]='r';
					currFacing=(currFacing+1)%4;
				} else if (map[currX][currY]=='r') {
					map[currX][currY]='b';
					currFacing=Math.floorMod(currFacing-1,4);
				}
				currX+=facingDelta[currFacing][0];
				currY+=facingDelta[currFacing][1];
				die=currX<0 || currX>=N || currY<0 || currY>=N;
			}
			
			System.out.println((!die) ? "Yes" : "Kaputt!"); 
		}
	}
}