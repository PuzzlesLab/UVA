import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=1;testCase<=testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int R=Integer.parseInt(st.nextToken());
			int C=Integer.parseInt(st.nextToken());
			boolean [][] flag=new boolean [R][C];
			for (int r=0;r<R;r++) {
				char [] ch=br.readLine().toCharArray();
				for (int c=0;c<C;c++) flag[r][c]=ch[c]=='1';
			}
			
			int [][] moveLeftCount=new int [R][C];
			for (int r=0;r<R;r++) if (flag[r][0]) moveLeftCount[r][0]=Integer.MAX_VALUE;
			//Starts with c==1 (can't move left when c=0!)
			for (int c=1;c<C;c++) for (int r=0;r<R;r++) if (flag[r][c]) {
				if ((flag[r][c-1] && moveLeftCount[r][c-1]!=Integer.MAX_VALUE) || !flag[r][c-1]) moveLeftCount[r][c]=moveLeftCount[r][c-1]+1;
				else moveLeftCount[r][c]=Integer.MAX_VALUE;
			}
			
			int [][] moveRightCount=new int [R][C];
			for (int r=0;r<R;r++) if (flag[r][C-1]) moveRightCount[r][C-1]=Integer.MAX_VALUE;
			//Starts with c==C-2 (can't move left when c=C-1!)
			for (int c=C-2;c>=0;c--) for (int r=0;r<R;r++) if (flag[r][c]) {
				if ((flag[r][c+1] && moveRightCount[r][c+1]!=Integer.MAX_VALUE) || !flag[r][c+1]) moveRightCount[r][c]=moveRightCount[r][c+1]+1;
				else moveRightCount[r][c]=Integer.MAX_VALUE;
			}

			int ans=Integer.MAX_VALUE;
			for (int c=0;c<C;c++) {
				int curr=0;
				for (int r=0;r<R;r++) {
					int min=Math.min(moveLeftCount[r][c], moveRightCount[r][c]);
					if (min==Integer.MAX_VALUE) {
						curr=Integer.MAX_VALUE;
						break;
					}
					else curr+=min;
				}
				ans=Math.min(curr, ans);
			}
			if (ans==Integer.MAX_VALUE) ans=-1;
			
			System.out.printf("Case %d: %d\n", testCase, ans);
		}
		
	}

}