import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
	
	private static int MaxBlackCount=0;
	private static String Ans=null;
	
	private static void fill(boolean [][] adjacent, int currNode, char [] color, int blackCount) {
		if (currNode<color.length) {
			boolean blackAdj=false;
			for (int n=0;n<adjacent.length;n++) if (adjacent[currNode][n] && color[n]=='B') {
				blackAdj=true;
				break;
			}
			if (!blackAdj) {
				color[currNode]='B';
				fill(adjacent,currNode+1,color,blackCount+1);
			}
			color[currNode]='W';
			fill(adjacent,currNode+1,color,blackCount);
		} else {
			if (blackCount>MaxBlackCount) {
				MaxBlackCount=blackCount;

				StringBuilder sb=new StringBuilder();
				sb.append(blackCount);
				sb.append('\n');
				for (int n=0;n<adjacent.length;n++) if (color[n]=='B') {
					sb.append(n+1);
					sb.append(' ');
				}
				if (blackCount>0) sb.setLength(sb.length()-1);
				Ans=sb.toString();
			}
		}
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			boolean [][] adjacent=new boolean[N][N];

			for (int k=0;k<K;k++) {
				st=new StringTokenizer(br.readLine());
				int n1=Integer.parseInt(st.nextToken())-1;
				int n2=Integer.parseInt(st.nextToken())-1;
				adjacent[n1][n2]=true;
				adjacent[n2][n1]=true;
			}

			MaxBlackCount=0;
			Ans=null;
			char [] color=new char[N];
			Arrays.fill(color,'W');
			fill(adjacent,0,color,0);

			System.out.println(Ans);
		}
	}

}