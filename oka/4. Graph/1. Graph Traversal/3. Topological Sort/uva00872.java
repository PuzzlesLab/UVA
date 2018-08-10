import java.util.*;
import java.io.*;

public class Main {
	public static boolean possible;
	public static void permutation(int start,int end,boolean g[][],int x[],int next[],boolean visited[]) {
		if(start==end) {
			possible = true;
			for(int i=0;i<next.length;i++) {
				System.out.print((char)(next[i]+'A'));
				if(i<next.length-1)
					System.out.print(" ");
			}
			System.out.println();
		}else {
			for(int i=0;i<x.length;i++) {
				int cur = x[i];
				if(visited[cur])
					continue;
				visited[cur] = true;
				next[start] = cur;
				boolean safe = true;
				for(int j=0;j<x.length;j++) {
					int adj = x[j];					
					if(!visited[adj]&&g[adj][cur]) {
						safe = false;
						break;
					}
				}
				if(safe) {
					permutation(start+1,end,g,x,next,visited);
				}
				next[start] = 0;
				visited[cur] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int i=0;i<T;i++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = st.countTokens();
			int r[] = new int[v];
			boolean g[][] = new boolean[26][26];
			for(int j=0;j<v;j++) {
				r[j] = st.nextToken().charAt(0)-'A';
			}
			Arrays.sort(r);
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				String stat = st.nextToken();
				int a = stat.charAt(0)-'A';
				int b = stat.charAt(2)-'A';
				g[a][b] = true;
			}
			int next[] = new int[v];
			boolean visited[] = new boolean[26];
			possible = false;
			permutation(0,v,g,r,next,visited);
			if(!possible)
				System.out.println("NO");
			if(i<T-1)
				System.out.println();
		}

	}
}
