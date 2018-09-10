import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static int findSet(int [] parent, int id) {
		return (parent[id]==id) ? id : (parent[id]=findSet(parent,parent[id]));
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
			
			int [] parent=new int [N];
			int [] rank=new int [N];
			int [] size=new int [N];
			for (int i=0;i<N;i++) {
				parent[i]=i;
				size[i]=1;
			}
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int C=Integer.parseInt(st.nextToken());
				if (C>1) {
					int first=Integer.parseInt(st.nextToken());

					for (int c=1;c<C;c++) {
						int second=Integer.parseInt(st.nextToken());
						
						int x=findSet(parent, first), y=findSet(parent, second);
						if (x!=y) {
							if (rank[x]>rank[y]) {
								parent[y]=x;
								size[x]+=size[y];
							} else {
								if (rank[x]==rank[y]) rank[y]++;
								parent[x]=y;
								size[y]+=size[x];
							}
						}
					}
				}
			}
			
			System.out.println(size[findSet(parent, 0)]);
		}
	}

}
