import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static char getParent(char [] parent, char c) {
		if (parent[c]!=c) parent[c]=getParent(parent,getParent(parent,parent[c]));
		return parent[c];
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			boolean [] exists=new boolean [128];
			char [] parent=new char [128];
			for (int i=0;i<parent.length;i++) parent[i]=(char)i;
			
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int E=Integer.parseInt(st.nextToken());
			for (int e=0;e<E;e++) {
				st=new StringTokenizer(br.readLine());
				char c1=st.nextToken().charAt(0);
				char c2=st.nextToken().charAt(0);
				exists[c1]=true;
				exists[c2]=true;

				if (c1==c2) continue;

				char p1=getParent(parent,c1);
				char p2=getParent(parent,c2);
				if (p1==p2) continue;
				if (p1<p2) parent[p2]=p1;
				else parent[p1]=p2;
			}
			int tree=N;
			for (int n=0;n<128;n++) if (exists[n] && getParent(parent,(char)n)!=n) tree--;
			// For 1 spanning tree -> n-e+f=2
			// For 2 spanning tree -> Deduct one for overcount
			// For X spanning tree -> n-e+f=1+x, f=1+x-n+e;
			System.out.println(1+tree-N+E);
		}
 	}

}
