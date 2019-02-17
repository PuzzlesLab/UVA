import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static int floodFill(char [][] c, int x, int y) {
		int sum=0;
		for (int i=-1;i<=1;i++) for (int i2=-1;i2<=1;i2++) {
			int currX=x+i, currY=y+i2;
			if (currX>=0 && currX<c.length && currY>=0 && currY<c[currX].length && c[currX][currY]=='*') {
				c[currX][currY]='.';
				sum+=1+floodFill(c,currX,currY);
			}
		}
		return sum;
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			char [][] c=new char [Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
			for (int i=0;i<c.length;i++) c[i]=br.readLine().toCharArray();
			
			int count=0;
			for (int i=0;i<c.length;i++) for (int i2=0;i2<c[i].length;i2++) if (c[i][i2]=='*') {
				int size=floodFill(c,i,i2);
				if (size==1) count++;
			}
			System.out.println(count);
		}
	}

}