import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			int Q=Integer.parseInt(st.nextToken());
			char [][] data=new char [M][];
			for (int i=0;i<M;i++) data[i]=br.readLine().toCharArray();
			
			StringBuilder sb=new StringBuilder();
			sb.append(M);
			sb.append(' ');
			sb.append(N);
			sb.append(' ');
			sb.append(Q);
			sb.append('\n');
			for (int q=0;q<Q;q++) {
				st=new StringTokenizer(br.readLine());
				int r=Integer.parseInt(st.nextToken());
				int c=Integer.parseInt(st.nextToken());
				
				int S=1;
				for (int s=1;true;s++) {
					if (r-s<0 || r+s>=M || c-s<0 || c+s>=N) break;
					else {
						boolean flag=true;
						for (int x=r-s;x<=r+s && flag;x++) for (int y=c-s;y<=c+s && flag;y++) flag&=data[x][y]==data[r][c];
						if (flag) S=s*2+1;
						else break;
					}
				}
				sb.append(S);
				sb.append('\n');
			}
			System.out.print(sb.toString());
		}
	}

}