import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			int [][] adjMat=new int [N+1][N+1];
			for (int i=1;i<=N;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				// i -> j (c color)
				for (int c=1;c<=N;c++) adjMat[i][c]=Integer.parseInt(st.nextToken());
			}

			StringTokenizer st=new StringTokenizer(br.readLine());
			int N1=Integer.parseInt(st.nextToken());
			int N2=Integer.parseInt(st.nextToken());
			int N3=Integer.parseInt(st.nextToken());
			
			boolean [][] visited=new boolean [N+1][N+1];

			int ans=-1;
			LinkedList<int []> q=new LinkedList<>();
			q.addLast(new int [] {N1,N2,0});
			visited[N1][N2]=true;
			while (!q.isEmpty()) {
				int [] curr=q.removeFirst();
				if (curr[0]==N3 || curr[1]==N3) {
					ans=curr[2];
					break;
				}

				// Move n1
				int nextN1=adjMat[curr[0]][curr[1]];
				if (nextN1!=0 && !visited[nextN1][curr[1]]) {
					visited[nextN1][curr[1]]=true;
					q.addLast(new int [] {nextN1,curr[1],curr[2]+1});
				}
				
				// Move n2
				int nextN2=adjMat[curr[1]][curr[0]];
				if (nextN2!=0 && !visited[curr[0]][nextN2]) {
					visited[curr[0]][nextN2]=true;
					q.addLast(new int [] {curr[0],nextN2,curr[2]+1});
				}
			}

			StringBuilder sb=new StringBuilder();
			sb.append("Game #");
			sb.append(tc++);
			sb.append('\n');
			if (ans==-1) sb.append("Destination is Not Reachable !");
			else {
				sb.append("Minimum Number of Moves = ");
				sb.append(ans);
			}
			sb.append('\n');
			System.out.println(sb);
		}
	}

}