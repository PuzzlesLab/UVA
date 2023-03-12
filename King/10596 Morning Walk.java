import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {

	private static int getParent(int [] parent, int n) {
		if (parent[n]!=n) return parent[n]=getParent(parent,parent[n]);
		return parent[n];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken());
			int R=Integer.parseInt(st.nextToken());

			int [] degree=new int [N+1];
			int [] parent=new int [N+1];
			for (int n=0;n<parent.length;n++) parent[n]=n;
			
			for (int r=0;r<R;r++) {
				st=new StringTokenizer(br.readLine());
				int c1=Integer.parseInt(st.nextToken());
				int c2=Integer.parseInt(st.nextToken());
				degree[c1]++;
				degree[c2]++;
				
				int p1=getParent(parent,c1);
				int p2=getParent(parent,c2);
				if (p1<p2) parent[p2]=p1;
				else parent[p1]=p2;
			}
			
			HashSet<Integer> parents=new HashSet<>();
			for (int n=0;n<=N;n++) if (degree[n]>0) parents.add(getParent(parent,n));
			if (parents.size()!=1) {
				System.out.println("Not Possible");
				continue;
			}
			
			boolean allEven=true;
			for (int n=0;n<=N;n++) allEven&=degree[n]%2==0;
			
			if (!allEven) {
				System.out.println("Not Possible");
				continue;
			}
			
			System.out.println("Possible");
		}
	}
}
