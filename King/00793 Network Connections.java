import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class uva {
	
	private static int findSet(int [] parent, int i) {
		return (parent[i] == i) ? i : (parent[i]=findSet(parent, parent[i]));
	}
	
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		br.readLine(); //empty
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			int C=Integer.parseInt(br.readLine());
			
			int [] parent=new int [C];
			int [] rank=new int [C];
			for (int i=0;i<parent.length;i++) parent[i]=i;
			
			int success=0, fail=0;
			String s;
			while ((s=br.readLine())!=null) {
				if (s.length()==0) break;
				
				StringTokenizer st=new StringTokenizer(s);
				
				char op=st.nextToken().charAt(0);
				int c1=Integer.parseInt(st.nextToken())-1;
				int c2=Integer.parseInt(st.nextToken())-1;
				
				if (op=='c') {
					int x=findSet(parent, c1);
					int y=findSet(parent, c2);
					if (rank[x] > rank[y]) parent[y]=x;
					else {
						parent[x]=y;
						if (rank[x]==rank[y]) rank[y]++;
					}
				} else if (op=='q') {
					if (findSet(parent, c1)==findSet(parent, c2)) success++;
					else fail++;
				}
			}
			
			System.out.println(success+","+fail);
			if (testCase<testCaseCount-1) System.out.println();
		}
		
	}

}