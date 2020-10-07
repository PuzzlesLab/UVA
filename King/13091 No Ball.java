import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			char [][] data=new char [5][5];
			for (int i=0;i<data.length;i++) data[i]=br.readLine().toCharArray();
			boolean moreThanExist=false;
			boolean lessThanExist=false;
			int signX=-1, signY=-1;
			for (int i=0;i<data.length;i++) for (int i2=0;i2<data[i].length;i2++) {
				if (data[i][i2]=='>') {
					moreThanExist=true;
					signX=i;
					signY=i2;
				} else if (data[i][i2]=='<') {
					lessThanExist=true;
					signX=i;
					signY=i2;
				}
			}
					
			boolean noBall=true;
			if (moreThanExist) {
				for (int i2=signY+1;i2<data[signX].length;i2++) if (data[signX][i2]=='|') {
					noBall=false;
					break;
				}
			} else if (lessThanExist) {
				for (int i2=signY-1;i2>=0;i2--) if (data[signX][i2]=='|') {
					noBall=false;
					break;
				}
			}
			
			System.out.printf("Case %d: %s\n", testCase, noBall ? "No Ball" : "Thik Ball");
			br.readLine();//empty
		}
	}
}