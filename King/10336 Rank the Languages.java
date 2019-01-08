import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	public static char [][] mat;
	public static int [] worldCount;
	
	public static class Value implements Comparable<Value> {
		char c;
		int count;
		
		public int compareTo(Value v) {
			if (this.count==v.count) {
				return this.c-v.c;
			}
			return v.count-this.count;
		}
	}
	
	public static void floodfill (int currX, int currY, char lang) {
		if (currX>=0 && currX<mat.length && currY>=0 && currY<mat[currX].length && mat[currX][currY]==lang) {
			mat[currX][currY]='.';
			
			floodfill(currX-1,currY,lang);
			floodfill(currX+1,currY,lang);
			
			floodfill(currX,currY-1,lang);
			floodfill(currX,currY+1,lang);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			mat=new char [Integer.parseInt(st.nextToken())][];
			for (int i=0;i<mat.length;i++) {
				mat[i]=br.readLine().toCharArray();
			}
			ArrayList<Character> charList=new ArrayList<>();
			worldCount=new int [128];
			for (int i=0;i<mat.length;i++) {
				for (int i2=0;i2<mat[i].length;i2++) {
					if (mat[i][i2]!='.') {
						if (!charList.contains(mat[i][i2])) {
							charList.add(mat[i][i2]);
						}
						worldCount[mat[i][i2]]++;
						floodfill(i,i2,mat[i][i2]);
					}
				}
			}
			
			Value [] val=new Value[charList.size()];
			for (int i=0;i<charList.size();i++) {
				val[i]=new Value();
				val[i].c=charList.get(i);
				val[i].count=worldCount[charList.get(i)];
			}
			Arrays.sort(val);
			
			
			StringBuilder sb=new StringBuilder();
			sb.append("World #");
			sb.append(testCase);
			sb.append("\n");
			for (int i=0;i<val.length;i++) {
				sb.append(val[i].c);
				sb.append(": ");
				sb.append(val[i].count);
				sb.append("\n");
			}
			System.out.print(sb.toString());
		}
	}
	
}