import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	//Union Find Disjoin Set. MST is overkill.
	public static int getParent(int [] parent, int id) {
		if (parent[id]!=id) parent[id]=getParent(parent, parent[id]);
		return parent[id];
	}
	
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		int [] parent=new int [100000];
		for (int i=0;i<parent.length;i++) parent[i]=i;
		int count=0;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int first=Integer.parseInt(st.nextToken());
			if (first!=-1)  {
				int second=Integer.parseInt(st.nextToken());
				int p1=getParent(parent, first);
				int p2=getParent(parent, second);
				if (p1!=p2) {
					if (p1>p2) parent[p1]=p2;
					else parent[p2]=p1;
				} else count++;
			} else {
				br.readLine();
				System.out.println(count);
				count=0;
				for (int i=0;i<parent.length;i++) parent[i]=i;
			}
		}
	}

}