import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			char [] chars=br.readLine().toCharArray();
			int maxHeight=0;
			int minHeight=0;
			int currLevel=0;
			for (char c : chars) {
				if (c=='/') {
					currLevel++;
					maxHeight=Math.max(maxHeight, currLevel);
				} else if (c=='\\') {
					currLevel--;
					minHeight=Math.min(minHeight, currLevel);
				}
			}
			
			char [][] map=new char [maxHeight-minHeight+2][chars.length];
			int currX=maxHeight+1, currY=0;
			for (int i=0;i<chars.length;i++) {
				char c=chars[i];
				map[currX][currY]=c;
				if (i<chars.length-1) {
					if ((c=='_' || c=='\\') && chars[i+1]=='\\') currX++;
					else if (c=='/' && (chars[i+1]=='/' || chars[i+1]=='_')) currX--;
				}
				currY++;
			}
			
			int ans=0;
			for (int row=map.length-1;row>=0;row--) {
				boolean open=false;
				int start=-1;
				for (int col=0;col<map[row].length;col++) {
					char c=map[row][col];
					if (c=='\\' && !open) {
						open=true;
						start=col;
					} else if (c=='/' && open) {
						open=false;
						ans+=(col-start);
						start=-1;
					}
				}
			}
			System.out.println(ans);
		}
	}

}