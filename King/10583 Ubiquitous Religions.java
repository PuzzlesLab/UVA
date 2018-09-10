import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static int findSet(int [] parent, int i) {
		return parent[i]==i ? i : (parent[i]=findSet(parent, parent[i]));
	}
	public static void main (String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int testCaseCount=1;
		while (!(s=br.readLine()).equals("0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int N=Integer.parseInt(st.nextToken()), M=Integer.parseInt(st.nextToken());
			
			//Form sets. (No need rank since we don't need to do lookup!)
			int [] parent=new int[N];
			int setCount=N;
			for (int i=0;i<N;i++) parent[i]=i;
			
			for (int m=0;m<M;m++) {
				st=new StringTokenizer(br.readLine());
				int x=findSet(parent, Integer.parseInt(st.nextToken())-1), y=findSet(parent, Integer.parseInt(st.nextToken())-1);
				if (x!=y) {
					parent[y]=x;
					setCount--;
				}
			}
			
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(testCaseCount++);
			sb.append(": ");
			sb.append(setCount);
			System.out.println(sb.toString());
		}
	}

}