import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int T=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		
		int [][] acTime=new int [N][T];
		for (int i=0;i<N;i++) Arrays.fill(acTime[i],-1);
		int [][] acSeq=new int [N][T];
		int [] acSeqCount=new int [N];

		for (int m=0;m<M;m++) {
			st=new StringTokenizer(br.readLine());
			int time=Integer.parseInt(st.nextToken());
			int teamID=Integer.parseInt(st.nextToken())-1;
			int problem=st.nextToken().charAt(0)-'A';
			String verdict=st.nextToken();
			if (problem<N && teamID<T && verdict.equals("Yes") && acTime[problem][teamID]==-1) {
				acSeq[problem][acSeqCount[problem]++]=teamID;
				acTime[problem][teamID]=time;
			}
		}
		
		StringBuilder sb=new StringBuilder();
		for (int i=0;i<N;i++) {
			sb.append((char)(i+'A'));
			sb.append(' ');
			if (acSeqCount[i]==0) sb.append("- -");
			else {
				int lastACTeam=acSeq[i][acSeqCount[i]-1];
				sb.append(acTime[i][lastACTeam]);
				sb.append(' ');
				sb.append(lastACTeam+1);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
	}
}