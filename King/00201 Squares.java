import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Main {

	private static int N;
	private static boolean [][] Edges;

	private static int flatten(int x, int y) {
		return x*N+y;
	}

	private static boolean verify(int x, int y, int size) {
		// H1
		for (int d=0;d<size;d++) if (!Edges[flatten(x+d,y)][flatten(x+d+1,y)]) return false;
		// H2
		for (int d=0;d<size;d++) if (!Edges[flatten(x+d,y+size)][flatten(x+d+1,y+size)]) return false;
		// V1
		for (int d=0;d<size;d++) if (!Edges[flatten(x,y+d)][flatten(x,y+d+1)]) return false;
		// V2
		for (int d=0;d<size;d++) if (!Edges[flatten(x+size,y+d)][flatten(x+size,y+d+1)]) return false;
		return true;
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int tc=1;
		while ((s=br.readLine())!=null) {
			N=Integer.parseInt(s);
			int M=Integer.parseInt(br.readLine());
			
			Edges=new boolean [flatten(N,N)][flatten(N,N)];
			boolean [] pointExists=new boolean [Edges.length];
			ArrayList<int []> startPoints=new ArrayList<>();
			for (int m=0;m<M;m++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				char c=st.nextToken().charAt(0);
				int i=Integer.parseInt(st.nextToken())-1;
				int j=Integer.parseInt(st.nextToken())-1;
				if (!pointExists[flatten(i,j)]) {
					startPoints.add(new int [] {i,j});
					pointExists[flatten(i,j)]=true;
				}
				if (c=='H') Edges[flatten(i,j)][flatten(i,j+1)]=true;
				else if (c=='V') Edges[flatten(j,i)][flatten(j+1,i)]=true;
			}

			int [] counts=new int [N+1];
			boolean flag=false;
			for (int size=1;size<=N;size++) {
				for (int i=0;i<startPoints.size();i++) {
					int x=startPoints.get(i)[0];
					int y=startPoints.get(i)[1];
					if (N-x<=size || N-y<=size) continue;
					if (verify(x,y,size)) {
						counts[size]++;
						flag=true;
					}
				}
			}
			
			StringBuilder sb=new StringBuilder();
			if (tc>1) sb.append("\n**********************************\n\n");
			sb.append("Problem #");
			sb.append(tc++);
			sb.append("\n\n");
			if (flag) {
				for (int size=1;size<=N;size++) if (counts[size]>0) {
					sb.append(counts[size]);
					sb.append(" square (s) of size ");
					sb.append(size);
					sb.append('\n');
				}
				sb.setLength(sb.length()-1);
			} else sb.append("No completed squares can be found.");
			System.out.println(sb);
		}
	}

}
