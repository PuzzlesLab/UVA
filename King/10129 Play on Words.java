import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {

	private static int getParent(int [] parent, int n) {
		if (parent[n]!=n) parent[n]=getParent(parent,parent[n]);
		return parent[n];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int N=Integer.parseInt(br.readLine());
			int [][] degree=new int [26][2];
			int [] parent=new int [26];
			for (int i=0;i<26;i++) parent[i]=i;
			boolean [] exists=new boolean [26];

			for (int n=0;n<N;n++) {
				String s=br.readLine();
				int n1=s.charAt(0)-'a';
				int n2=s.charAt(s.length()-1)-'a';
				degree[n1][0]++;
				degree[n2][1]++;
				exists[n1]=true;
				exists[n2]=true;

				int p1=getParent(parent,n1);
				int p2=getParent(parent,n2);
				if (p1<p2) parent[p2]=p1;
				else if (p1>p2) parent[p1]=p2;
			}

			// Check if graph is connected
			HashSet<Integer> parents=new HashSet<>();
			for (int i=0;i<26;i++) if (exists[i]) parents.add(getParent(parent,i));
			if (parents.size()!=1) {
				System.out.println("The door cannot be opened.");
				continue;
			}

			int [] counts=new int [4];
			/*
			 *   0 = no. of nodes
			 *   1 = no. of nodes with in deg = out deg
			 *   2 = no. of nodes with in deg = out deg + 1
			 *   3 = no. of nodes with in deg + 1 = out deg
			 *   
			 *   Match condition = v1+v2+v3=v0
			 */
			for (int i=0;i<26;i++) {
				if (!exists[i]) continue;
				counts[0]++;
				if (degree[i][0]==degree[i][1]) counts[1]++;
				else if (degree[i][0]==degree[i][1]+1) counts[2]++;
				else if (degree[i][1]==degree[i][0]+1) counts[3]++;
			}

			if (counts[0]==counts[1]+counts[2]+counts[3]) System.out.println("Ordering is possible.");
			else System.out.println("The door cannot be opened.");
		}
	}
}
