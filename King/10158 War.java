package uva;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	/*
	 * 	TLE solution - HashSet with enemy.
	 * 	This solution uses another disjoint set (complement index) to join enemies.
	 */
	private static int findSet(int [] parent, int id) {
		return (parent[id]==id) ? id : (parent[id]=findSet(parent, parent[id]));
	}
	
	private static void join(int x, int y, int [] parent, int [] rank) {
		if (x!=y) {
			if (rank[x]>rank[y]) parent[y]=x;
			else {
				if (rank[x]==rank[y]) rank[y]++;
				parent[x]=y;
			}
		}
	}

	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int N=Integer.parseInt(br.readLine());

		int [] parent=new int [N+N];
		int [] rank=new int [N+N];
		for (int i=0;i<parent.length;i++) parent[i]=i;
		for (int i=N;i<rank.length;i++) rank[i]=1;
		
		StringBuilder sb=new StringBuilder();
		while (true) {
			String s=br.readLine();
			if (s.trim().equals("0 0 0")) break;
			StringTokenizer st=new StringTokenizer(s);
			int c=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());

			int result=-2;	
			int x1=findSet(parent, x), x2=findSet(parent, x+N), y1=findSet(parent, y), y2=findSet(parent, y+N);
			switch (c) {
				case 1: if (x1!=y2) {
							join(x1, y1, parent, rank);
							join(x2, y2, parent, rank);
						}
						else result=-1;
						break;
				case 2: if (x1!=y1) {
							join(x1, y2, parent, rank);
							join(x2, y1, parent, rank);
						} else result=-1;
						break;
				case 3: result = (x1==y1) ? 1 : 0;
						break;
				case 4: result = (x1==y2) ? 1 : 0;
						break;
			}
			if (result!=-2) {
				sb.append(result);
				sb.append('\n');
			}
		}
		System.out.print(sb.toString());
	}

}
