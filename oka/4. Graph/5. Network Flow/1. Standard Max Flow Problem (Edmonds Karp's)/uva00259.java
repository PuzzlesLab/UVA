import java.util.*;
import java.io.*;

public class Main {
	// 0 source, 1-26 application, 27-36 computer, 37 sink
	public static int g[][] = new int[38][38];
	public static int p[] = new int[38];
	public static int INT_MAX = 1_000_000;
	public static int appNum;
	
	public static void init() {
		appNum = 0;
		Arrays.fill(p,-1);
		for(int k[]:g) {
			Arrays.fill(k,0);
		}
		for(int i=0;i<=9;i++) {
			g[27+i][37] = 1;
		}
	}
	
	public static void read(String s) {
		StringTokenizer st = new StringTokenizer(s);
		String app = st.nextToken();
		String list = st.nextToken();
		int application = app.charAt(0)-'A'+1;
		int num = app.charAt(1)-'0';
		appNum += num;
		g[0][application] = num;
		
		for(int i=0;i<list.length()-1;i++) {
			g[application][27+list.charAt(i)-'0'] = 1;//??
		}
	}
	
	public static void print(int i) {
		for(int j=1;j<=26;j++) {
			if(g[i][j]==1) {
				System.out.print((char)(j+'A'-1));
				return;
			}
		}
		System.out.print("_");
	}
	
	public static boolean bfs(int source,int sink) {
		boolean visited[] = new boolean[38];
		Queue<Integer> q  = new LinkedList<>();
		q.add(source);
		visited[source] = true;
		Arrays.fill(p,-1);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int i=0;i<38;i++) {
				if(g[cur][i]>0&&!visited[i]) {
					visited[i] = true;
					p[i] = cur;
					q.add(i);
				}
			}
		}
		return visited[sink];
	}
	
	public static int EK(int source,int sink) {
		int mf = 0;
		while(bfs(source,sink)) {
			int pathFlow = Integer.MAX_VALUE;
			for(int v=sink;v!=source;v=p[v]) {
				int u = p[v];
				pathFlow = Math.min(pathFlow,g[u][v]);
			}
			for(int v=sink;v!=source;v=p[v]) {
				int u = p[v];
				g[u][v] -= pathFlow;
				g[v][u] += pathFlow;
			}
			mf += pathFlow;
		}
		return mf;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while((s=br.readLine())!=null&&!s.isEmpty()) {
			init();
			read(s);
			while((s=br.readLine())!=null&&!s.isEmpty()) {
				read(s);
			}
			int mf = EK(0,37);
			if(mf!=appNum) {
				System.out.println("!");
			}else {				
				for(int i=27;i<=36;i++) {
					print(i);
				}
				System.out.println();
			}
		}
	}

}
